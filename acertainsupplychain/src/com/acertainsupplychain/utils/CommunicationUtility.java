/**
 * 
 */
package com.acertainsupplychain.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;

import com.acertainsupplychain.exceptions.InvalidItemException;
import com.acertainsupplychain.exceptions.LockException;
import com.acertainsupplychain.exceptions.OrderProcessingException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * BankUtility implements utility methods used by servers and clients.
 * 
 */
public final class CommunicationUtility {


	public static MessageTag convertURItoMessageTag(String requestURI) {

		try {
			MessageTag messageTag = MessageTag
					.valueOf(requestURI.substring(1).toUpperCase());
			return messageTag;
		} catch (IllegalArgumentException ex) {
			; // Enum type matching failed so non supported message
		} catch (NullPointerException ex) {
			; // RequestURI was empty
		}
		return null;
	}


	/**
	 * Serializes an object to an xml string
	 * 
	 * @param object
	 * @return
	 */
	public static String serializeObjectToXMLString(Object object) {
		String xmlString;
		XStream xmlStream = new XStream(new StaxDriver());
		xmlString = xmlStream.toXML(object);
		return xmlString;
	}
	
	public static Object deserializeXMLStringToObject(String xmlObject) {
		Object dataObject = null;
		XStream xmlStream = new XStream(new StaxDriver());
		dataObject = xmlStream.fromXML(xmlObject);
		return dataObject;
	}
	

	public static ServerResult SendAndRecv(HttpClient client,
			ContentExchange exchange) throws Exception{
		int exchangeState;
		try {
			client.send(exchange);
		} catch (Exception ex) {
			throw new Exception(
					ClientConstants.strERR_CLIENT_REQUEST_SENDING, ex);
		}

		try {
			exchangeState = exchange.waitForDone(); // block until the response
													// is available
		} catch (InterruptedException ex) {
			throw new Exception(
					ClientConstants.strERR_CLIENT_REQUEST_SENDING, ex);
		}

		if (exchangeState == HttpExchange.STATUS_COMPLETED) {
			try {
				ServerResult result = (ServerResult) CommunicationUtility.
						deserializeXMLStringToObject(exchange.getResponseContent());
				OrderProcessingException ex = result.getException();
				if (ex != null) {
					if (ex instanceof InvalidItemException)
						throw new InvalidItemException(ex);
					else if (ex instanceof LockException)
						throw new LockException();
					else 
						throw new OrderProcessingException(ex.getMessage());
				}
				return result;
				
			} catch (UnsupportedEncodingException ex) {
				throw new Exception(
						ClientConstants.strERR_CLIENT_RESPONSE_DECODING,
						ex);
			}
		} else if (exchangeState == HttpExchange.STATUS_EXCEPTED) {
			throw new Exception(
					ClientConstants.strERR_CLIENT_REQUEST_EXCEPTION);
		} else if (exchangeState == HttpExchange.STATUS_EXPIRED) {
			throw new Exception(
					ClientConstants.strERR_CLIENT_REQUEST_TIMEOUT);
		} else {
			throw new Exception(
					ClientConstants.strERR_CLIENT_UNKNOWN);
			}
	}

	/**
	 * Returns the message of the request as a string
	 * 
	 * @param request
	 * @return xml string
	 * @throws IOException
	 */
	public static String extractPOSTDataFromRequest(HttpServletRequest request)
			throws IOException {
		Reader reader = request.getReader();
		int len = request.getContentLength();

		// Request must be read into a char[] first
		char res[] = new char[len];
		reader.read(res);
		reader.close();
		return new String(res);
	}
}
