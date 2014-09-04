package acertainsupplychain.LocalClient;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.io.Buffer;
import org.eclipse.jetty.io.ByteArrayBuffer;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.acertainsupplychain.ItemQuantity;
import com.acertainsupplychain.OrderStep;
import com.acertainsupplychain.exceptions.InvalidItemException;
import com.acertainsupplychain.exceptions.LockException;
import com.acertainsupplychain.exceptions.OrderProcessingException;
import com.acertainsupplychain.interfaces.ItemSupplier;
import com.acertainsupplychain.interfaces.OrderManager.StepStatus;
import com.acertainsupplychain.interfaces.Supplier;
import com.acertainsupplychain.utils.ClientConstants;
import com.acertainsupplychain.utils.CommunicationUtility;
import com.acertainsupplychain.utils.ServerResponse;
import com.acertainsupplychain.utils.ServerResult;

public class LocalClientHTTPProxy implements ItemSupplier {

	private HttpClient client;
	private final Supplier supplier;

	/**
	 * Initialize the client object
	 */
	public LocalClientHTTPProxy(Supplier sup) throws Exception {
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
		supplier = sup;
	}

	public void stop() {
		try {
			client.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void executeStep(final OrderStep step)
			throws OrderProcessingException {

		if (!(step.getSupplierId() == supplier.getSupplierID()))
			throw new OrderProcessingException("Wrong Supplier");
		step.setFrom("Local Client");
		String xmlString = CommunicationUtility
				.serializeObjectToXMLString(step);
		Buffer requestContent = new ByteArrayBuffer(xmlString);
		ContentExchange exchange = new ContentExchange(true) {
			@Override
			protected void onResponseComplete() throws IOException {
				int status = getResponseStatus();
				if (status == 200) {
					ServerResponse r = new ServerResponse();
					r = (ServerResponse) CommunicationUtility
							.deserializeXMLStringToObject(getResponseContent());
					if (r.getComplete())
						step.setStatus(StepStatus.SUCCESSFUL);
					else if (r.getException() instanceof LockException)
						try {
							executeStep(step);
						} catch (OrderProcessingException e) {
							try {
								throw new OrderProcessingException(
										"Wrong Supplier");
							} catch (OrderProcessingException e1) {
							}
						}
					else
						step.setStatus(StepStatus.FAILED);
				}
			}

			@Override
			protected void onConnectionFailed(Throwable x) {
				step.setStatus(StepStatus.SERVER_DOWN);
			}

			@Override
			protected void onExpire() {
				step.setStatus(StepStatus.SERVER_DOWN);
			}
		};
		String urlString = "http://localhost:" + supplier.getServerOfSupplier()
				+ "/EXECUTE";
		exchange.setMethod("POST");
		exchange.setURL(urlString);
		exchange.setRequestContent(requestContent);
		step.setStatus(StepStatus.REGISTERED);
		try {
			this.client.send(exchange);
		} catch (IOException e) {
		}
	}

	@Override
	public List<ItemQuantity> getOrdersPerItem(Set<Integer> itemIds)
			throws InvalidItemException {

		String xmlString = CommunicationUtility
				.serializeObjectToXMLString(itemIds);
		Buffer requestContent = new ByteArrayBuffer(xmlString);
		ContentExchange exchange = new ContentExchange();
		String urlString = "http://localhost:" + supplier.getServerOfSupplier()
				+ "/GET_ORDERS";
		exchange.setMethod("POST");
		exchange.setURL(urlString);
		exchange.setRequestContent(requestContent);
		ServerResult result = new ServerResult();
		List<ItemQuantity> items = null;
		try {
			result = CommunicationUtility.SendAndRecv(this.client, exchange);
			items = result.getItems();
		} catch (Exception e) {
			if (e instanceof InvalidItemException)
				throw new InvalidItemException(e);
			else if (e instanceof LockException) {
				try {
					items = getOrdersPerItem(itemIds);
				} catch (Exception e1) {
					throw new InvalidItemException(e);
				}
			} else
				System.out.println("Supliers Server is Down!");
		}
		return items;
	}
}
