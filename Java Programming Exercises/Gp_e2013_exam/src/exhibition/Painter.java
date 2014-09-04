package exhibition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * A Painter class aggregates Painting class.
 * That because it contains objects of that class.
 * A Painter can have a name and a list of Paintings.
 * In a Painter we can add a Painting and we can enquire for various info.
 *
 */
public class Painter {
	
	String name;
	List<Painting> listPaintings = new ArrayList<Painting>();
	
	/**
	 * Constructs a Painter with the given name.
	 * @param info the name of the Painter.
	 */
	public Painter (String info){
		
		name=info;		
	}
	
	/**
	 * This method enquires the name of the Painter. 
	 * @return the name of the Painter.
	 */
	public String getName(){
		
		return this.name;
	}
	
	/**
	 * This method adds a Painting to the List of the Painter's Paintings.
	 * @param draw the Painting to add in the Painter's List of Paintings.
	 */
	public void addPainting(Painting draw){
		
		listPaintings.add(draw);
		
	}
	
	/**
	 * This method enquires the fame of the Painter.
	 * The fame of the Painter equals to the fame of his best Painting.
	 * @return the fame of the Painter (his best Painting fame).
	 */
	public int getFame(){
		
		int maxFame = 0;
		
		for (Painting paint : listPaintings){			
			if (paint.getFame()>maxFame)
				maxFame = paint.getFame();
			}		
		return maxFame;
	}
	
	/**
	 * This method enquires the profit base of the Painter.
	 * that is equal to the sum of his paintings’ fame.
	 * @return the sum of the Painter's paintings’ fame.
	 */
	public int showPaintings(){
		
		int sum = 0;		
		for (Painting paint : listPaintings)
			sum +=paint.getFame();
		return sum;		
	}

}
