package com.acertainsupplychain.exceptions;

/**
 * This exception flags that an invalid item ID was given to an item supplier.
 */
@SuppressWarnings("serial")
public class InvalidItemException extends OrderProcessingException {

	/**
	 * Constructor based on Exception constructors.
	 */
	public InvalidItemException() {
		super();
	}

	/**
	 * Constructor based on Exception constructors.
	 */
	public InvalidItemException(String message) {
		super(message);
	}

	/**
	 * Constructor based on Exception constructors.
	 */
	public InvalidItemException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor based on Exception constructors.
	 */
	public InvalidItemException(Throwable ex) {
		super(ex);
	}
	
}
