package workshop;

/**
 * The class ToyMaker inherits from the superclass Elf
 */

public class ToyMaker extends Elf
{
	/**
	 * Constructs a toy maker Elf
	 * @param shop is the workshop where this Elf is created.
	 */
	public ToyMaker (WorkShop shop)
	{
		super.reference=shop;
	}
	
	/* The method creates a new present and adds it to the 
	 * workshops present inventory.
	 */
	public void work()
	{
		Present pres = new Present();
		super.reference.addPresent(pres);
	}
}
