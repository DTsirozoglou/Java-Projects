package com.acertainbank.client.workloads;

import com.acertainbank.client.AccountManagerHTTPProxy;
import com.acertainbank.interfaces.AccountManager;


/**
 * 
 * WorkloadConfiguration represents the configuration parameters to be used by
 * Workers class for running the workloads
 * 
 */
public class WorkloadConfiguration {
	private double amount = 5;
	private int warmUpRuns = 100;
	private int numActualRuns = 500;
	private float percentCreditTask = 35f;
	private float percentDebitTask= 70f;
	private float percentTransferTask= 90f;
	private Generator bankGenerator = null;
	AccountManager client;

	public WorkloadConfiguration()
			throws Exception {
		client = new AccountManagerHTTPProxy();
		bankGenerator = new Generator();
		
	}


	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	public int getWarmUpRuns() {
		return warmUpRuns;
	}

	public void setWarmUpRuns(int warmUpRuns) {
		this.warmUpRuns = warmUpRuns;
	}

	public int getNumActualRuns() {
		return numActualRuns;
	}

	public void setNumActualRuns(int numActualRuns) {
		this.numActualRuns = numActualRuns;
	}

	public Generator getBookSetGenerator() {
		return bankGenerator;
	}

	public void setBookSetGenerator(Generator bookSetGenerator) {
		this.bankGenerator = bookSetGenerator;
	}


	public AccountManager getClient() {
		return client;
	}


	public void setClient(AccountManager client) {
		this.client = client;
	}


	public float getPercentCreditTask() {
		return percentCreditTask;
	}


	public void setPercentCreditTask(float percentCreditTask) {
		this.percentCreditTask = percentCreditTask;
	}


	public float getPercentDebitTask() {
		return percentDebitTask;
	}


	public void setPercentDebitTask(float percentDebitTask) {
		this.percentDebitTask = percentDebitTask;
	}


	public float getPercentTransferTask() {
		return percentTransferTask;
	}


	public void setPercentTransferTask(float percentTransferTask) {
		this.percentTransferTask = percentTransferTask;
	}

}
