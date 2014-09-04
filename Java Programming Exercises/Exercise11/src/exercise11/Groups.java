package exercise11;

import java.sql.Date;
import java.util.ArrayList;

/**
 * We have the Super Class "Groups" 
 * Groups have an automatic and unique groupID 
 * Groups can be classes groups or seminar groups or guest speakers groups or club meetings group.
 * Groups have a priority. "1" is the highest priority of classes groups, "2" is the medium priority of seminars and guest speakers groups 
 * and "3" is the lowest priority of a club meeting group.
 * Groups can also send requests to college buildings demanding a specific classroom of a building or a classroom matching their criteria 
 * given a range of acceptable time slots.
 */
public class Groups 
{
	private int groupID;
	private static int lastGroupID = 0;
	private int priority;
	
	/**
	 * Constructs a Group with a unique id
	 */
	public Groups()
	{
		setPriority(2);
		lastGroupID++;
		groupID = lastGroupID;
	}
	
	/**
	 * @return the id of the group
	 */
	public int idRequest() 
	{
		return this.groupID;
	}	
	
	 /**
	* @return the priority of the group
	*/
	public int getGroupPriority()
	{
		return this.priority;
	}

   	/**
	 * @param the priority of the group
	 */
	protected void setPriority(int pr) 
	{
		this.priority = pr;
	}			

	/**
	 * This method requests a specific room from a college building
	 * @param college The college building we want to request the room
	 * @param roomID The room Id the the group want to reserve
	 * @param acc_slots The acceptable time-slots to make the reservation 
	 */
	public void roomRequest(CollegeBuilding college,int roomID,ArrayList<Date> acc_slots )
	{
		college.scheduler(this, roomID, acc_slots);
	}

	/**
	 * This method requests a desired room from a college building
	 * @param college The college building we want to request the room
	 * @param size The wanted size of the room to reserve
	 * @param comfSeats if the room has comfortable seats or not
	 * @param amenities the list of the room's amenities
	 * @param acc_slots The acceptable time-slots to make the reservation
	 */
	public void roomRequest(CollegeBuilding college,int size, boolean comfSeats, ArrayList<String> amenities,ArrayList<Date> acc_slots )
	{
		college.scheduler(this, size,comfSeats,amenities, acc_slots);
	}
	
}
