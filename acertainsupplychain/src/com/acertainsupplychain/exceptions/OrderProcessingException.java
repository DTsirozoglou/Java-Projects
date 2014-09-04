package com.acertainsupplychain.exceptions;

/**
 * Represents an exception encountered during processing of order workflows.
 * This is a root exception class.
 */
@SuppressWarnings("serial")
public class OrderProcessingException extends Exception {

	/**
	 * Constructor based on Exception constructors.
	 */
	public OrderProcessingException() {
		super();
	}

	/**
	 * Constructor based on Exception constructors.
	 */
	public OrderProcessingException(String message) {
		super(message);
	}

	/**
	 * Constructor based on Exception constructors.
	 */
	public OrderProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor based on Exception constructors.
	 */
	public OrderProcessingException(Throwable ex) {
		super(ex);
	}

}
