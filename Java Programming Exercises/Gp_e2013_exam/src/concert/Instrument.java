package concert;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * An Instrument can be a Drums, a Guitar or a Piano.All Instruments should inherit 
 * from this abstract class,Instrument.
 */
public abstract class Instrument {	
	public Instrument(){		
	}
	
	/**
	 * An abstract method to be implemented by the subclasses of the class Instrument.
	 * This method returns the sound of the instrument in a string 
	 * and prints it in the systems console.
	 * @return the sound of the Instrument being used.
	 */
	abstract String use();
}
