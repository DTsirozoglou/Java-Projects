package com.acertainbank.client.workloads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.acertainbank.business.CertainBank;

/**
 * 
 * CertainWorkload class runs the workloads by different workers concurrently.
 * It configures the environment for the workers using WorkloadConfiguration
 * objects and reports the metrics
 * 
 */
public class CertainWorkload {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		boolean synched=false;
//		boolean synched=true;
		CertainBank b=new CertainBank(synched);
		int numConcurrentWorkloadThreads = 10;
		List<WorkerRunResult> workerRunResults = new ArrayList<WorkerRunResult>();
		List<Future<WorkerRunResult>> runResults = new ArrayList<Future<WorkerRunResult>>();
		ExecutorService exec = Executors
				.newFixedThreadPool(numConcurrentWorkloadThreads);

		for (int i = 0; i < numConcurrentWorkloadThreads; i++) {
			WorkloadConfiguration config = new WorkloadConfiguration();
			Worker workerTask = new Worker(config,b.getBranches());
			// Keep the futures to wait for the result from the thread
			runResults.add(exec.submit(workerTask));
		}

		// Get the results from the threads using the futures returned
		for (Future<WorkerRunResult> futureRunResult : runResults) {
			WorkerRunResult runResult = futureRunResult.get();
			workerRunResults.add(runResult);
		}
		exec.shutdownNow(); // shutdown the executor
		reportMetric(workerRunResults);
                
	}

	/**
	 * Computes the metrics and prints them
	 * 
	 * @param workerRunResults
	 */
	public static void reportMetric(List<WorkerRunResult> workerRunResults) {
		float throughput=0.0f;
		float latency=0.0f;
		for(WorkerRunResult result:workerRunResults)
		{
			throughput+=(float)result.getSuccessfulInteractions()/(float)result.getTotalRuns();
                        System.out.println(throughput);
			latency+=result.getElapsedTimeInNanoSecs();
			System.out.println("SuccessfulFrequentBankInteractionRuns:"+result.getSuccessfulFrequentBankInteractionRuns());
			System.out.println("TOTALFrequentBankInteractionRuns:"+result.getTotalFrequentBankInteractionRuns());
			System.out.println("Total Runs:"+result.getTotalRuns());
			System.out.println("------------------------------------------------------");
		}
		System.out.println("Aggregate Throuput:"+throughput);
		System.out.println("Latency:"+latency/workerRunResults.size());
		
		
	}
}
