/**
 * 
 */
package acertainsupplychain.BrokerServer;

import org.eclipse.jetty.server.Server;

/**
 * Starts the bank store HTTP server that the clients will communicate with.
 */
public class IntegrationBrokerHTTPServer implements Runnable {
	private IntegrationBrokerHTTPProxy proxy;
	int adress;
	private Server server;

	/**
	 * @param args
	 */
	public IntegrationBrokerHTTPServer(int serverAdress) {

		try {
			this.proxy = new IntegrationBrokerHTTPProxy(serverAdress);
		} catch (Exception e) {
		}
		this.adress = serverAdress;
	}

	@Override
	public void run() {
		IntegrationBrokerHTTPMessageHandler handler = new IntegrationBrokerHTTPMessageHandler();
		handler.setProxy(this.proxy);
		server = new Server(adress);
		server.setHandler(handler);
		try {
			server.start();
		} catch (Exception e) {
		}
	}

	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
		}
	}

}
