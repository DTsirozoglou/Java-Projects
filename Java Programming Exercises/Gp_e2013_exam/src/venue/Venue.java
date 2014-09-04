package venue;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Dimitrios Tsirozoglou
 * 
 *The class Venue represents the place where cultural events take place.
 *A venue is where events are booked and then at a later date executed. 
 *The events cost a specific price cause artists need to get paid 
 *– but can on the other hand generate a profit for the Venue, if the events are popular.
 *The venue maintains an internal calendar (a currentDate), so it always knows what 
 *date is currently is and what upcoming events are in the pipeline. 
 *A Venue also has a balance amount, so as to book artists, and a random audience
 *when the event is happening. 
 */
public class Venue {
	
	private int balance; 
	private int audience;
	private Date currentDay;
	// I decided that since we allow only one event at a day, a treemap
	// sorted by date, will be a nice structure to keep my Venue Schedule.
	private Map<Date, CulturalEvent> schedule;

    /**
     * Constructs a Venue with a given starting balance (starting funds).
     * @param bal the starting balance of the Venue.
     */
    public Venue(int bal){
    	this.balance = bal;
    	Random randomGenerator = new Random();
    	//creates a random audience from 0 to 9 people.
    	this.audience = randomGenerator.nextInt(10);
    	this.schedule  = new TreeMap<Date, CulturalEvent>(); 
    	//Sets the current Day of the venue scheduler to the current day.
    	this.setCurrentDate(new Date());    	
    }
    
    /**
     * This method enquires the number of the audience.
     * @return the number of the audience.
     */
    public int getAudience(){
    	return this.audience;
    }
    
    /**
     * This method enquires all the events scheduled at the venue.
     * @return the schedule of the Venue.
     */
    public Map<Date, CulturalEvent> getEvents(){
    	return this.schedule;
    }
    
    /**
     * This method makes a request to Venue scheduler to book a specific date 
     * for a specific cultural event.If the booking is successful then the cost of
     * making this event must be subtracted from the Venue current funds. 
     * @param reqDate the requesting date for the event.
     * @param event The event for that date.
     */
    public void bookEvent(Date reqDate,CulturalEvent event){
    	boolean alreadyBooked = false;
    	if (!reqDate.before(getCurrentDate())){
	    	for (Map.Entry<Date, CulturalEvent> entry : this.schedule.entrySet()){
	    		if (entry.getKey().equals(reqDate))
	    			//if there is a date already in our schedule that means
	    			//that another event is planned on that date.
	    			//So that date is already booked!
	    			alreadyBooked = true;
	    			break;
	    	}
	    	//We also check if there are enough funds to book the event
	    	if ((this.balance - event.getCost() >=0)&(!alreadyBooked)){
				this.schedule.put(reqDate, event);
				//When we book the event, the cost of the event must
				//be subtracted from the Venue's funds.
				this.balance -= event.getCost();
				System.out.println("Booked Succesfully completed");
	    	}
	    	else
	    		System.out.println("Cannot be booked");
    	}
    	else
    		//If the requested date for the event is before the date of the current date
    		//the scheduler, then no booking!
    		System.out.println("That date passed");
    }
    
    /**
     * This method executes the event scheduled on the current day 
     * of the Venue's scheduler.
     */
    public void makeEventHappen(){
       	//get the popularity score of the cultural event
    	int popularityScore = this.schedule.get(this.currentDay).execute();
    	//calculate the profits Base by multiplying with the number of the audience.
    	int profitsBase = this.audience *popularityScore;
    	this.balance += profitsBase;
    	//update our schedule by removing this event.
    	this.schedule.remove(getCurrentDate());
    	//go to the next day
    	this.incremmentCurrentDay();
    }
    
    /**
     * This method sets the current day of the Venue's scheduler
     * to the date of the next planned event.
     * @throws Exception if there are no planned event on the scheduler.
     */
    public void nextEventDate() throws Exception{
    	
    	if (!this.moreEvents())
    		throw new Exception("No more planned events in the schedule");
    	else{
    		Set<Date> c =  this.schedule.keySet();
        	Iterator<Date> itr = c.iterator();
        	Date upcomingEventday = itr.next();
        	this.setCurrentDate(upcomingEventday); 
        	}
    }
    
    /**
     * This method checks if there are any planned events after 
     * the current day stored on the Venue.
     * @return true if there is an Event planned after the shedulers current day.
     */
    public boolean moreEvents(){
    	Set<Date> c =  this.schedule.keySet();
    	Iterator<Date> itr = c.iterator();
    	if (itr.hasNext()){
	    	Date firstDate = itr.next();
	    	if (firstDate.equals(getCurrentDate())){
	    		//if the first entry in our map is the current date
	    		//then we must check if there is another entry.
	    		if (itr.hasNext())
	        		return true;
	        	else
	        		return false;
	    	}
	    	//if it has next and this isn't the current date, then it is another event.
	    	else
	    		return true;
    	}
    	//if itr.hasNext()==null then we have nothing scheduled.
    	else
    		return false;    	
    }
    
    /**
     * This method enquires the balance of the Venue.
     * @return the balance of the Venue.
     */
    public int getBalance(){
    	return this.balance;
    }
    
    /**
     * This method enquires the date of the current date of the Venue.
     * @return the date of the current date of the Venue.
     */
    public Date getCurrentDate(){
    	return this.currentDay;
    }
    
    /**
     * This method sets the date of the current date of the Venue.
     * @param newDate the new date of the current date of the Venue.
     */
    private void setCurrentDate(Date newDate){
    	this.currentDay = newDate;
    }
    
	/**
	 * This method increase the Current date of the Scheduler by one.
	 */
	private void incremmentCurrentDay(){   
		
		Calendar cal = Calendar.getInstance();
		//set callendars time to the time of the current date of the Venue.
		cal.setTime( this.getCurrentDate());
		//go to the next date of that date.
		cal.add( Calendar.DATE, 1 );          
		Date tommorrow= cal.getTime();
		//set the date of the Venue's current date.
	    this.setCurrentDate(tommorrow);
	    //Generate random audience for that date.
	    Random randomGenerator = new Random();
	    this.audience = randomGenerator.nextInt(10);
    }
    
    /**
     * This method displays the Schedule of the Venue in the console.
     */
    public void display(){
    	for (Map.Entry<Date, CulturalEvent> entry : this.schedule.entrySet()){
    		System.out.println("in the date : " + entry.getKey() + " we have the event : "+" \n");
    	    entry.getValue().display();
    	}
    }
}
