package exhibition;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * Painting is a simple class. 
 * A Painting has a title and a fame  
 *
 */
public class Painting {
	
	private String title;
	private int fame;
	
	/**
	 * Constructs a Painting with a title and a fame
	 * @param name the title of the Painting
	 * @param famous the fame of the Painting
	 */
	public Painting (String name, int famous) 
	{
		title = name;
		fame = famous;
	}

	/**
	 * The method enquires the title of the Painting
	 * @return the title of the Painting
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * The method enquires the fame of the Painting
	 * @return the fame of the Painting
	 */
	public int getFame(){
		return this.fame;
	}
}
