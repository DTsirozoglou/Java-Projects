package acertainsupplychain.BrokerServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.io.Buffer;
import org.eclipse.jetty.io.ByteArrayBuffer;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.acertainsupplychain.OrderStep;
import com.acertainsupplychain.exceptions.InvalidWorkflowException;
import com.acertainsupplychain.exceptions.LockException;
import com.acertainsupplychain.interfaces.OrderManager;
import com.acertainsupplychain.utils.ClientConstants;
import com.acertainsupplychain.utils.CommunicationUtility;
import com.acertainsupplychain.utils.ServerConstants;
import com.acertainsupplychain.utils.ServerResponse;


public class IntegrationBrokerHTTPProxy implements OrderManager {
	private HttpClient client;
	private Map<String,Integer> serverAddresses;
	private final String filePath = "A:\\eclipse\\workspace\\acertainsupplychain\\src\\SupplierServer.properties";
	private CopyOnWriteArrayList<Workflow> workflows = new CopyOnWriteArrayList <Workflow>();
	private int clientAdress;
	
	
	/**
	 * Initialize the client object
	 */
	public IntegrationBrokerHTTPProxy(int adress) throws Exception {
		client = new HttpClient();
		client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
		client.setMaxConnectionsPerAddress(ClientConstants.CLIENT_MAX_CONNECTION_ADDRESS); // max
																									// concurrent
																									// connections
																									// to
																									// every
																									// address
		client.setThreadPool(new QueuedThreadPool(
				ClientConstants.CLIENT_MAX_THREADSPOOL_THREADS)); // max
																			// threads
		client.setTimeout(ClientConstants.CLIENT_MAX_TIMEOUT_MILLISECS); // seconds
																					// timeout;
																					// if
																					// no
																					// server
																					// reply,
																					// the
																					// request
																					// expires
		
		client.start();
		clientAdress = adress;
		initializeServers();
	}
	
	private String toServer(int supplierID) throws Exception{
		
		String toServer="";
		boolean found = false;
		for (String serverAdr : serverAddresses.keySet()){
				if (supplierID ==serverAddresses.get(serverAdr)){
				toServer = serverAdr;
				found=true;
				break;
				}				
		}
		if (!found)
			throw new Exception("SERVER DOWN!");
		return toServer;
	}
	
	private void initializeServers() throws IOException {
		Properties props = new Properties();
		serverAddresses = new HashMap<String,Integer>();
		props.load(new FileInputStream(filePath));
		String serverAddresses = props.getProperty(ServerConstants.KEY_SERVER);
		int supplierID =0;
		for (String server : serverAddresses.split(ServerConstants.SPLIT_REGEX)) {
			if (!server.toLowerCase().startsWith("http://")) {
				server = new String("http://" + server);
			}
			this.serverAddresses.put(server, supplierID);
			supplierID++;
		}
	}

	public void stop() {
		try {
			client.stop();
		} catch (Exception e) {
		}
	}
	
	private  boolean completedWorkflow(Workflow flow){
		
		List<OrderStep> steps = flow.getSteps();
		for (OrderStep step : steps){
			if ((step.getStatus() == StepStatus.REGISTERED)||(step.getStatus()==StepStatus.JUSTCREATED))
				return false;				
		}
		return true;
	}
	
	private void writeToLog(String status,int workID, int stepID) {
		
		PrintWriter out=null;
		try {
			out = new PrintWriter(new FileOutputStream(new File("Broker_"+clientAdress+".txt"),true));
			out.println("Workflow ID :"+ workID +" Step ID : "+stepID+" Status : "+status);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			out.close();
		}		
	}
	
	private void writeToLog(String status,int workID) {
		
		PrintWriter out=null;
		try {
			out = new PrintWriter(new FileOutputStream(new File("Broker_"+clientAdress+".txt"),true));
			out.println("Workflow ID :"+ workID +" Status : "+status);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			out.close();
		}	
}

