package workshop;

import java.util.Set;

/**
 * The class ReindeerFeeder inherits from the superclass Elf
 */

public class ReindeerFeeder extends Elf
{
	/**
	 * Constructs an Elf for feeding the Reindeer
	 * @param shop is the workshop where this Elf is created.
	 */
	public ReindeerFeeder (WorkShop shop)
	{
		super.reference=shop;
	}
	
	/* 
	 * The method finds the hungriest reindeer in the workshop and feeds it.
	 * This is repeated 2 times (for a total of 3 feeding sessions all in all).
	 */
	public void work()
	{
		Set <Reindeer> reindeers = super.reference.getReindeers(); 
		Reindeer mostHungry = null;
		int mosthunger=0;
		
		for (int i = 0; i <3; i++)
		{
			for (Reindeer deer : reindeers)
			{
				if (deer.getHunger()>mosthunger)
				{
					mosthunger = deer.getHunger();
					mostHungry = deer;
				}
			}
			if (mosthunger==0)
			{
				break;
			}
			else
			{
				mostHungry.feed();
			}
		}
	}

}
