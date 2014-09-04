package concert;

/**
 * @author Dimitrios Tsirozoglou
 *
 *The class Drums inherits from the superclass Instrument.
 */
public class Drums extends Instrument {	
	public Drums(){		
	}
	public String use(){
		System.out.println("Drums Sound");
		return "Drums Sound";
	}
}
