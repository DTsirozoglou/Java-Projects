package exercise11;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A CollegeBuilding class aggregates a ClassRoom class as it contains objects of the Classroom class. We have a "has-a"
 * relationship, every CollegeBuilding has a classRoom or more. 
 * A College building also can schedule requests of Groups,to efficiently occupy the college's classrooms 
 * To schedule them, we must have in mind the group's priority and the specific group restrictions.
 */
public class CollegeBuilding {
	
	private ArrayList<ClassRoom> rooms = new ArrayList<ClassRoom>();
	Calendar calen; //= Calendar.getInstance();
	// calendar = a weekly time slot table  with slots from 5 a.m. to 10 p.m. daily. (maybe a new calendar)
	// At first the fields of this table will be set to 0, declaring that all the time slots are available.

	/**
	 * Constructs a CollegeBuilding with a new time slot table, and the classes that this college has
	 */
	public CollegeBuilding(ArrayList<ClassRoom> Therooms)
	{
		rooms = Therooms;
		//calendar = new calendar
		calen = Calendar.getInstance();
	}
	
	public Calendar scheduler(Groups group, int roomID,ArrayList<Date> acc_slots) 
	{
		// look if the wanted classroom exists
		// if (roomID== rooms.get(0).getClassID())
		// Check the priority of the group requesting the room
		//	group.getGroupPriority();
		// If the priority is one (it is a classes group) we must check and the classes priority among other classses
		//	((Classes) group).getClassePriority();
		// If the priority is three (it is a club meeting group) we must have in mind that this group  can be placed in limited time slots.
		// When we find a match for the request we renew the calendar of this college building and we can return it
		// Alternative we could send a String message, informing the group about the result of the request.
		 return calen;
	}
	
	public String scheduler(Groups group, int size, boolean comfSeats, ArrayList<String> amenities,ArrayList<Date> acc_slots) 
	{
		// The only difference here is that we must first search if there is a classroom in the building matching the given description
		return "Classroom found!!";
	}
}
