package com.acertainbank.business;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.TimeUnit;

public class Account {

	private double amount;
	private int id;
	private ReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public Account(double amount, int id) {
		this.amount = amount;
		this.id = id;
	}

	// tries to lock the account for writing with timeout 500ms
	public boolean lockForWriting(){// throws LockException {
		boolean result = false;
		try {
			result = lock.writeLock().tryLock(500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		return result;
	}

	// tries to lock the account for reading with timeout 500ms
	public boolean lockForReading(){ //throws LockException {
		boolean result = false;
		try {
			result = lock.readLock().tryLock(500, TimeUnit.MILLISECONDS);
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
