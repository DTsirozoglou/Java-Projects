package concert;

/**
 * @author Dimitrios Tsirozoglou
 *
 *The class Piano inherits from the superclass Instrument.
 */
public class Piano extends Instrument {	
	public Piano(){		
	}	
	public String use(){
		System.out.println("Piano Sound");
		return "Piano Sound";
	}
}
