package com.acertainbank.utils;

import com.acertainbank.exceptions.BankException;

/**
 * 
 * Data Structure that we use to communicate objects and error messages from the
 * server to the client.
 * 
 */
public class BankResponse {
	private BankException exception = null;
	private BankResult result = null;

	public BankResponse() {

	}

	public BankResponse(BankException exception,
			BankResult result) {
		this.setException(exception);
		this.setResult(result);
	}

	public BankException getException() {
		return exception;
	}

	public void setException(BankException exception) {
		this.exception = exception;
	}

	public BankResult getResult() {
		return result;
	}

	public void setResult(BankResult result) {
		this.result = result;
	}

}
