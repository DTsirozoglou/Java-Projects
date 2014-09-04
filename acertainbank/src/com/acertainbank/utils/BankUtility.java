/**
 * 
 */
package com.acertainbank.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;

import com.acertainbank.client.BankClientConstants;
import com.acertainbank.exceptions.BankException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * BankUtility implements utility methods used by servers and clients.
 * 
 */
public final class BankUtility {


	public static BankMessageTag convertURItoMessageTag(String requestURI) {

		try {
			BankMessageTag messageTag = BankMessageTag
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
	public static String serializeObjectToXMLString(Object... obj) {
		
		String xmlString="";
		XStream xmlStream = new XStream(new StaxDriver());
		
		for(Object ob:obj)
		{
			xmlString+=xmlStream.toXML(ob)+"AND";
		}
		return xmlString.substring(0,xmlString.lastIndexOf("AND"));
	}
	
	public static Object deserializeXMLStringToObject(String xmlObject) {
		Object dataObject = null;
		XStream xmlStream = new XStream(new StaxDriver());
		dataObject = xmlStream.fromXML(xmlObject);
		return dataObject;
	}
	
	/**
	 * De-serializes an xml string to object
	 * 
	 * @param xmlObject
	 * @return
	 */
	public static List<Object> deserializeXMLStringToObjects(String xmlObject) {
		List<Object> result=new ArrayList<Object>();
		Object dataObject = null;
		XStream xmlStream = new XStream(new StaxDriver());
		String[] tresult = xmlObject.split("AND");
		for (int i=0;i<tresult.length;i++)
		{
			dataObject = xmlStream.fromXML(tresult[i]);
			result.add(dataObject);
		}
		dataObject = xmlStream.fromXML(xmlObject);
		return result;
	}


	/**
	 * Manages the sending of an exchange through the client, waits for the
	 * response and unpacks the response
	 * 
	 * @param client
	 * @param exchange
	 * @return 
	 * @throws BankException
	 */
	public static BankResult SendAndRecv(HttpClient client,
			ContentExchange exchange) throws BankException{
		int exchangeState;
		try {
			client.send(exchange);
		} catch (IOException ex) {
			throw new BankException(
					BankClientConstants.strERR_CLIENT_REQUEST_SENDING, ex);
		}

		try {
			exchangeState = exchange.waitForDone(); // block until the response
													// is available
		} catch (InterruptedException ex) {
			throw new BankException(
					BankClientConstants.strERR_CLIENT_REQUEST_SENDING, ex);
		}

		if (exchangeState == HttpExchange.STATUS_COMPLETED) {
			try {
				BankResponse bankStoreResponse = (BankResponse) BankUtility
						.deserializeXMLStringToObject(exchange
								.getResponseContent().trim());
				BankException ex = bankStoreResponse.getException();
				if (ex != null) {
					throw ex;
				}
				return bankStoreResponse.getResult();
				
			} catch (UnsupportedEncodingException ex) {
				throw new BankException(
						BankClientConstants.strERR_CLIENT_RESPONSE_DECODING,
						ex);
			}
		} else if (exchangeState == HttpExchange.STATUS_EXCEPTED) {
			throw new BankException(
					BankClientConstants.strERR_CLIENT_REQUEST_EXCEPTION);
		} else if (exchangeState == HttpExchange.STATUS_EXPIRED) {
			throw new BankException(
					BankClientConstants.strERR_CLIENT_REQUEST_TIMEOUT);
		} else {
			throw new BankException(
					BankClientConstants.strERR_CLIENT_UNKNOWN);
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
