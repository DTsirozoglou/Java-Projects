package exhibition;

import venue.CulturalEvent;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * An Exhibition class represents the type of the Cultural Event we create.
 * In an Exhibition we must have a Painter that will demonstrate his Paintings.
 * We also want to know the cost of an Exhibition witch depends on the fame of the Painter
 * and the profit base of the Exhibition
 *
 */
public class Exhibition implements CulturalEvent {
	
	protected Painter reference;
	
	/**
	 * Constructs an Exhibition cultural Event with a given Painter.
	 * @param thePainter is the Painter who will have the Exhibition.
	 */
	public Exhibition (Painter thePainter){
		this.reference=thePainter;
	}

	@Override
	public int getCost() {
		return this.reference.getFame();
	}

	@Override
	public int execute() {
		return this.reference.showPaintings(); 		
	}
	
	/**
	 * This method displays some useful info for the event
	 */
	public void display(){
		System.out.println("Exhibition Event with the Painter : "+this.reference.getName()
				+" with fame: "+this.reference.getFame()+" \n");
	}

}
