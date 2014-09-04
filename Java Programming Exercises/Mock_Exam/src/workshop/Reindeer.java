package workshop;

/**
 * A Reindeer has the characteristic "hunger", witch is represented by an integer field, hunger, that goes from 0 to 100,
 * with 0 being full and 100 being very hungry.Also Reindeer gets an automatic int Id, in order to be able to make our
 * toString override method more meaningful.    This is represented by an integer field, hunger, that goes 
 * from 0 to 100, with 0 being full and 100 being very hungry. 
 *  
 */
public class Reindeer 
{
	private int reindeerID;
	private static int lastreindeerID = 0;
	int hunger;
	
	/**
	 * Constructs a Reindeer with hunger set to maximum and an automatic id. 
	 */
	public Reindeer ()
	{		
		reindeerID = lastreindeerID;
		lastreindeerID++;
		this.hunger = 100;
	}
	
	/**The method enquires the hunger of the Reindeer
	 * @return the hunger of the Reindeer
	 */
	public int getHunger()
	{
		return this.hunger;
	}
	
	/**The method enquires if the Reindeer is full.
	 * @return true if the hunger of the Reindeer is set to 0.
	 */
	public boolean isFull()
	{
		if (this.hunger == 0)
		{
			return true;
		}
		else return false;
	}
	
	/**
	 * The method decreases the Reindeer’s hunger with 5.This method is used by the Reindeer Feeder.
	 */
	public void feed()
	{
		if (this.hunger -5 > 0)
		{
			this.hunger = this.hunger - 5;
		}
		//hunger level can never decrease below zero!
		else
		{
			this.hunger = 0;
		}
	}
	
	/**
	 * The method increases the Reindeer’s hunger with 50
	 */
	public void ride()
	{
		if (this.hunger + 50 < 100)
		{
			this.hunger = this.hunger + 50;
		}
		//hunger level can never go above 100!
		else
		{
			this.hunger = 100;
		}		
	}
	
	/* 
	 * This method overrides toString object class.
	 * @return the id of the Reindeer and its hunger.
	 */
	public String toString()
	{
		return "The Reindeer with ID "+ this.reindeerID + " has hunger : "+this.getHunger();
	}
}
