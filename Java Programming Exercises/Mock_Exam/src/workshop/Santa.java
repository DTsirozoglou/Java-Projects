package workshop;

import java.util.ListIterator;

/**
 * The Santa class creates the workshop and puts it in a field.Furthermore, Santa has one method, deliverPresents().
 * Since there will always only be one Santa, we’re making all members (methods and fields) on this class static. 
 */
public class Santa
{
	private static WorkShop shop;
	public Santa() {
	}
	
	/**
	 * The method enquires the workShop of Santa.
	 * @return the workShop
	 */
	public static WorkShop getWorkshop()
	{
		if (shop != null) {
			return shop;
		} else {
			shop = new WorkShop();
		}
		return shop;
	}
	
	/**
	 * The method will call the ride method on all the reindeer in the workshop, 
	 * remove all wrapped presents from the workshop inventory.
	 * Presents that are not yet wrapped are left behind.
	 */
	public static void deliverPresents()
	{
		// call the ride method on all the reindeer in the workshop
		for (Reindeer deer : shop.getReindeers())
		{
			deer.ride();
		}
		
		ListIterator<Present> iterator = shop.getPresents().listIterator();
		
		// remove all wrapped presents from the workshop inventory
		while (iterator.hasNext())
		{
			if (iterator.next().wrapped == true)
			{
				iterator.remove();
			}
		}
	}
}
