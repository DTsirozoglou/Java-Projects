package exercise07;

import java.util.ArrayList;

/**
 * An Item  has a unique id, a name, a status , a list of the names that have made reservation for it
 * An Item can be reserved for specific "legalDays" before the one who had it rent got fined.
 * An Item has also the day and month that it reserved, and the date and time that it was brought back
 */

public class Item
{
	private  int id;
	public String name;
	private static int autoId = 0;
	public String status = "Available";
    public ArrayList<String> reservlist = new ArrayList<String>();
    private int legalDays;
    private int dayResrved;
    private int monthResrved;
    private int dayCheckedin;
    private int monthCheckedin;
	
	/**Constructs an Item with a name, an automatic unique id, and the number of the days that this Item is available for rent
	 * @param item the name of the item
	 * @param days the number of days without getting fined
	 */
	public Item(String item, int days)
	{
		autoId++;
		id = autoId;
		name=item;
		legalDays=days;
	}
	
	/** The method adds to the reservation list of the item, the employee who is making the reservation , and sets the date of the item's reservation
	 * @param EmpName is the employee who is making the reservation
	 * @param dayCheckedout is the number of the day that the reservation took place
	 * @param monthCheckedout is the number of the month that the reservation took place
	 */
	public void addreservation(String EmpName,int dayCheckedout, int monthCheckedout)
	{
		if (this.status == "Available")
		{
			if (this.reservlist.contains(EmpName))
			{
				this.dayResrved = dayCheckedout;
				this.monthResrved = monthCheckedout;
				this.status= "Reserved";				
			}
			else 
			{
				this.reservlist.add(EmpName);
				this.dayResrved = dayCheckedout;
				this.monthResrved = monthCheckedout;
				this.status= "Reserved";
			}
		}
		else // if the product is already reserved we just add the employee to the list of this item reservations
		{
			this.reservlist.add(EmpName);
		}
	}
	
	/** The method removes a name from the reservation list of an item, changes the item's status to "Available", 
	 *  and prints to console whether the reservation was legal, and print to whom the stockroom clerk has to notify 
	 * @param EmpName The emplooy's name who is checking in
	 * @param dayCheckin The day that the item is returned
	 * @param monthCheckin The month that the item is returned
	 */
	
	public void removeReservation(String EmpName,int dayCheckin, int monthCheckin)
	{
		this.reservlist.remove(EmpName);
		this.status= "Available";
		this.dayCheckedin = dayCheckin;
		this.monthCheckedin = monthCheckin;
		
		//I find until witch day and month according to the "legal days" of this item, there will be no fine
		int daynotfined = this.dayResrved + this.legalDays;
		int monthnotfined = this.monthResrved;
		
		if (daynotfined >30)
		{
			daynotfined = daynotfined - 30;
			monthnotfined = monthResrved + 1;
		}
		
		// Check if the employee brought the item back on time
		if ((monthCheckin == monthnotfined) && (dayCheckin > daynotfined)) 
		{
			System.out.println("Send e-mail to " + EmpName + " You have been fined!!");
		}
		if (monthCheckin < monthnotfined)  
		{
			System.out.println("Send e-mail to " + EmpName + " You have been fined!!");
		}
		
		// Notifies the next Employee in the reservation list, if any!
		if (!this.reservlist.isEmpty())
		{
			System.out.println("Send e-mail to " + this.reservlist.get(0) + " the item " + this.name + " is now Available");
		}
	}
		
	/** A Method that returns the list of the reservation for an item 
	 * @return the list of the item's reservations
	 */
	
	public ArrayList<String> getreservation()
	{
		return this.reservlist;
	}
}
