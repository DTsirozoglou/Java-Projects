package com.acertainsupplychain.exceptions;
/**
 */
public class LockException extends OrderProcessingException {

	private static final long serialVersionUID = 1L;
	private int itemID;
	
	public LockException (String message, int itemID) {
		super(message);
		this.setItemID(itemID);
	}

	public LockException () {
		super("The item is in use");
	}

	public int getItemID() {
		return itemID;
	}

	private void setItemID(int itemID) {
		this.itemID = itemID;
	}

}
