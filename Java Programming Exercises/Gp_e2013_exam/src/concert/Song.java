package concert;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * Song is a simple Class. A song has a title, a length 
 * and a popularity index between one to five
 *
 */
public class Song {
	
	String title;
	int length;
	int popularity;
	
	/**
	 * Constructs a Song with the given name,duration and fame.
	 * @param name the title of the Song.
	 * @param duration the length of the Song.
	 * @param fame the popularity of the Song.
	 */
	public Song (String name, int duration, int fame){
		this.title = name;
		this.length = duration;
		this.popularity = fame;
	}
	
	/**
	 * This method enquires the title of the Song.
	 * @return the title of the Song.
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * This method enquires the Length of the Song.
	 * @return the length of the Song.
	 */
	public int getLength(){
		return this.length;
	}
	
	/**
	 * This method enquires the popularity of the Song
	 * @return the popularity of the Song.
	 */
	public int getPopularity(){
		return this.popularity;
	}
}
