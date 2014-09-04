package com.acertainsupplychain.utils;

import com.acertainsupplychain.exceptions.OrderProcessingException;

/**
 * 
 * Data Structure that we use to communicate objects and error messages from the
 * server to the client.
 * 
 */
public class ServerResponse {
	private OrderProcessingException exception = null;
	private boolean complete = false;

	public ServerResponse() {

	}

//	public ServerResponse(OrderProcessingException exception){
//		this.setException(exception);
//	}

	public OrderProcessingException getException() {
		return exception;
	}

	public void setException(OrderProcessingException exception) {
		this.exception = exception;
	}

	public  void setComplete() {
		this.complete=true;
	}

	public  boolean getComplete() {
		return this.complete;
	}

}
