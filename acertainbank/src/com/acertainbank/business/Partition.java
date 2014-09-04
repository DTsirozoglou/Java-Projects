package com.acertainbank.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.acertainbank.exceptions.InexistentAccountException;
import com.acertainbank.exceptions.InexistentBranchException;
import com.acertainbank.exceptions.LockException;
import com.acertainbank.exceptions.NegativeAmountException;
import com.acertainbank.interfaces.AccountManager;
import com.acertainbank.utils.BankResult;

public class Partition implements AccountManager {
	private HashMap<Branch, Set<Account>> partition = new HashMap<Branch, Set<Account>>();
	private CertainBank certainBank;

	/**
	 * @return the certainBank
	 */
	public CertainBank getCertainBank() {
		return certainBank;
	}

	private String serverAddress;

	public Partition(String serverAddress, CertainBank cBank) {
		this.serverAddress = serverAddress;
		this.certainBank = cBank;
	}

	public BankResult info() {
		BankResult result = new BankResult();
		result.setPartitions(certainBank.getAccountManagerPartitions());
		return result;
	}

	/**
	 * @return the partition
	 */
	public HashMap<Branch, Set<Account>> getPartition() {
		return partition;
	}

	/**
	 * @return the serverAddress
	 */
	public String getServerAddress() {
		return serverAddress;
	}

	public void addRecord(Branch br, Set<Account> account) {
		this.partition.put(br, account);
	}

	@Override
	public void credit(int branchId, int accountId, double amount)
			throws InexistentBranchException, InexistentAccountException,
			NegativeAmountException, LockException {
		boolean branchFound = false;
		boolean accountFound = false;
		for (Branch br : partition.keySet()) {
			if (br.getId() == branchId) {
				branchFound = true;
				for (Account acc : partition.get(br)) {
					if (acc.getId() == accountId) {
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (acc.lockForWriting()) {
							acc.setAmount(acc.getAmount() + amount);
							acc.unlockWriter();
							accountFound = true;
							break;
						} else {
							throw new LockException("Cant acquire lock",
									acc.getId());
						}
					}
				}
				if (!accountFound) {
					throw new InexistentAccountException();
				}
			}
			if (accountFound)
				break;
		}
		if (!branchFound)
			throw new InexistentBranchException();
	}

	@Override
	public void debit(int branchId, int accountId, double amount)
			throws InexistentBranchException, InexistentAccountException,
			NegativeAmountException, LockException {
		boolean branchFound = false;
		boolean accountFound = false;
		for (Branch br : partition.keySet()) {
			if (br.getId() == branchId) {
				branchFound = true;
				for (Account acc : partition.get(br)) {
					if (acc.getId() == accountId) {
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (acc.lockForWriting()) {
							double newAmount = acc.getAmount() - amount;
							acc.setAmount(newAmount);
							acc.unlockWriter();
							accountFound = true;
							break;
						} else {
							throw new LockException("Cant acquire lock",
									acc.getId());
						}
					}
				}
				if (!accountFound) {
					throw new InexistentAccountException();
				}
			}
			if (accountFound)
				break;
		}
		if (!branchFound)
			throw new InexistentBranchException();
	}

	@Override
	public void transfer(int branchId, int accountIdOrig, int accountIdDest,
			double amount) throws InexistentBranchException,
			InexistentAccountException, NegativeAmountException, LockException {
		boolean branchFound = false;
		boolean accountFound = false;
		for (Branch br : partition.keySet()) {
			if (br.getId() == branchId) {
				branchFound = true;
				for (Account acc : partition.get(br)) {
					if (acc.getId() == accountIdOrig) {

						for (Account accDest : partition.get(br)) {

							if (accDest.getId() == accountIdDest) {
								try {
									Thread.sleep(5);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								if (acc.lockForWriting()) {
									if (accDest.lockForWriting()) {
										acc.setAmount(acc.getAmount() - amount);
										accDest.setAmount(accDest.getAmount()
												+ amount);
										accDest.unlockWriter();
										acc.unlockWriter();
										accountFound = true;
										break;
									} else {
										acc.unlockWriter();
										throw new LockException(
												"Cant acquire lock",
												acc.getId());
									}
								} else {
									throw new LockException(
											"Cant acquire lock", acc.getId());
								}
							}
						}
						if (accountFound)
							break;
					}
				}
				if (!accountFound) {
					throw new InexistentAccountException();
				}
			}
			if (accountFound)
				break;
		}
		if (!branchFound)
			throw new InexistentBranchException();
	}

	@Override
	public double calculateExposure(int branchId)
			throws InexistentBranchException, LockException {
		boolean branchFound = false;
		double sum = 0;
		List<Account> lockedAccounts = new ArrayList<Account>();
		for (Branch br : partition.keySet()) {
			if (br.getId() == branchId) {
				branchFound = true;
				for (Account acc : br.getAccount()) {
					if (acc.lockForReading()) {
						lockedAccounts.add(acc);
						if (acc.getAmount() < 0)
							sum += acc.getAmount();
					} else {
						for (Account lockedAcc : lockedAccounts)
							lockedAcc.unlockReader();
						throw new LockException("Cant acquire lock",
								acc.getId());
					}
				}
				for (Account acc : br.getAccount()) {
					acc.unlockReader();
				}
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		if (!branchFound)
			throw new InexistentBranchException();
		return sum;
	}
}
