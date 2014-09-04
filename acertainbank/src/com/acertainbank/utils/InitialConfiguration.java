package com.acertainbank.utils;

/**
 * 
 * WorkloadConfiguration represents the configuration parameters to be used by
 * Workers class for running the workloads
 * 
 */
public class InitialConfiguration {
	private int numOfAccounts = 10000;
	private int numOfBranches = 100;
	private int numOfServers = 20;
	/**
	 * @return the numOfAccounts
	 */
	public int getNumOfAccounts() {
		return numOfAccounts;
	}

	/**
	 * @param numOfAccounts the numOfAccounts to set
	 */
	public void setNumOfAccounts(int numOfAccounts) {
		this.numOfAccounts = numOfAccounts;
	}

	/**
	 * @return the numOfBranches
	 */
	public int getNumOfBranches() {
		return numOfBranches;
	}

	/**
	 * @param numOfBranches the numOfBranches to set
	 */
	public void setNumOfBranches(int numOfBranches) {
		this.numOfBranches = numOfBranches;
	}

	/**
	 * @return the numOfServers
	 */
	public int getNumOfServers() {
		return numOfServers;
	}

	/**
	 * @param numOfServers the numOfServers to set
	 */
	public void setNumOfServers(int numOfServers) {
		this.numOfServers = numOfServers;
	}
}
