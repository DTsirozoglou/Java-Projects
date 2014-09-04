package acertainsupplychain.WorkLoad;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import acertainsupplychain.Configuration.Initializer;
import acertainsupplychain.Configuration.ServerInitializer;

import java.util.concurrent.Future;

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
		
		Initializer initializationManager = new Initializer(true,true,false);
		ServerInitializer serversUP = new ServerInitializer(initializationManager);
		serversUP.joinThreads();
		int numConcurrentWorkloadThreadsOf1stType =20;
		int numConcurrentWorkloadThreadsOf2ndType =1;
		List<WorkerRunResult> workerRunResults = new ArrayList<WorkerRunResult>();
		List<Future<WorkerRunResult>> runResults = new ArrayList<Future<WorkerRunResult>>();
		ExecutorService exec = Executors.newFixedThreadPool(numConcurrentWorkloadThreadsOf1stType + numConcurrentWorkloadThreadsOf2ndType);

		WorkloadConfiguration config = new WorkloadConfiguration();
		for (int i = 0; i < (numConcurrentWorkloadThreadsOf1stType); i++) {
			Worker workerTask2 = new Worker(config,initializationManager,false,1);
			
			// Keep the futures to wait for the result from the thread
			runResults.add(exec.submit(workerTask2));
		}
//		Thread.sleep(11150);
		for (int i = 0; i < (numConcurrentWorkloadThreadsOf2ndType); i++) {
		Worker workerTask = new Worker(config,initializationManager,true,1);
		
		// Keep the futures to wait for the result from the thread
		runResults.add(exec.submit(workerTask));
		}

		// Get the results from the threads using the futures returned
		for (Future<WorkerRunResult> futureRunResult : runResults) {
			WorkerRunResult runResult = futureRunResult.get();
			workerRunResults.add(runResult);
		}
		exec.shutdownNow(); // shutdown the executor
		reportMetric(workerRunResults,numConcurrentWorkloadThreadsOf2ndType,numConcurrentWorkloadThreadsOf1stType);
	}

	/**
	 * Computes the metrics and prints them
	 * 
	 * @param workerRunResults
	 */
	public static void reportMetric(List<WorkerRunResult> workerRunResults, int numConcurrentWorkloadThreadsOf2ndType,int numConcurrentWorkloadThreadsOf1stType) {
		float throughput=0.0f;
		float latency=0.0f;
		int totalthreads = numConcurrentWorkloadThreadsOf2ndType + numConcurrentWorkloadThreadsOf1stType;
		for (int i=0;i<numConcurrentWorkloadThreadsOf2ndType;i++)
		{
		WorkerRunResult result = workerRunResults.get(totalthreads-1-i); 
		throughput+=(float)result.getSuccessfulInteractions()/(float)result.getTotalRuns();
//                    System.out.println(throughput);
		latency+=result.getElapsedTimeInNanoSecs();
		System.out.println("SuccessfulFrequentInteractionRuns:"+result.getSuccessfulFrequentInteractionRuns());
		System.out.println("TOTALFrequentInteractionRuns:"+result.getTotalFrequentInteractionRuns());
		System.out.println("Total Runs:"+result.getTotalRuns());
		System.out.println("------------------------------------------------------");
		}
		System.out.println("Aggregate Throuput:"+throughput);
		System.out.println("Latency:"+latency/numConcurrentWorkloadThreadsOf2ndType);//workerRunResults.size());
		
	}
}