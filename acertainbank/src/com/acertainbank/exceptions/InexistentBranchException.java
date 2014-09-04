package com.acertainbank.exceptions;

/**
 * This Exception is thrown when the branch ID
 * with which one of the calls to AccountManager
 * is called is not present in the system.
 */
public class InexistentBranchException extends BankException {

	private static final long serialVersionUID = 1L;
	private int branchId;
	
	public InexistentBranchException (String message, int branchId) {
		super(message);
		this.branchId = branchId;
	}

	public InexistentBranchException (int branchId) {
		super("The branch "+branchId+" does not exist");
		this.branchId = branchId;
	}

	public InexistentBranchException () {
		super("The branch does not exist");
	}

	public int getBranchId () {
		return branchId;
	}
}
