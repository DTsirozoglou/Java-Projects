package com.acertainbank.exceptions;

/**
 * This Exception is thrown when the branch and account ID
 * with which one of the calls to AccountManager
 * is called is not present in the system.
 */
public class LockException extends BankException {

	private static final long serialVersionUID = 1L;
	private int accountId;
	
	public LockException (String message, int accountId) {
		super(message);
		this.accountId = accountId;
	}

	public LockException (int accountId) {
		super("The account "+accountId+" does not exist");
		this.accountId = accountId;
	}

	public LockException () {
		super("The account does not exist");
	}

	public int getAccountId () {
		return accountId;
	}
}
