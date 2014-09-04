/**
 A VendingMachine has a number of cans and tokens that can be changed 
 */

public class VendingMachine {
	
	private int cans; 
	private int tokens;
	
	/**
	 Constructs a VendingMachine with zero tokens and 10 cans 
	 */
	
	public VendingMachine() {
		
		cans 	= 10;
		tokens 	= 0;
		
	}
	
	/**
	 Constructs a VendingMachine with the given number of cans 
	 @newCans the new number of cans in the VendingMachine
	 */
	
	public VendingMachine(int newCans) {
		
		cans 	= newCans;
		tokens 	= 0;
		
	}
	
	/**
	Increases the number of the cans by plusCans
    @param plusCans number of cans to add
	*/
	
	public void fillUp(int plusCans) {
		
		cans = cans + plusCans;
		
	}
	
	/**
	Increases the number of the tokens by one and decrease the cans by one
	*/
	
	public void insertToken() {
		
		cans = cans - 1;
		tokens = tokens + 1;
		
	}	
	
	/**
	 The method gets the number of the cans in the Vending Machine
	 @return the number of the cans
	 */
	
	public int getCanCount() {
		
		return cans;
		
	}
	
	/**
	 The method gets the number of the tokens inserted in the Vending Machine
	 @return the number of the tokens
	 */
	
	public int getTokenCount() {
		
		return tokens;
		
	}
	
}