package com.acertainsupplychain.SupplierServer;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.acertainsupplychain.ItemQuantity;
import com.acertainsupplychain.OrderStep;
import com.acertainsupplychain.exceptions.OrderProcessingException;
import com.acertainsupplychain.interfaces.Supplier;
import com.acertainsupplychain.utils.CommunicationUtility;
import com.acertainsupplychain.utils.MessageTag;
import com.acertainsupplychain.utils.ServerResponse;
import com.acertainsupplychain.utils.ServerResult;

/**
 * 
 */
public class ItemSupplierHTTPMessageHandler extends AbstractHandler {

	private Supplier supplier;

	public void setSupplier(Supplier sup) {
		this.supplier = sup;
	}

	@SuppressWarnings("unchecked")
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		MessageTag messageTag;
		String requestURI;
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		requestURI = request.getRequestURI();
		messageTag = CommunicationUtility.convertURItoMessageTag(requestURI);
		if (messageTag == null) {
			System.out.println("Unknown message tag");
		} else {
			switch (messageTag) {
			case EXECUTE:
				String xml = CommunicationUtility
						.extractPOSTDataFromRequest(request);
				OrderStep step = (OrderStep) CommunicationUtility
						.deserializeXMLStringToObject(xml);
				ServerResponse serverResponse = new ServerResponse();
				try {
					supplier.executeStep(step);
					serverResponse.setComplete();
				} catch (OrderProcessingException e) {
					serverResponse.setException(e);
				}
				String resposnseString = CommunicationUtility
						.serializeObjectToXMLString(serverResponse);
				response.getWriter().println(resposnseString);
				break;
			case GET_ORDERS:
				xml = CommunicationUtility.extractPOSTDataFromRequest(request);
				Set<Integer> ids = (Set<Integer>) CommunicationUtility
						.deserializeXMLStringToObject(xml);
				ServerResult result = new ServerResult();
				List<ItemQuantity> items = null;
				try {
					items = supplier.getOrdersPerItem(ids);
					result.setItems(items);
				} catch (OrderProcessingException e) {
					result.setException(e);
				}
				resposnseString = CommunicationUtility
						.serializeObjectToXMLString(result);
				response.getWriter().println(resposnseString);
				break;
			default:
				System.out.println("Unhandled message tag");
				break;
			}
		}
		baseRequest.setHandled(true);
	}
}
