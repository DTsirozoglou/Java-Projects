package acertainsupplychain.Configuration;

/**
 * 
 * WorkloadConfiguration represents the configuration parameters to be used by
 * Workers class for running the workloads
 * 
 */
public class InitialConfiguration {
	private int numOfItems = 500;
	private int numOfSteps = 50;
	private int numOfServers = 1;
	private int numOfBrokers = 4;
	
	/**
	 * @return the numOfItems
	 */
	public int getNumOfItems() {
		return numOfItems;
	}

	/**
	 * @param numOfItems the numOfItems to set
	 */
	public void setNumOfItems(int numOfItems) {
		this.numOfItems = numOfItems;
	}

	/**
	 * @return the numOfSteps
	 */
	public int getNumOfSteps() {
		return numOfSteps;
	}

	/**
	 * @param numOfSteps the numOfSteps to set
	 */
	public void setNumOfSteps(int numOfSteps) {
		this.numOfSteps = numOfSteps;
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

	public int getNumOfBrokers() {
		return numOfBrokers;
	}

	public void setNumOfBrokers(int numOfBrokers) {
		this.numOfBrokers = numOfBrokers;
	}
}
