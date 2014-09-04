package com.acertainbank.utils;

import java.util.HashMap;
import java.util.Set;

public class BankResult {
	private double result;
	private HashMap<String, Set<Integer>> partitions;
	
	/**
	 * @return the partitions
	 */
	public HashMap<String, Set<Integer>> getPartitions() {
		return partitions;
	}

	/**
	 * @param partitions the partitions to set
	 */
	public void setPartitions(HashMap<String, Set<Integer>> partitions) {
		this.partitions = partitions;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result= result;
	}
}
