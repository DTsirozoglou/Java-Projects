package com.acertainsupplychain;

import java.util.List;

import com.acertainsupplychain.interfaces.OrderManager.StepStatus;

/**
 * An OrderStep instance contains a quantity ordered against specific items, all
 * managed by a specific item supplier.
 */
public final class OrderStep {

	/**
	 * The ID of the item supplier that manages the items.
	 */
	private final int supplierId;
	private StepStatus status;

	/**
	 * The list of items ordered and their quantities.
	 */
	private final List<ItemQuantity> items;
	private static int autoId = 0;
	private int stepID;
	private String from;

	/**
	 * Constructs an OrderStep instance with given supplier, item, and quantity.
	 */
	public  OrderStep(int supplierId, List<ItemQuantity> items) {
		this.supplierId = supplierId;
		this.items = items;
		status = StepStatus.JUSTCREATED;
		autoId++;
		stepID = autoId;
	}

	/**
	 * @return the supplierId
	 */
	public synchronized int getSupplierId() {
		return supplierId;
	}

	/**
	 * @return the items
	 */
	public synchronized List<ItemQuantity> getItems() {
		return items;
	}

	/**
	 * @return the status
	 */
	public synchronized StepStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public synchronized void setStatus(StepStatus status) {
		this.status = status;
		
	}
	
	/**
	 * @return the stepID
	 */
	public synchronized int getStepID() {
		return stepID;
	}

	public synchronized  String getFrom() {
		return from;
	}

	public synchronized void setFrom(String from) {
		this.from = from;
	}

	public synchronized void display(){
		System.out.println("To supplier : "+this.supplierId);
		for (ItemQuantity item : getItems())
			System.out.println("Item : " + item.getItemId() +" Amount : " + item.getQuantity());
		
	}

}
