package exercise08;

public class BankAccount implements Comparable<BankAccount>
{
	private double balance;
	
	/**We create a bankAccount with a given balance
	 * @param bal the balance of the bankAccount
	 */
	public BankAccount(double bal)
	{
		this.balance=bal;
	}
	
	/**The method gets the balance of the bankAccount
	 * @return the balance
	 */
	public double getBalance()
	{
		return this.balance;
	}
	
	/**
	Compares two bank accounts.
	@param other the other BankAccount
	@return 1 if this bank account has a greater balance than the other one,
	-1 if this bank account is has a smaller balance than the other one,
	and 0 if both bank accounts have the same balance
	*/
	
	public int compareTo(BankAccount other)
	{
		if (this.balance >other.balance)
		{
			return 1;
		}
		else if (this.balance < other.balance)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}
