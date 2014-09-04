/**
 * 
 */
package com.acertainbank.server;
import org.eclipse.jetty.server.Server;
import com.acertainbank.business.Partition;


/**
 * Starts the bank store HTTP server that the clients will communicate with.
 */
public class BankHTTPServer implements Runnable {
	String address;
	Partition p;
	
	/**
	 * @param args
	 */
	public BankHTTPServer(String address,Partition p) {
		this.address=address;
		this.p=p;
	}
	
	@Override
	public void run()
	{
		BankHTTPMessageHandler handler = new BankHTTPMessageHandler();
		handler.setPartition(p);
		Server server = new Server(Integer.valueOf(address));
		server.setHandler(handler);
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
