package com.acertainsupplychain;

/**
 * Represents an item and an associated quantity.
 */
public final class ItemQuantity {

	/**
	 * The ID of the item requested.
	 */
	private final int itemId;

	/**
	 * The number of items requested.
	 */
	private final int quantity;

	/**
	 * Creates an ItemQuantity instance with given item ID and quantity.
	 */
	public ItemQuantity(int itemId, int quantity) {
		this.itemId = itemId;
		this.quantity = quantity;
	}

	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
}
