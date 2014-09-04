package com.acertainbank.business;

import com.acertainbank.exceptions.InexistentAccountException;
import com.acertainbank.exceptions.InexistentBranchException;
import com.acertainbank.exceptions.LockException;
import com.acertainbank.exceptions.NegativeAmountException;
import com.acertainbank.interfaces.AccountManager;

public class SynchronizedPartition extends Partition implements AccountManager {

	public SynchronizedPartition(String serverAddress, CertainBank cBank) {
		super(serverAddress, cBank);
	}

	@Override
	public synchronized void credit(int branchId, int accountId, double amount)
			throws InexistentBranchException, InexistentAccountException,
			NegativeAmountException, LockException {
		boolean branchFound = false;
		boolean accountFound = false;
		for (Branch br : super.getPartition().keySet()) {
			if (br.getId() == branchId) {
				branchFound = true;
				for (Account acc : super.getPartition().get(br)) {
					if (acc.getId() == accountId) {
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						acc.setAmount(acc.getAmount() + amount);
						accountFound = true;
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
	public synchronized void debit(int branchId, int accountId, double amount)
			throws InexistentBranchException, InexistentAccountException,
			NegativeAmountException, LockException {
		boolean branchFound = false;
		boolean accountFound = false;
		for (Branch br : super.getPartition().keySet()) {
			if (br.getId() == branchId) {
				branchFound = true;
				for (Account acc : super.getPartition().get(br)) {
					if (acc.getId() == accountId) {
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						double newAmount = acc.getAmount() - amount;
						acc.setAmount(newAmount);
						accountFound = true;
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
	public synchronized void transfer(int branchId, int accountIdOrig,
			int accountIdDest, double amount) throws InexistentBranchException,
			InexistentAccountException, NegativeAmountException, LockException {
		boolean branchFound = false;
		boolean accountFound = false;
		for (Branch br : super.getPartition().keySet()) {
			if (br.getId() == branchId) {
				branchFound = true;
				for (Account acc : super.getPartition().get(br)) {
					if (acc.getId() == accountIdOrig) {
						for (Account accDest : super.getPartition().get(br)) {
							if (accDest.getId() == accountIdDest) {
								try {
									Thread.sleep(5);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								acc.setAmount(acc.getAmount() - amount);
								accDest.setAmount(accDest.getAmount() + amount);
								accountFound = true;
								break;
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
	public synchronized double calculateExposure(int branchId)
			throws InexistentBranchException, LockException {
		boolean branchFound = false;
		double sum = 0;
		for (Branch br : super.getPartition().keySet()) {
			if (br.getId() == branchId) {
				branchFound = true;
				for (Account acc : br.getAccount()) {
					if (acc.getAmount() < 0)
						sum += acc.getAmount();
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
