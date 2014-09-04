/**
 * 
 */
package com.acertainbank.client.workloads;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

import com.acertainbank.business.Branch;
import com.acertainbank.exceptions.BankException;
import com.acertainbank.exceptions.InexistentBranchException;
import com.acertainbank.interfaces.AccountManager;
import com.acertainbank.utils.InitialConfiguration;

/**
 * 
 * Worker represents the workload runner which runs the workloads with
 * parameters using WorkloadConfiguration and then reports the results
 * 
 */
public class Worker implements Callable<WorkerRunResult> {

	private WorkloadConfiguration configuration = null;
	private Set<Branch> branches;
	private int numSuccessfulFrequentBankInteraction = 0;
	private int numTotalFrequentBankInteraction = 0;

	public Worker(WorkloadConfiguration config, Set<Branch> branches) {
		configuration = config;
		this.branches = branches;
	}

	/**
	 * Run the appropriate interaction while trying to maintain the configured
	 * distributions
	 * 
	 * Updates the counts of total runs and successful runs for customer
	 * interaction
	 * 
	 * @param chooseInteraction
	 * @return
	 */
	private boolean runInteraction(float chooseInteraction) {
		try {
			if (chooseInteraction < configuration.getPercentCreditTask()) {
				numTotalFrequentBankInteraction++;
				runCreditTask();
				numSuccessfulFrequentBankInteraction++;
			} else if (chooseInteraction < configuration.getPercentDebitTask()) {
				numTotalFrequentBankInteraction++;
				runDebitTask();
				numSuccessfulFrequentBankInteraction++;
			} else if (chooseInteraction < configuration
					.getPercentTransferTask()) {
				numTotalFrequentBankInteraction++;
				runTransferTask();
				numSuccessfulFrequentBankInteraction++;
			} else {
				numTotalFrequentBankInteraction++;
				runExposureTask();
				numSuccessfulFrequentBankInteraction++;
			}
		} catch (BankException ex) {
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
		while (count++ <= configuration.getWarmUpRuns()) {
			chooseInteraction = rand.nextFloat() * 100f;
			runInteraction(chooseInteraction);
		}
//
		count = 1;
//		numTotalFrequentBankInteraction = 0;
//		numSuccessfulFrequentBankInteraction = 0;

		// Perform the actual runs
		startTimeInNanoSecs = System.nanoTime();
		while (count++ <= configuration.getNumActualRuns()) {
			chooseInteraction = rand.nextFloat() * 100f;
			if (runInteraction(chooseInteraction)) {
				successfulInteractions++;
			}
		}
		endTimeInNanoSecs = System.nanoTime();
		timeForRunsInNanoSecs += (endTimeInNanoSecs - startTimeInNanoSecs);
		return new WorkerRunResult(successfulInteractions,
				timeForRunsInNanoSecs, configuration.getNumActualRuns(),
				numSuccessfulFrequentBankInteraction,
				numTotalFrequentBankInteraction);
	}

	/**
	 * Runs the new stock acquisition interaction
	 * 
	 * @throws BookStoreException
	 */
	private void runCreditTask() throws BankException {
		AccountManager manager = configuration.getClient();
		InitialConfiguration ic = new InitialConfiguration();
		int randomBranches = configuration.getBookSetGenerator()
				.getRandomBranch(ic.getNumOfBranches());
		
		Branch validBranch = null;
		for (Branch br : branches) {
			if (br.getId() == randomBranches)
				validBranch = br;
		}
		if (validBranch == null)
			throw new InexistentBranchException();
		int randomAccount = configuration.getBookSetGenerator()
				.getRandomAccount(validBranch);
		double randomAmount = configuration.getBookSetGenerator()
				.getRandomAmount();
		manager.credit(randomBranches, randomAccount, randomAmount);
	}

	/**
	 * Runs the stock replenishment interaction
	 * 
	 * @throws Exception
	 */
	private void runDebitTask() throws BankException {
		AccountManager manager = configuration.getClient();
		InitialConfiguration ic = new InitialConfiguration();
		int randomBranches = configuration.getBookSetGenerator()
				.getRandomBranch(ic.getNumOfBranches());
		Branch validBranch = null;
		for (Branch br : branches) {
			if (br.getId() == randomBranches)
				validBranch = br;
		}
		if (validBranch == null)
			throw new InexistentBranchException();
		int randomAccount = configuration.getBookSetGenerator()
				.getRandomAccount(validBranch);
		double randomAmount = configuration.getBookSetGenerator()
				.getRandomAmount();
		manager.debit(randomBranches, randomAccount, randomAmount);
	}

	/**
	 * Runs the customer interaction
	 * 
	 * @throws BookStoreException
	 */
	private void runTransferTask() throws BankException {
		AccountManager manager = configuration.getClient();
		InitialConfiguration ic = new InitialConfiguration();
		int randomBranches = configuration.getBookSetGenerator()
				.getRandomBranch(ic.getNumOfBranches());
		Branch validBranch = null;
		for (Branch br : branches) {
			if (br.getId() == randomBranches)
				validBranch = br;
		}
		if (validBranch == null)
			throw new InexistentBranchException();
		int randomAccount1 = configuration.getBookSetGenerator()
				.getRandomAccount(validBranch);
		int randomAccount2 = configuration.getBookSetGenerator()
				.getRandomAccount(validBranch);
		double randomAmount = configuration.getBookSetGenerator()
				.getRandomAmount();
		manager.transfer(randomBranches, randomAccount1, randomAccount2,
				randomAmount);
	}

	private void runExposureTask() throws BankException {
		AccountManager manager = configuration.getClient();
		InitialConfiguration ic = new InitialConfiguration();
		int randomBranches = configuration.getBookSetGenerator()
				.getRandomBranch(ic.getNumOfBranches());
		manager.calculateExposure(randomBranches);
	}
}
