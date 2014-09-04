package com.acertainsupplychain.interfaces;

import java.util.List;

import com.acertainsupplychain.OrderStep;
import com.acertainsupplychain.exceptions.InvalidWorkflowException;
import com.acertainsupplychain.exceptions.OrderProcessingException;

/**
 * The OrderManager interface abstracts an integration broker for a supply chain
 * scenario. An order manager allows clients to submit and track order
 * workflows, which are recorded durably.
 */

public interface OrderManager {

	/**
	 * An enumeration listing possible states of an order step. REGISTERED means
	 * the step has been durably accepted by the order manager, but its
	 * processing is still ongoing. FAILED means that an unrecoverable exception
	 * has occurred in the processing of the order step. SUCCESSFUL means that
	 * the order step has been executed against the item supplier.
	 */
	
	public enum StepStatus {
		JUSTCREATED,REGISTERED,FAILED,SUCCESSFUL,SERVER_DOWN
	}

	/**
	 * Registers an order workflow with the order manager.
	 * 
	 * @param steps
	 *            - the order steps to be executed.
	 * @return the ID of the order workflow.
	 * @throws OrderProcessingException
	 *             - an exception thrown if steps are malformed or another error
	 *             condition occurs (you may specialize exceptions deriving from
	 *             OrderProcessingException if you want).
	 */
	public int registerOrderWorkflow(List<OrderStep> steps)
			throws OrderProcessingException;

	/**
	 * Queries the current state of a given order workflow registered with the
	 * order manager.
	 * 
	 * @param orderWorkflowId
	 *            - the ID of the workflow being queried.
	 * @return the list of states of the multiple steps of the given workflow
	 *         (order matters).
	 * @throw InvalidWorkflowException - if the workflow ID given is not valid.
	 */
	public List<StepStatus> getOrderWorkflowStatus(int orderWorkflowId)
			throws InvalidWorkflowException;

}
