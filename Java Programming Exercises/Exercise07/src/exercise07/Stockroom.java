package exercise07;

/**
 * Stockroom is a Program hat allows the stockroom clerk to create items,
 * check out items and check them back in, reserve items, notify the next employee in the item's reservation list when a reserved item 
 * has been returned, and notifies the clerk if the employee checking in must be fined or not.
 */

public class Stockroom 
{
	public static void main(String[] args) 
	{
		//To illustrate the behaviour i have created a music player that can be reserved for seven days
		Item musicPlayer = new Item ("music player",7);
		
		//The employee "Dimitris" come and rent the music player on 10th of August, and the clerk initialise the reservation list with him.
		musicPlayer.addreservation("Dimitris",10,8);
		
		//Then the employee "Petros" comes the next day and wanted to rent the music player, so the clerk add him to the reservation list of the music player
		musicPlayer.addreservation("Petros",11,8);
		
		//Then the employee "Kostas" comes at 12/08 and wanted to rent the music player, so the clerk add him to the reservation list of the music player
		musicPlayer.addreservation("Kostas",12,8);
		
		System.out.println("The reservation list of the "+ musicPlayer.name+ " is " + musicPlayer.reservlist);
		
		//The employee "Dimitris" come and return the music player on 18th of August, so he must be fined! and Petros must know that 
		//the music player is available, and Dimitris must be removed from the list
		musicPlayer.removeReservation("Dimitris", 18, 8);
			
		System.out.println("The reservation list of the "+ musicPlayer.name+ " is now " + musicPlayer.reservlist);
		System.out.println("The statu of the "+ musicPlayer.name+ " is " + musicPlayer.status);
		
		//After Petros was informed he went to reserve the book at 20/08, 
		//so the clerck should change the status of the player to reserved!
		musicPlayer.addreservation("Petros",20,8);
		System.out.println("The reservation list of the "+ musicPlayer.name+ " is now " + musicPlayer.reservlist);
		System.out.println("The statu of the "+ musicPlayer.name+ " is " + musicPlayer.status);
	}

}