	@Override
	public int registerOrderWorkflow(List<OrderStep> steps){
		final Workflow wflow=new Workflow(steps);
		workflows.add(wflow);
		final int workID = wflow.getWorkID();
		String from = Integer.toString(workID);
		String cl = Integer.toString(clientAdress);
		writeToLog("STARTED",workID);
		for (final OrderStep step:steps){
			step.setFrom("Broker Adress: "+cl+ " with flowID: "+from);
			final String toServer;
			try {
				toServer = this.toServer(step.getSupplierId());
			} catch (Exception e1) {
				step.setStatus(StepStatus.SERVER_DOWN);
				writeToLog("SERVER DOWN",workID,step.getStepID());
				continue;
			}
			String xmlString = CommunicationUtility.serializeObjectToXMLString(step);
			Buffer requestContent = new ByteArrayBuffer(xmlString);
			ContentExchange exchange = new ContentExchange(true)//true
			{
				@Override
			    protected void onResponseComplete() throws IOException
			    {
			        int status = getResponseStatus();
			        if (status == 200){
			        	ServerResponse r = new ServerResponse();
			        	r = (ServerResponse)CommunicationUtility.deserializeXMLStringToObject(getResponseContent()); 
			        	if (r.getComplete()){
			        		step.setStatus(StepStatus.SUCCESSFUL);
			        		writeToLog("COMPLETED",workID,step.getStepID());
			        		if (completedWorkflow(wflow)){
			        			writeToLog("END",workID);
			        		}
			        	}
			        	else if (r.getException() instanceof LockException){
			        		
//			        		writeToLog("RESENDING",workID,step.getStepID());
			        		resendLocked(step,workID,wflow);
			        		
			        	}
			        	else{
			        		step.setStatus(StepStatus.FAILED);
			        		writeToLog("FAILED",workID,step.getStepID());
			        		if (completedWorkflow(wflow))
			        			writeToLog("END",workID);
			        	}
			        }
			    }
				
				@Override
			    protected void onConnectionFailed(Throwable x) 
			    {
					serverAddresses.remove(toServer);
					step.setStatus(StepStatus.SERVER_DOWN);
	        		writeToLog("SERVER DOWN",workID,step.getStepID());
	        		if (completedWorkflow(wflow))
	        			writeToLog("END",workID);
			    }
				
				@Override
			    protected void onExpire() 
			    {
//					serverAddresses.remove(toServer);
					step.setStatus(StepStatus.SERVER_DOWN);
	        		writeToLog("SERVER DOWN",workID,step.getStepID());
	        		if (completedWorkflow(wflow))
	        			writeToLog("END",workID);
			    }
			};
			String urlString = toServer + "/EXECUTE";
			exchange.setMethod("POST");
			exchange.setURL(urlString);
			exchange.setRequestContent(requestContent);	

			try {
				this.client.send(exchange);
			} catch (IOException e) {
			} 			
			
			step.setStatus(StepStatus.REGISTERED);
			writeToLog("BEGUN",workID,step.getStepID());
		}

//		System.out.println(workID);
		return workID;
	}

	@Override
	public List<StepStatus> getOrderWorkflowStatus(int orderWorkflowId)
			throws InvalidWorkflowException {
		List<StepStatus> status= new ArrayList<StepStatus>();
		for (Workflow flow:workflows){
			if (orderWorkflowId == flow.getWorkID()){
				for (OrderStep step : flow.getSteps()){
					status.add(step.getStatus());
				}
				return status;
			}
		}
		throw new InvalidWorkflowException();
	}


	protected void resendLocked(final OrderStep step,final int workID,final Workflow wflow) {
		
		final String toServer;
		try {
			toServer = this.toServer(step.getSupplierId());
		
				
			String xmlString = CommunicationUtility.serializeObjectToXMLString(step);
			Buffer requestContent = new ByteArrayBuffer(xmlString);
			ContentExchange exchange = new ContentExchange(true)//true
			{
				@Override
			    protected void onResponseComplete() throws IOException
			    {
			        int status = getResponseStatus();
			        if (status == 200){
			        	ServerResponse r = new ServerResponse();
			        	r = (ServerResponse)CommunicationUtility.deserializeXMLStringToObject(getResponseContent()); 
			        	if (r.getComplete()){
			        		step.setStatus(StepStatus.SUCCESSFUL);
			        		writeToLog("COMPLETED",workID,step.getStepID());
			        		if (completedWorkflow(wflow)){
			        			writeToLog("END",workID);
			        		}
			        	}
			        	else if (r.getException() instanceof LockException){
			        		
//			        		writeToLog("RESENDING",workID,step.getStepID());
			        		resendLocked(step,workID,wflow);
			        		
			        	}
			        	else{
			        		step.setStatus(StepStatus.FAILED);
			        		writeToLog("FAILED",workID,step.getStepID());
			        		if (completedWorkflow(wflow))
			        			writeToLog("END",workID);
			        	}
			        }
			    }
				
				@Override
			    protected void onConnectionFailed(Throwable x) 
			    {
					serverAddresses.remove(toServer);
					step.setStatus(StepStatus.SERVER_DOWN);
	        		writeToLog("SERVER DOWN",workID,step.getStepID());
	        		if (completedWorkflow(wflow))
	        			writeToLog("END",workID);
			    }
				
				@Override
			    protected void onExpire() 
			    {
	//				serverAddresses.remove(toServer);
					step.setStatus(StepStatus.SERVER_DOWN);
	        		writeToLog("SERVER DOWN",workID,step.getStepID());
	        		if (completedWorkflow(wflow))
	        			writeToLog("END",workID);
			    }
			};
			String urlString = toServer + "/EXECUTE";
			exchange.setMethod("POST");
			exchange.setURL(urlString);
			exchange.setRequestContent(requestContent);	
	
			try {
				this.client.send(exchange);
			} catch (IOException e) {
			} 
		} catch (Exception e1) {
			step.setStatus(StepStatus.SERVER_DOWN);
			writeToLog("SERVER DOWN",workID,step.getStepID());
		}
	}
}



