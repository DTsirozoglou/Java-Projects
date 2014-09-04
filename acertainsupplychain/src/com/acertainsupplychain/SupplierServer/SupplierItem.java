package com.acertainsupplychain.SupplierServer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public  class SupplierItem  {
	/**
	 * The ID of the item requested.
	 */
	private final int itemId;

	/**
	 * The number of items requested.
	 */
	private int quantity;
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	/**
	 * Creates an SupplierItem instance with given item ID and quantity.
	 */
	public SupplierItem(int itemId) {
		this.itemId = itemId;
		setQuantity(0);
		
	}
	
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
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
	
	public boolean lockForWriting(){// throws LockException {
		boolean result = false;
		try {
			result = lock.writeLock().tryLock(200, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
//
			e.printStackTrace();
		}
		return result;
	}

	public boolean lockForReading(){ //throws LockException {
		boolean result = false;
		try {
			result = lock.readLock().tryLock(550, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 return result;
	}

	public void unlockWriter() {
		lock.writeLock().unlock();
	}

	public void unlockReader() {
		lock.readLock().unlock();
	}
}

