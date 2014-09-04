package com.acertainbank.client.workloads;

/**
 * 
 * WorkerRunResult class represents the result returned by a worker class after
 * running the workload interactions
 * 
 */
public class WorkerRunResult {
	private int successfulInteractions; // total number of successful interactions
	private int totalRuns; // total number of interactions run 
	private long elapsedTimeInNanoSecs; // total time taken to run all
										// interactions
	private int successfulFrequentBankInteractionRuns; // number of
															// successful
															// frequent bank
															//  interaction
															// runs
	private int totalFrequentBankInteractionRuns; // total number of
														// bank interaction
														// runs

	public WorkerRunResult(int successfulInteractions, long elapsedTimeInNanoSecs,
			int totalRuns, int successfulFrequentBankInteractionRuns,
			int totalFrequentBankInteractionRuns) {
		this.setSuccessfulInteractions(successfulInteractions);
		this.setElapsedTimeInNanoSecs(elapsedTimeInNanoSecs);
		this.setTotalRuns(totalRuns);
		this.setSuccessfulFrequentBankInteractionRuns(successfulFrequentBankInteractionRuns);
		this.setTotalFrequentBankInteractionRuns(totalFrequentBankInteractionRuns);
	}

	public int getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(int totalRuns) {
		this.totalRuns = totalRuns;
	}

	public int getSuccessfulInteractions() {
		return successfulInteractions;
	}

	public void setSuccessfulInteractions(int successfulInteractions) {
		this.successfulInteractions = successfulInteractions;
	}

	public long getElapsedTimeInNanoSecs() {
		return elapsedTimeInNanoSecs;
	}

	public void setElapsedTimeInNanoSecs(long elapsedTimeInNanoSecs) {
		this.elapsedTimeInNanoSecs = elapsedTimeInNanoSecs;
	}

	public int getSuccessfulFrequentBankInteractionRuns() {
		return successfulFrequentBankInteractionRuns;
	}

	public void setSuccessfulFrequentBankInteractionRuns(
			int successfulFrequentBankInteractionRuns) {
		this.successfulFrequentBankInteractionRuns = successfulFrequentBankInteractionRuns;
	}

	public int getTotalFrequentBankInteractionRuns() {
		return totalFrequentBankInteractionRuns;
	}

	public void setTotalFrequentBankInteractionRuns(
			int totalFrequentBookStoreInteractionRuns) {
		this.totalFrequentBankInteractionRuns = totalFrequentBookStoreInteractionRuns;
	}

}
