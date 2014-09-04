package workshop;

/**
 * The class PresentWrapper inherits from the superclass Elf
 */

public class PresentWrapper extends Elf
{
	/**
	 * Constructs an Elf for wrapping the presents
	 * @param shop is the workshop where this Elf is created.
	 */
	public PresentWrapper (WorkShop shop)
	{
		super.reference=shop;
	}
	
	
	/* The method make this elf takes an unwrapped present from the 
	 * workshop’s present inventory (if one exists) and wraps it neatly.
	 */
	public void work()
	{
		if (!(super.reference.getPresents()==null))
		{
			for (Present pres : super.reference.getUnwrappedPresents())
			{
				if (pres.wrapped == false)
				{
					pres.setWrapped(true);
				}
			}
		}
	}

}
