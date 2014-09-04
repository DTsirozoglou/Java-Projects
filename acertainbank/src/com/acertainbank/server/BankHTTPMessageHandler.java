package com.acertainbank.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.acertainbank.business.Partition;
import com.acertainbank.exceptions.InexistentAccountException;
import com.acertainbank.exceptions.InexistentBranchException;
import com.acertainbank.exceptions.LockException;
import com.acertainbank.exceptions.NegativeAmountException;
import com.acertainbank.utils.BankMessageTag;
import com.acertainbank.utils.BankResponse;
import com.acertainbank.utils.BankResult;
import com.acertainbank.utils.BankUtility;

/**
 * BankHTTPMessageHandler implements the message handler class which is
 * invoked to handle messages received by the BankHTTPServer.  
 * 
 */
public class BankHTTPMessageHandler extends AbstractHandler {
	private Partition p;
	public void setPartition(Partition p) {
		this.p = p;
	}
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		BankMessageTag messageTag;
		String requestURI;
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		requestURI = request.getRequestURI();
		messageTag = BankUtility.convertURItoMessageTag(requestURI);
		if (messageTag == null) {
			System.out.println("Unknown message tag");
		} else {
			switch (messageTag) {
			case INFO:
				BankResponse bankresponse = new BankResponse();

				bankresponse.setResult(p.info());
				String resposnseString = BankUtility
						.serializeObjectToXMLString(bankresponse);
				response.getWriter().println(resposnseString);
				break;
			case CREDIT:
				String xml = BankUtility.extractPOSTDataFromRequest(request);
				List<Object> objList =BankUtility.deserializeXMLStringToObjects(xml);
				int branchId = (int) objList.get(0);
				int accountId = (int) objList.get(1);
				double amount = (double) objList.get(2);
    			bankresponse = new BankResponse();
    			try {
					p.credit(branchId, accountId, amount);
				} catch (InexistentBranchException | InexistentAccountException
						| NegativeAmountException |LockException e) {
					bankresponse.setException(e);
				}
                resposnseString = BankUtility.serializeObjectToXMLString(bankresponse);
				response.getWriter().println(resposnseString);
				break;
			case DEBIT:
				xml = BankUtility.extractPOSTDataFromRequest(request);
				objList =BankUtility.deserializeXMLStringToObjects(xml);
				branchId = (int) objList.get(0);
				accountId = (int) objList.get(1);
				amount = (double) objList.get(2);
    			bankresponse = new BankResponse();
    			try {
					p.debit(branchId, accountId, amount);
				} catch (InexistentBranchException | InexistentAccountException
						| NegativeAmountException |LockException  e) {
					bankresponse.setException(e);
				}
                resposnseString = BankUtility.serializeObjectToXMLString(bankresponse);
				response.getWriter().println(resposnseString);
				break;
			case TRANSFER:
				xml = BankUtility.extractPOSTDataFromRequest(request);
				objList =BankUtility.deserializeXMLStringToObjects(xml);
				branchId = (int) objList.get(0);
				int accountIdOrig = (int) objList.get(1);
				int accountIdDest = (int) objList.get(2);
				amount = (double) objList.get(3);
    			bankresponse = new BankResponse();
    			try {
					p.transfer(branchId, accountIdOrig,accountIdDest ,amount);
				} catch (InexistentBranchException | InexistentAccountException
						| NegativeAmountException |LockException e) {
					bankresponse.setException(e);
				}
                resposnseString = BankUtility.serializeObjectToXMLString(bankresponse);
				response.getWriter().println(resposnseString);
				break;
			case CALCULATE:
				xml = BankUtility.extractPOSTDataFromRequest(request);
				Object obj=BankUtility.deserializeXMLStringToObject(xml);
				branchId = (int) obj;
    			bankresponse = new BankResponse();
    			try {
    				BankResult res=new BankResult();
    				res.setResult(p.calculateExposure(branchId));
					bankresponse.setResult(res);
				} catch (InexistentBranchException|LockException  e) {
					bankresponse.setException(e);
				}
                resposnseString = BankUtility.serializeObjectToXMLString(bankresponse);
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
