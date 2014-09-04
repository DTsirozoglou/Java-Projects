package concert;

/**
 * @author Dimitrios Tsirozoglou
 *
 *The class Guitar inherits from the superclass Instrument.
 */
public class Guitar extends Instrument {	
	public Guitar(){		
	}
	public String use(){
		System.out.println("Guitar Sound");
		return "Guitar Sound";
	}
}
