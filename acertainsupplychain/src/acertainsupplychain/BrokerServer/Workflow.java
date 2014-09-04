package acertainsupplychain.BrokerServer;

import java.util.List;

import com.acertainsupplychain.OrderStep;

public class Workflow {

	private static int autoId = 0;
	private List<OrderStep> steps;
	private int workID;
	
	public Workflow(List<OrderStep> steps){
		
		workID = autoId;
		autoId++;
		this.steps=steps;
	}

	/**
	 * @return the workID
	 */
	public int getWorkID() {
		return workID;
	}

	/**
	 * @return the steps
	 */
	public List<OrderStep> getSteps() {
		return steps;
	}
}
