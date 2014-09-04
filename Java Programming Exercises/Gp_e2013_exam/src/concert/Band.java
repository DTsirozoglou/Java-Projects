package concert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * A Band class aggregates the Musician and the Song classes.
 * That because it contains objects of those classes.
 * A band can have a name a list of the Bands Musicians, and a lists of the Band's songs.
 * In a Band we can add a Song or a Musician member of the Band.
 * Also we can enquire for various other information.
 *
 */
public class Band {
	String name;
	List<Musician> members= new ArrayList <Musician>();
	List<Song> songs= new ArrayList <Song>();
	
	/**
	 * Constructs a Band with the given name.
	 * @param title the name of the Band
	 */
	public Band(String title){
		this.name = title;
	}
	
	/**
	 * This method adds a Musician member to the Band's list of members.
	 * @param member the Musician to be added to the Band's list.
	 */
	public void addMember(Musician member){
		this.members.add(member);
	}
	
	/**
	 * This method enquires the names of the Band's members.
	 * @return the names of the members.
	 */
	public List<String> getMembersNames(){
		List<String> artists=new ArrayList<String>();
		for (Musician artist : this.members)
			artists.add(artist.getName());
		return artists;
	}
	
	/**
	 * This method adds a Song to the Band's Song list.
	 * @param song the Song to be added in the list of the band's Songs.
	 */
	public void addSong (Song song){
		this.songs.add(song);
	}
	
	/**
	 * This method enquires the name of the Band.
	 * @return the name of the Band.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * This method enquires the popularity of the band.
	 * The popularity of the band equals the fame of the Band's most popular song.
	 * @return the fame of the Band's most popular song (the popularity of the band).
	 */
	public int getPopularity(){
		int result = 0;
		for (Song song1 : this.songs){
			if (song1.getPopularity() >result)
				result = song1.getPopularity();
		}
		return result;
	}
	
	/**
	 * This method decides witch of the band's songs will be played 
	 * in the given duration of the concert and makes the musicians of the band
	 * use their instruments for the selected songs. Finally it returns the sum
	 * of the popularity of the selected songs.
	 * @param duration the time the Band is allowed to perform in the event.
	 * @return the profit base of the performance.
	 */
	public int play(int duration){
		int profitBase=0;
		int concertDuration =0;
		
		//I sort the band's song based on their popularity
		// In this way I want to take the most popular band's songs for 
		// the allowed duration of the concert.
		Comparator<Song> comp = new SongComparator();
		Collections.sort (this.songs, comp);
		Collections.reverse(this.songs);
		
		//For every song in the sorted song list I perform a check to see
		// if the song will be added in the Songs that will be played in the event
		for(Song song1:this.songs){
			// play as many songs as fit in the given duration.
			if((concertDuration + song1.length)<= duration){				
				//When a song is selected, all the musicians of the band will
				//use their instrument to play it.
				for (Musician artist : this.members){
					artist.play();
				}
				concertDuration += song1.length;
				//the total popularity score of the song played.
				profitBase += song1.getPopularity(); 
			}
		}
		return profitBase;
	}
}
