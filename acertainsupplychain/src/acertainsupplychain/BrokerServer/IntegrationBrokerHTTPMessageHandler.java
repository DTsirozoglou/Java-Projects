package acertainsupplychain.BrokerServer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.acertainsupplychain.OrderStep;
import com.acertainsupplychain.exceptions.InvalidWorkflowException;
import com.acertainsupplychain.interfaces.OrderManager.StepStatus;
import com.acertainsupplychain.utils.CommunicationUtility;
import com.acertainsupplychain.utils.MessageTag;
import com.acertainsupplychain.utils.ServerResult;

/**
 * 
 */
public class IntegrationBrokerHTTPMessageHandler extends AbstractHandler {
	
	private IntegrationBrokerHTTPProxy proxy;
	
	public void setProxy(IntegrationBrokerHTTPProxy p) {
		this.proxy = p;
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
			case REGISTER:
				String xml = CommunicationUtility.extractPOSTDataFromRequest(request);
				List<OrderStep> steps = (List<OrderStep>) CommunicationUtility.deserializeXMLStringToObject(xml);
				ServerResult serverResult = new ServerResult();
				int workID = proxy.registerOrderWorkflow(steps);
				serverResult.setResult(workID);   
				String resposnseString = CommunicationUtility.serializeObjectToXMLString(serverResult);
				response.getWriter().println(resposnseString);
				break;
			case STATUS:
				xml = CommunicationUtility.extractPOSTDataFromRequest(request);
				int orderWorkflowId =(int) CommunicationUtility.deserializeXMLStringToObject(xml);
				serverResult = new ServerResult();
				try {
					List<StepStatus> status = proxy.getOrderWorkflowStatus(orderWorkflowId);
					serverResult.setStatus(status);
				} catch (InvalidWorkflowException e) {
					serverResult.setException(e);
				}   
				resposnseString = CommunicationUtility.serializeObjectToXMLString(serverResult);
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
