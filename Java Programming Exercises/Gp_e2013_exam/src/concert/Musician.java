package concert;

/**
 * @author Dimitrios Tsirozoglou
 *
 *A Musician has a name and an instrument that he knows to play.
 */
public class Musician {
	
	String name;
	private Instrument instrum;
	
	/**
	 * Constructs a Musician with a name and an assigned Instrument.
	 * @param info the name of the Musician.
	 * @param instr the instrument that the musician can play.
	 */
	public Musician(String info, Instrument instr){		
		name = info;
		instrum= instr;		
	}
	
	/**
	 * This method enquires the name of this band member
	 * @return the name of the artist
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * This method makes the Musician use his instrument
	 * and produce the sound of it.
	 */
	public void play(){
		this.instrum.use();
	}
}
