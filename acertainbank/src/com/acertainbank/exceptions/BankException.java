package com.acertainbank.exceptions;

/**
 * Exception to signal a Bank error
 */
public class BankException extends Exception {
	private static final long serialVersionUID = 1L;

	public BankException() {
		super();
	}

	public BankException(String message) {
		super(message);
	}

	public BankException(String message, Throwable cause) {
		super(message, cause);
	}

	public BankException(Throwable ex) {
		super(ex);
	}
}
