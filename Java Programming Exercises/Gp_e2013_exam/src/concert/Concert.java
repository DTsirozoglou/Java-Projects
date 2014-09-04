package concert;

import venue.CulturalEvent;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * An Concert class represents the type of the Cultural Event we create.
 * In an Concert we must have a Band that will perform for a specific duration.
 * We also want to know the cost of a Concert witch depends on the Band's most popular song
 * and the profit base of the Concert.
 *
 */
public class Concert implements CulturalEvent {
	
	private int duration;
	private Band bandRefer;
	
	/**
	 * Constructs a Concert with the performing Band, and with a duration.
	 * @param band the Band that will perform in the Concert.
	 * @param durat the duration of the Concert.
	 */
	public Concert (Band band, int durat){
		this.duration = durat;
		this.bandRefer = band;		
	}
	
	/**
	 * This method enquire the Band performing on the Concert.
	 * @return the Band performing on the Concert.
	 */
	public Band getBand(){
		return this.bandRefer;
	}
	
	/**
	 * This method sets the Band that will perform in the Concert.
	 * @param band the Band that will perform in the Concert.
	 */
	public void setBand(Band band){
		this.bandRefer = band;
	}
	
	/**
	 * This method enquires the duration of the concert.
	 * @return the duration of the Concert.
	 */
	public int getDuration(){
		return this.duration;
	}

	@Override
	public int getCost() {
		return this.bandRefer.getPopularity();
	}

	@Override
	public int execute() {
		return this.bandRefer.play(this.duration);
	}

	@Override
	public void display() {
		System.out.println("Concert Event with the Band : "+this.bandRefer.getName()+ " with popularity : "+this.bandRefer.getPopularity());
		System.out.println("The members of the Band are: "+this.bandRefer.getMembersNames()+" \n");
		
	}
}
