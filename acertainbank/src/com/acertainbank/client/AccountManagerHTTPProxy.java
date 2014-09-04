package com.acertainbank.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.io.Buffer;
import org.eclipse.jetty.io.ByteArrayBuffer;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.acertainbank.exceptions.BankException;
import com.acertainbank.exceptions.InexistentAccountException;
import com.acertainbank.exceptions.InexistentBranchException;
import com.acertainbank.exceptions.LockException;
import com.acertainbank.exceptions.NegativeAmountException;
import com.acertainbank.interfaces.AccountManager;
import com.acertainbank.utils.BankConstants;
import com.acertainbank.utils.BankResult;
import com.acertainbank.utils.BankUtility;


public class AccountManagerHTTPProxy implements AccountManager {
	private HttpClient client;
	private final String filePath = "A:\\eclipse-standard-kepler-R-win32-x86_64\\workspace\\acertainbank\\src\\server.properties";
	private List<String> serverAddresses;
	

	
	/**
	 * Initialize the client object
	 */
	public AccountManagerHTTPProxy() throws Exception {
		client = new HttpClient();
		client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
		client.setMaxConnectionsPerAddress(BankClientConstants.CLIENT_MAX_CONNECTION_ADDRESS); // max
																									// concurrent
																									// connections
																									// to
																									// every
																									// address
		client.setThreadPool(new QueuedThreadPool(
				BankClientConstants.CLIENT_MAX_THREADSPOOL_THREADS)); // max
																			// threads
		client.setTimeout(BankClientConstants.CLIENT_MAX_TIMEOUT_MILLISECS); // seconds
																					// timeout;
																					// if
																					// no
																					// server
																					// reply,
																					// the
																					// request
																					// expires
		
		// Enquire the mapping between partitions and branches 
		client.start();
		initializeServers();
		BankResult result = null;
		ContentExchange exchange = new ContentExchange();
		Random r =new Random(System.currentTimeMillis());
		String server = serverAddresses.get(r.nextInt(serverAddresses.size()));
		String urlString = server + "/INFO";
		exchange.setMethod("POST");
		exchange.setURL(urlString);
		result = BankUtility.SendAndRecv(this.client, exchange);
		accManagerPartition.putAll(result.getPartitions());
	}
	
	private String toServer(int branchID) throws InexistentBranchException{
		
		String toServer="";
		boolean found = false;
		for (String serverAdr : accManagerPartition.keySet()){
			if (found)
				break;
			for (int br : accManagerPartition.get(serverAdr)){
				if (branchID ==br){
					toServer = serverAdr;
					found=true;
					break;
				}				
			}
		}
		if (!found)
			throw new InexistentBranchException();	
		return toServer;
	}
	
	private void initializeServers() throws IOException {
		Properties props = new Properties();
		serverAddresses = new ArrayList<String>();
		props.load(new FileInputStream(filePath));
		String serverAddresses = props.getProperty(BankConstants.KEY_SERVER);
		for (String server : serverAddresses.split(BankConstants.SPLIT_REGEX)) {
			if (!server.toLowerCase().startsWith("http://")) {
				server = new String("http://" + server);
			}
			this.serverAddresses.add(server);
		}
	}
	
	@Override
	public void credit(int branchId, int accountId, double amount)
			throws InexistentBranchException, InexistentAccountException,
			NegativeAmountException,LockException  {
		if(amount<0)
			throw new NegativeAmountException(amount);
		String xmlString = BankUtility
				.serializeObjectToXMLString(branchId,accountId,amount);
		Buffer requestContent = new ByteArrayBuffer(xmlString);
		ContentExchange exchange = new ContentExchange();
		String toServer = this.toServer(branchId);
		String urlString = toServer + "/CREDIT";
		exchange.setMethod("POST");
		exchange.setURL(urlString);
		exchange.setRequestContent(requestContent);
		try {
			BankUtility.SendAndRecv(this.client, exchange);
		} catch (BankException  e) {
			if (e instanceof NegativeAmountException)
				throw new NegativeAmountException(amount);
			else if (e instanceof InexistentAccountException)
				throw new InexistentAccountException(accountId);
			else if (e instanceof LockException)
				throw new LockException(e.getMessage(),accountId);
			else
				accManagerPartition.remove(toServer);
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
	public void debit(int branchId, int accountId, double amount)
			throws InexistentBranchException, InexistentAccountException,
			NegativeAmountException,LockException  {
		if(amount<0)
			throw new NegativeAmountException(amount);
		String xmlString = BankUtility
				.serializeObjectToXMLString(branchId,accountId,amount);
		Buffer requestContent = new ByteArrayBuffer(xmlString);
		ContentExchange exchange = new ContentExchange();
		String toServer = this.toServer(branchId);
		String urlString = toServer + "/DEBIT";
		exchange.setMethod("POST");
		exchange.setURL(urlString);
		exchange.setRequestContent(requestContent);
		try {
			BankUtility.SendAndRecv(this.client, exchange);
		} catch (BankException  e) {
			if (e instanceof NegativeAmountException)
				throw new NegativeAmountException(amount);
			else if (e instanceof InexistentAccountException)
				throw new InexistentAccountException(accountId);
			else if (e instanceof LockException)
				throw new LockException(e.getMessage(),accountId);
			else
				accManagerPartition.remove(toServer);
		}
	}

	@Override
	public void transfer(int branchId, int accountIdOrig, int accountIdDest,
			double amount) throws InexistentBranchException,
			InexistentAccountException, NegativeAmountException,LockException   {
		if(amount<0)
			throw new NegativeAmountException(amount);
		String xmlString = BankUtility
				.serializeObjectToXMLString(branchId,accountIdOrig,accountIdDest,amount);
		Buffer requestContent = new ByteArrayBuffer(xmlString);
		ContentExchange exchange = new ContentExchange();
		String toServer = this.toServer(branchId);
		String urlString = toServer + "/TRANSFER";
		exchange.setMethod("POST");
		exchange.setURL(urlString);
		exchange.setRequestContent(requestContent);
		try {
			BankUtility.SendAndRecv(this.client, exchange);
		} catch (BankException  e) {
			if (e instanceof NegativeAmountException)
				throw new NegativeAmountException(amount);
			else if (e instanceof InexistentAccountException)
				throw new InexistentAccountException();
			else if (e instanceof LockException)
				throw new LockException(e.getMessage(),((LockException) e).getAccountId());
			else
				accManagerPartition.remove(toServer);
		}
		
	}

	@Override
	public double calculateExposure(int branchId)
			throws InexistentBranchException,LockException   {
		String xmlString = BankUtility
				.serializeObjectToXMLString(branchId);
		Buffer requestContent = new ByteArrayBuffer(xmlString);
		ContentExchange exchange = new ContentExchange();
		String toServer = this.toServer(branchId);
		String urlString = toServer + "/CALCULATE";
		exchange.setMethod("POST");
		exchange.setURL(urlString);
		exchange.setRequestContent(requestContent);
		try {
			BankResult res=BankUtility.SendAndRecv(this.client, exchange);
			return res.getResult();
		} catch (BankException e) {
			if (e instanceof InexistentBranchException)
				throw new InexistentBranchException();
			else if (e instanceof LockException)
				throw new LockException(e.getMessage(),((LockException) e).getAccountId());
			else
				accManagerPartition.remove(toServer);
		}
		return 0;
	}

}
