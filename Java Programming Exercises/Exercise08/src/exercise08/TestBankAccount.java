package exercise08;

import java.util.ArrayList; 
import java.util.Collections;

public class TestBankAccount 
{
	public static void main(String[] args)
	{
		// Put bank accounts into a list 
		ArrayList<BankAccount> list = new ArrayList<BankAccount>(); 
		
		// We create five BankAccounts
		BankAccount ba1 = new BankAccount (1500);
		BankAccount ba2 = new BankAccount (1200);
		BankAccount ba3 = new BankAccount (1240);
		BankAccount ba4 = new BankAccount (2340);
		BankAccount ba5 = new BankAccount (1800);
		
		//We add them to our bankAccount list
		list.add(ba1); 
		list.add(ba2); 
		list.add(ba3); 
		list.add(ba4);
		list.add(ba5);
		
		// Call the library sort method
		Collections.sort(list);
		
		// Print out the sorted list 
		for (int i = 0; i < list.size(); i++) 
		{
		BankAccount b = list.get(i);
		System.out.println(b.getBalance()); 
		}
	}
}
