package acertainsupplychain.Configuration;

import java.util.ArrayList;
import java.util.List;

import acertainsupplychain.BrokerServer.IntegrationBrokerHTTPServer;

import com.acertainsupplychain.SupplierServer.ItemSupplierHTTPServer;

public class ServerInitializer {
	
	private Thread[] threads;
	private List<IntegrationBrokerHTTPServer> brokerServers;
	
	public List<IntegrationBrokerHTTPServer> getbrokerServers() {
		return brokerServers;
	}

	public ServerInitializer(Initializer initializationManager){
		brokerServers=new ArrayList<IntegrationBrokerHTTPServer>();
		InitialConfiguration ic = new InitialConfiguration();
		threads = new Thread[ic.getNumOfServers()+ic.getNumOfBrokers()-1];
		
		for (int i = 0; i <ic.getNumOfServers(); i++) {
			ItemSupplierHTTPServer server; 
				server= new ItemSupplierHTTPServer(initializationManager.getSuppliers().get(i));
				
			Thread t = new Thread(server);
			threads[i] = t;
			t.start();
		}
		int broker=0;
		for (int i = ic.getNumOfServers()-1; i <threads.length; i++) {
			IntegrationBrokerHTTPServer server; 
				server= new IntegrationBrokerHTTPServer(initializationManager.getBrokers().get(broker));
				brokerServers.add(server);
			broker++;
			Thread t = new Thread(server);
			threads[i] = t;
			t.start();
		}
	}
	public void joinThreads(){
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
