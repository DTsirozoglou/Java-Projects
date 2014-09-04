package venue;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * An Event, in order to be a CulturalEvent, must implement the methods 
 * of the CulturalEvent interface. 
 *
 */
public interface CulturalEvent {

	/**
	 * This method enquires the cost of the CulturalEvent
	 * @return the cost of the CulturalEvent
	 */
	public int getCost();
	
	/**
	 * This method executes the current CulturalEvent 
	 * and returns the profit base of the event 
	 * @return the profit base of the CulturalEvent
	 */
	public int execute();
	
	/**
	 * This method displays some useful info for the Event
	 */
	public void display();
}
