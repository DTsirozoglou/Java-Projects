package com.acertainsupplychain.utils;

import java.util.List;

import com.acertainsupplychain.ItemQuantity;
import com.acertainsupplychain.exceptions.OrderProcessingException;
import com.acertainsupplychain.interfaces.OrderManager.StepStatus;

public class ServerResult {
	
	private int result;
	private List<StepStatus> status;
	private OrderProcessingException exception = null;
	private List<ItemQuantity> items;

	/**
	 * @return the status
	 */
	public List<StepStatus> getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(List<StepStatus> status) {
		this.status = status;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result= result;
	}

	public OrderProcessingException getException() {
		return exception;
	}

	public void setException(OrderProcessingException exception) {
		this.exception = exception;
	}

	public List<ItemQuantity> getItems() {
		return items;
	}

	public void setItems(List<ItemQuantity> items) {
		this.items = items;
	}
}
