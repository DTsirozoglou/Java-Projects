package acertainsupplychain.WorkLoad;

/**
* 
* WorkloadConfiguration represents the configuration parameters to be used by
* Workers class for running the workloads
* 
*/
public class WorkloadConfiguration {
//	private int warmUpRuns = 20;
	private int numActualRuns = 200;
	private float percentRegisterFlowTask = 15f;
	private float percentGetFlowStatusTask= 80f;
	private float percentExecuteOrderTask= 99f;

	public WorkloadConfiguration(){
		
	}

//	public int getWarmUpRuns() {
//		return warmUpRuns;
//	}

//	public void setWarmUpRuns(int warmUpRuns) {
//		this.warmUpRuns = warmUpRuns;
//	}

	public int getNumActualRuns() {
		return numActualRuns;
	}

	public void setNumActualRuns(int numActualRuns) {
		this.numActualRuns = numActualRuns;
	}


	public float getPercentRegisterFlowTask() {
		return percentRegisterFlowTask;
	}


	public void setPercentRegisterFlowTask(float percentRegisterFlowTask) {
		this.percentRegisterFlowTask = percentRegisterFlowTask;
	}


	public float getPercentGetFlowStatusTask() {
		return percentGetFlowStatusTask;
	}


	public void setPercentGetFlowStatusTask(float percentGetFlowStatusTask) {
		this.percentGetFlowStatusTask = percentGetFlowStatusTask;
	}


	public float getPercentExecuteOrderTask() {
		return percentExecuteOrderTask;
	}


	public void setPercentExecuteOrderTask(float percentExecuteOrderTask) {
		this.percentExecuteOrderTask = percentExecuteOrderTask;
	}


}

