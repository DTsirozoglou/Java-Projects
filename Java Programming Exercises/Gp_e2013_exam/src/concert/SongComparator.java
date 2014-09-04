package concert;

import java.util.Comparator;


/**
 * @author Dimitrios Tsirozoglou
 * 
 * SongComparator is created to help me sort the Song List of each band by it's popularity.
 *
 */
public class SongComparator implements Comparator<Song> {
	
	/**
	 * Compares two Song objects.
	 * @param song1 the first song
	 * @param song2 the second song
	 * @return 1 if the popularity of the first song is greater than the popularity of
	 * the second song, -1 if the popularity of the first song is
	 * smaller than the popularity of the second song or 0 if the two
	 * songs have the same popularity
	 */
	public int compare(Song song1, Song song2){
		if (song1.getPopularity()>song2.getPopularity())
			return 1;
		else if (song1.getPopularity()<song2.getPopularity())
			return -1;
		else
			return 0;
	}
}
