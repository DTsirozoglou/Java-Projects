package com.acertainsupplychain.Globalclient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.io.Buffer;
import org.eclipse.jetty.io.ByteArrayBuffer;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.acertainsupplychain.OrderStep;
import com.acertainsupplychain.exceptions.InvalidWorkflowException;
import com.acertainsupplychain.exceptions.OrderProcessingException;
import com.acertainsupplychain.interfaces.OrderManager;
import com.acertainsupplychain.utils.ClientConstants;
import com.acertainsupplychain.utils.CommunicationUtility;
import com.acertainsupplychain.utils.ServerConstants;
import com.acertainsupplychain.utils.ServerResult;


public class GlobalClientHTTPProxy implements OrderManager {
	
	private HttpClient client;
	private Map<Integer,String> serverAddresses;
	private final String filePath = "A:\\eclipse\\workspace\\acertainsupplychain\\src\\BrokerServer.properties";
	private Map<Integer,String> flowIDs;
	
	/**
	 * Initialize the client object
	 */
	public GlobalClientHTTPProxy() throws Exception {
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
		flowIDs = new HashMap<Integer,String>();
		initializeServers();
	}
	
	private String toServer() throws OrderProcessingException {
			if (serverAddresses.size()>0){
			Random r = new Random(System.currentTimeMillis());
			int server = r.nextInt(serverAddresses.size());
			String toServer=serverAddresses.get(server);
			return toServer;
		}else 
			throw new OrderProcessingException("All integrations Brokers are Down!");
	}
	
	private String flowServer(int orderWorkflowId) throws InvalidWorkflowException {
		String flowServer="";
		boolean found = false;
		for (Integer id : flowIDs.keySet()){
				if (orderWorkflowId ==id){
					flowServer = flowIDs.get(id);
				found=true;
				break;
				}				
		}
		if (!found){
			throw new InvalidWorkflowException("SERVER DOWN!");
			}
		return flowServer;
}
	
	private void initializeServers() throws IOException {
		Properties props = new Properties();
		serverAddresses = new HashMap<Integer,String>();
		props.load(new FileInputStream(filePath));
		String serverAddresses = props.getProperty(ServerConstants.KEY_BROKER_SERVER);
		int supplierID =0;
		for (String server : serverAddresses.split(ServerConstants.SPLIT_REGEX)) {
			if (!server.toLowerCase().startsWith("http://")) {
				server = new String("http://" + server);
			}
			this.serverAddresses.put(supplierID,server);
			supplierID++;
		}
	}

	public void stop() {
		try {
			client.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int registerOrderWorkflow(List<OrderStep> steps)
			throws OrderProcessingException {

		String toServer = toServer();
		String xmlString = CommunicationUtility.serializeObjectToXMLString(steps);
		ContentExchange exchange = new ContentExchange();
		String urlString = toServer + "/REGISTER";
		exchange.setMethod("POST");
		exchange.setURL(urlString);
		Buffer requestContent = new ByteArrayBuffer(xmlString);
		exchange.setRequestContent(requestContent);	
		ServerResult result = new ServerResult();
		int flowID;
		try {
			result = CommunicationUtility.SendAndRecv(this.client, exchange);
			flowID= result.getResult();
			flowIDs.put(flowID, toServer);
		} catch (Exception e) {
				serverAddresses.remove(toServer);
				if (serverAddresses.size()>0)
					try {
						flowID = registerOrderWorkflow(steps);
					}catch (Exception e1) {
						throw new OrderProcessingException(e1);
					}
				else throw new OrderProcessingException(e);
		} 	
		return flowID;
	}

	@Override
	public List<StepStatus> getOrderWorkflowStatus(int orderWorkflowId)
			throws InvalidWorkflowException {
		
		String toServer;
		toServer = flowServer(orderWorkflowId);
		String xmlString = CommunicationUtility.serializeObjectToXMLString(orderWorkflowId);
		Buffer requestContent = new ByteArrayBuffer(xmlString);
		ContentExchange exchange = new ContentExchange();
		String urlString = toServer + "/STATUS";
		exchange.setMethod("POST");
		exchange.setURL(urlString);
		exchange.setRequestContent(requestContent);	
		ServerResult result = new ServerResult();
		List<StepStatus> status= null;
		try {
			result = CommunicationUtility.SendAndRecv(this.client, exchange);
			status = result.getStatus();
		} catch (Exception e) {
			throw new InvalidWorkflowException(e.getMessage());
		} 	
		return status;
	}
}
