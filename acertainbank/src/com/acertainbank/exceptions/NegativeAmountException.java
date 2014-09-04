package com.acertainbank.exceptions;

/**
 * This Exception is thrown when the amount with
 * which one of the calls to AccountManager is called
 * is negative.
 */
public class NegativeAmountException extends BankException {

	private static final long serialVersionUID = 1L;
	private double amount;
	
	public NegativeAmountException (String message, double amount) {
		super(message);
		this.amount = amount;
	}

	public NegativeAmountException (double amount) {
		super("The amount "+amount+" is negative");
		this.amount = amount;
	}

	public NegativeAmountException () {
		super("The amount is negative");
	}

	public double getAmount () {
		return amount;
	}
}
