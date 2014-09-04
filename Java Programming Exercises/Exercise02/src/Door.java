
/**
 A Door has a state and a name that can be changed 
 */

public class Door {
	
	private String state; 
	private String name;
	
	/**
	 Constructs a Door with the state set to "open" and the name to "front" 
	 */
	
	public Door() {
		
		state = "open";
		name = "front";
		
	}
	
	/**
	 Constructs a Door with the state set to newState and the name to newName 
	 @newState the new state of the door
	 @newName the new name of the door
	 */
	
	public Door(String newName, String newState) {
		
		state = newState;
		name = newName;
		
	}
	
	/**
	 The method sets the state of the door to open
	 */
	
	public void open() {
		
		state = "open";
		
	}
	
	/**
	 The method sets the state of the door to closed
	 */
	
	public void close() {
		
		state = "closed";
		
	}
	
	/**
	 The method gets the name of the door
	 @return the name of the door 
	 */
	
	public String getName() {
		
		return name;
		
	}
	
	/**
	 The method gets the state of the door
	 @return the state of the door 
	 */
	
	public String getState() {
		
		return state;
		
	}
	
	/**
	Sets the name of the door to a new Name
    @param newName the new name of the door
	*/
	
	public void setName (String newName) {
		
		name = newName;
		
	}
	
	/**
	Sets the State of the door to a new State
    @param newState the new state of the door
	*/
	
	public void setState (String newState) {
		
		state = newState;
		
	}

}
