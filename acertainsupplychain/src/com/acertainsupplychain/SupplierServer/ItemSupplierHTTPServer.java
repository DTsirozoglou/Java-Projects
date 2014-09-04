/**
 * 
 */
package com.acertainsupplychain.SupplierServer;
import org.eclipse.jetty.server.Server;

import com.acertainsupplychain.interfaces.Supplier;

public class ItemSupplierHTTPServer implements Runnable {
	Supplier supplier; 
	Server server;
	/**
	 * @param args
	 */
	public ItemSupplierHTTPServer(Supplier supplier) {
		
		this.supplier=supplier;
	}
	
	@Override
	public void run()
	{
		ItemSupplierHTTPMessageHandler handler = new ItemSupplierHTTPMessageHandler();
		handler.setSupplier(supplier);
		server = new Server(Integer.valueOf(supplier.getServerOfSupplier()));
		server.setHandler(handler);
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void stop()
	{
		if(server!=null)
			try {
				server.stop();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
