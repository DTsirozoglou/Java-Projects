package acertainsupplychain.WorkLoad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

import com.acertainsupplychain.OrderStep;
import com.acertainsupplychain.Globalclient.GlobalClientHTTPProxy;
import com.acertainsupplychain.exceptions.InvalidItemException;
import com.acertainsupplychain.exceptions.InvalidWorkflowException;
import com.acertainsupplychain.exceptions.LockException;
import com.acertainsupplychain.exceptions.OrderProcessingException;
import com.acertainsupplychain.interfaces.ItemSupplier;
import com.acertainsupplychain.interfaces.OrderManager;
import com.acertainsupplychain.interfaces.Supplier;

import acertainsupplychain.Configuration.Initializer;
import acertainsupplychain.LocalClient.LocalClientHTTPProxy;

/**
 * 
 * Worker represents the workload runner which runs the workloads with
 * parameters using WorkloadConfiguration and then reports the results
 * 
 */
public class Worker implements Callable<WorkerRunResult> {

	private WorkloadConfiguration configuration = null;
	private int numSuccessfulFrequentInteraction = 0;
	private int numTotalFrequentInteraction = 0;
	private Initializer initializationManager;
	private OrderManager globalClient;
	private List <ItemSupplier> localClient;
	private List<Integer> flowsCreated;
	private boolean timedTask;

	public Worker(WorkloadConfiguration config,Initializer initialManager,boolean timedTask,int numLocalClients) throws Exception {
		configuration = config;
		initializationManager = initialManager;
		localClient = new ArrayList<ItemSupplier>();
		Supplier localSupplier = initializationManager.getSuppliers().get(0);
		for (int i=0;i<numLocalClients;i++)
			localClient.add(new LocalClientHTTPProxy(localSupplier));			
		globalClient = new GlobalClientHTTPProxy();
		flowsCreated = new ArrayList<Integer>();
		this.timedTask =timedTask;
		}

	/**
	 * Run the appropriate interaction while trying to maintain the configured
	 * distributions
	 * 
	 * Updates the counts of total runs and successful runs for clients
	 * interaction
	 * 
	 * @param chooseInteraction
	 * @return
	 */
	private boolean runInteraction(float chooseInteraction) {
		try {
			 if (chooseInteraction < configuration.getPercentRegisterFlowTask()) {
				numTotalFrequentInteraction++;
				runRegisterFlowTask();
				numSuccessfulFrequentInteraction++;
			} else if (chooseInteraction < configuration.getPercentGetFlowStatusTask()) {
				numTotalFrequentInteraction++;
				if (flowsCreated.isEmpty()){
					runRegisterFlowTask();
				}else GetFlowStatusTask();
				numSuccessfulFrequentInteraction++;
			} else if (chooseInteraction < configuration.getPercentExecuteOrderTask()) {
				numTotalFrequentInteraction++;
				ExecuteOrderTask();
				numSuccessfulFrequentInteraction++;
			} else {
				numTotalFrequentInteraction++;
				runGetOrderTask();
				numSuccessfulFrequentInteraction++;
			}
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * Run the workloads trying to respect the distributions of the interactions
	 * and return result in the end
	 */
	public WorkerRunResult call() throws Exception {
		int count = 1;
		long startTimeInNanoSecs = 0;
		long endTimeInNanoSecs = 0;
		int successfulInteractions = 0;
		long timeForRunsInNanoSecs = 0;

		Random rand = new Random();
		float chooseInteraction;

//		 Perform the warmup runs
//		while (count++ <= configuration.getWarmUpRuns()) {
//			chooseInteraction = rand.nextFloat() * 100f;
//			runInteraction(chooseInteraction);
//		}

		
		if (!this.timedTask){
			count = 1;
			// Perform the actual runs
			while (count++ <= configuration.getNumActualRuns()) {
				chooseInteraction = rand.nextFloat() * 99f;
				if (runInteraction(chooseInteraction)) {
					successfulInteractions++;
				}
			}
		}
		
		else{
			count = 1;
			numTotalFrequentInteraction = 0;
			numSuccessfulFrequentInteraction = 0;
	
			// Perform the actual runs
			startTimeInNanoSecs = System.nanoTime();
			while (count++ <= 50) { 
				chooseInteraction = 100f;
				if (runInteraction(chooseInteraction)) {
					successfulInteractions++;
				}
			}
			endTimeInNanoSecs = System.nanoTime();
			timeForRunsInNanoSecs += (endTimeInNanoSecs - startTimeInNanoSecs);
		}
		
		return new WorkerRunResult(successfulInteractions,
				timeForRunsInNanoSecs, 50,
				numSuccessfulFrequentInteraction,
				numTotalFrequentInteraction);
	}

	/**
	 * @throws OrderProcessingException 
	 * 
	 */
	private void runRegisterFlowTask() throws Exception {
		
		Generator stepGenerator = new Generator();
		List <OrderStep> order = stepGenerator.generateSteps(initializationManager);
		int flowID;
		try {
			flowID = globalClient.registerOrderWorkflow(order);
			this.flowsCreated.add(flowID);
		} catch (OrderProcessingException e) {
		
			throw new OrderProcessingException(e);
		}

	}

	/**
	 * 
	 * @throws InvalidWorkflowException 
	 */
	private void GetFlowStatusTask() throws Exception {

			Generator stepGenerator = new Generator();
			int flowId= stepGenerator.generateflowID(this.flowsCreated);
			try {
				globalClient.getOrderWorkflowStatus(flowId);
			} catch (InvalidWorkflowException e) {
				throw new InvalidWorkflowException(e);
			}
	}

	/**
	 * @throws OrderProcessingException 
	 * 
	 */
	private void ExecuteOrderTask() throws OrderProcessingException{

		Generator stepGenerator = new Generator();
		OrderStep localOrder = stepGenerator.generateStep(initializationManager);
		Random r =new Random(System.currentTimeMillis());
		int server = r.nextInt(localClient.size());
		try {
			localClient.get(server).executeStep(localOrder);
		} catch (OrderProcessingException e) {
			throw new OrderProcessingException(e);
		}
	}

	private void runGetOrderTask() throws Exception  {
		Generator stepGenerator = new Generator();
		Set<Integer> itemsID= stepGenerator.generateOrderToGet(initializationManager);
		try {
			localClient.get(0).getOrdersPerItem(itemsID);
		} catch (InvalidItemException | LockException e) {
			throw new Exception(e);
		}
	}
}
