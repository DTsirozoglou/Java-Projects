package com.acertainsupplychain.exceptions;

/**
 * This exception flags that an invalid workflow ID was given to an order
 * manager.
 */
@SuppressWarnings("serial")
public class InvalidWorkflowException extends OrderProcessingException {

	/**
	 * Constructor based on Exception constructors.
	 */
	public InvalidWorkflowException() {
		super();
	}

	/**
	 * Constructor based on Exception constructors.
	 */
	public InvalidWorkflowException(String message) {
		super(message);
	}

	/**
	 * Constructor based on Exception constructors.
	 */
	public InvalidWorkflowException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor based on Exception constructors.
	 */
	public InvalidWorkflowException(Throwable ex) {
		super(ex);
	}

}
