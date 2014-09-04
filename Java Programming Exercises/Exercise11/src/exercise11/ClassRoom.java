package exercise11;

import java.util.ArrayList;

/**
 * The class ClassRoom has a unique roomNumber, a size, amenities and may also 
 * have comfortable seats.
 */
public class ClassRoom {
	
	private boolean comforSeats; 
	private ArrayList<String> amenities = new ArrayList<String>();
	private int size;
	private int classID;
	private static int lastClassID = 0;
	
	/**
	 * Constructs a ClassRoom with a unique id,
	 * @param theSize the size of the classroom
	 * @param theAmenities the amenities of the classroom
	 * @param comfort 0 if the classroom doesn't have comfortable seats
	 */
	public ClassRoom(int theSize,ArrayList<String> theAmenities,boolean comfort)
	{
		comforSeats = comfort;
		amenities = theAmenities;
		size = theSize;
		lastClassID++;
		classID = lastClassID;
	}

	/**
	 * @return true if the classroom has comfortable seats
	 */
	public boolean isComforSeats() {
		return comforSeats;
	}

	/**
	 * @return the amenities of the classroom
	 */
	public ArrayList<String> getAmenities() {
		return amenities;
	}

	/**
	 * @return the size of the classroom
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the classID
	 */
	public int getClassID() {
		return classID;
	}

}
