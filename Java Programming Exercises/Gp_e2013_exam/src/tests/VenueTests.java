package tests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import venue.CulturalEvent;
import venue.Venue;
import concert.Band;
import concert.Concert;
import concert.Drums;
import concert.Guitar;
import concert.Instrument;
import concert.Musician;
import concert.Piano;
import concert.Song;
import exhibition.Exhibition;
import exhibition.Painter;
import exhibition.Painting;

/**
 * @author Dimitrios Tsirozoglou
 * This is a JUnit test, to test the methods used by our implemented program
 */
public class VenueTests {
	
	private static List<CulturalEvent> events = new ArrayList<CulturalEvent>();
	SimpleDateFormat dateFormat = new SimpleDateFormat( "dd/MM/yyyy" );

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		//At first we will create our world.
		
		//Create some Paintings
		Painting d1a = new Painting("Painting 1a",4);
		Painting d1b = new Painting("Painting 1b",1);
		Painting d1c = new Painting("Painting 1c",3);
		Painting d1d = new Painting("Painting 1d",4);
		Painting d1e = new Painting("Painting 1e",2);
		Painting d1f = new Painting("Painting 1f",5);
		
		Painting d2a = new Painting("Painting 2a",3);
		Painting d2b = new Painting("Painting 2b",3);
		Painting d2c = new Painting("Painting 2c",3);
		Painting d2d = new Painting("Painting 2d",4);
		Painting d2e = new Painting("Painting 2e",1);
		Painting d2f = new Painting("Painting 2f",2);
		
		//Create two Painters
		Painter p1 = new Painter("Painter 1");
		Painter p2 = new Painter("Painter 2");
		
		//Add the Paintings to the Painters
		p1.addPainting(d1a);
		p1.addPainting(d1b);
		p1.addPainting(d1c);
		p1.addPainting(d1d);
		p1.addPainting(d1e);
		p1.addPainting(d1f);
		
		p2.addPainting(d2a);
		p2.addPainting(d2b);
		p2.addPainting(d2c);
		p2.addPainting(d2d);
		p2.addPainting(d2e);
		p2.addPainting(d2f);
		
		//Create the instruments that the artists will use		
		Instrument piano = new Piano();
		Instrument guitar = new Guitar();
		Instrument drums = new Drums();
		
		//Create the musicians of the Bands
		Musician artist1 = new Musician("artist1", guitar);
		Musician artist2 = new Musician("artist2", guitar);
		Musician artist3 = new Musician("artist3", guitar);
		Musician artist4 = new Musician("artist4", piano);
		Musician artist5 = new Musician("artist5", drums);
		Musician artist6 = new Musician("artist6", drums);
		Musician artist7 = new Musician("artist7", drums);
		Musician artist8 = new Musician("artist8", guitar);
		
		//Create three Bands
		Band band1 = new Band("band1");
		Band band2 = new Band("band2");
		Band band3 = new Band("band3");
		
		//Add the members to the bands
		band1.addMember(artist1);
		band1.addMember(artist8);
		band1.addMember(artist4);
		band1.addMember(artist6);
		
		band2.addMember(artist2);
		band2.addMember(artist5);
		
		band3.addMember(artist3);
		band3.addMember(artist7);
		
		//Create the songs that will be at the bands repertoire. 
		Song song1 = new Song("song1", 5, 2);
		Song song2 = new Song("song2", 8, 2);
		Song song3 = new Song("song3", 2, 5);
		Song song4 = new Song("song4", 4, 2);
		Song song5 = new Song("song5", 7, 5);
		Song song6 = new Song("song6", 5, 3);
		Song song7 = new Song("song7", 3, 2);
		Song song8 = new Song("song8", 3, 4);
		Song song9 = new Song("song9", 3, 2);
		Song song10 = new Song("song10", 5, 2);
		Song song11 = new Song("song11", 3, 2);
		Song song12 = new Song("song12", 6, 3);
		
		//Add the songs to the bands
		band1.addSong(song12);
		band1.addSong(song11);
		band1.addSong(song10);
		band1.addSong(song6);
		band1.addSong(song2);
		
		band2.addSong(song4);
		band2.addSong(song7);
		band2.addSong(song3);
		band2.addSong(song9);
		band2.addSong(song8);
		
		band3.addSong(song1);
		band3.addSong(song5);
		
		//Create events
		Exhibition event1st = new Exhibition(p1);
		Exhibition event2nd = new Exhibition(p2);
				
		Concert concert1 = new Concert(band1, 15);
		Concert concert2 = new Concert(band2, 14);
		Concert concert3 = new Concert(band3, 15);
			
		//add the events to our events List
		events.add(event1st);
		events.add(event2nd);
		events.add(concert1);
		events.add(concert2);
		events.add(concert3);
	}
	
	/**
	 * Here we want to test all the method being used in the beforeClass
	 * 
	 * 1. First we create Some Paintings, some Painters,some Songs, some Artists and some bands.
	 * 
	 * 2. We start adding Paintings to the Painter testing the addPainting method functionality.
	 * 
	 * 3. We add songs and musician members to our three Bands testing the addSong and addMember methods. 
	 * 
	 * 4. We create events with the bands and the painters and add them to our static event list.
	 * 
	 * 5. In the test we check if all the events are inserted into the event list 
	 * 
	 * 6. The display method, except from testing its functionality, among with this we also check the methods 
	 *    getName(class Painter), getFame, getMembersNames, getPopularity, getName(class Band), that are used 
	 *    in the display method.Also with the display method we can visualise what is going on in our Program
	 *    and that is we I implement it.
	 */

	@Test
	public void test1() {
		System.out.println("Test1 display results :" +" \n");
		
		assertTrue(events.size()==5);
		for (CulturalEvent event : events)
			event.display();		
	}
	
	/**
	 * Here we want to test methods of the Venue Class
	 * 
	 * 1. First we create the current day.
	 * 
	 * 2. We create a Venue with a starting balance of 15.
	 * 
	 * 3. We test getCurrentDate() method by checking if the initialisation has be done correctly
	 * 
	 * 4. We test the getBalnce() method by checking if it returns the same amount as the started (15).
	 * 
	 * 5. Finally we check the moreEvents() method, by assuring that it returns false result since we don't have
	 * 	  any booked events yet.
	 * @throws ParseException 
	 */

	@Test
	public void test2() {
		
		Date today = new Date();
		Venue venue = new Venue(15);
		assertTrue(today.equals(venue.getCurrentDate()));
		assertTrue(venue.getBalance()==15);
		assertTrue(!venue.moreEvents());
	}
	
	/**
	 * Here we want to test methods of the Venue Class
	 * 
	 * 1. First we create a Venue with a starting balance of 20.
	 * 
	 * 2. We test bookEvent and getCost methods.
	 * 
	 * 3.We try to book dates of the venue schedule for all the events we have on the events list.
	 * 
	 * 4.The last's books date is before our current date so our scheduler should not book this event,
	 *   and should print a message in the console that the date has passed!
	 *   
	 * 5.After every successful booking, we check that the venue's balanced has been reduced by the cost of the booking.
	 * 	 We also check that in the last case, our balance remained the same.
	 * 
	 * 6.We move the current day of our scheduler using the moveEventDay method.
	 *   The expected current day of our scheduler should now be "14/12/2013", as the date of our first event in the schedule.
	 *   
	 * 7.Display the schedule of the Venue for visualising what is going on.
	 */

	@Test
	public void test3() {
		
		System.out.println("Test3 results :" +" \n");
		System.out.println("Result of Booking" +" \n");
			
		Venue venue = new Venue(20);
		int startingBalance = venue.getBalance();
		try {
			venue.bookEvent(dateFormat.parse("31/12/2013"),events.get(0));
		} catch (ParseException e) {
		}
		assertTrue(venue.getBalance()<startingBalance);
		startingBalance=venue.getBalance();
		try {
			venue.bookEvent(dateFormat.parse("22/12/2013"),events.get(1));
		} catch (ParseException e) {
		}
		assertTrue(venue.getBalance()<startingBalance);
		startingBalance=venue.getBalance();
		try {
			venue.bookEvent(dateFormat.parse("21/12/2013"),events.get(2));
		} catch (ParseException e) {
		}
		assertTrue(venue.getBalance()<startingBalance);
		startingBalance=venue.getBalance();
		try {
			venue.bookEvent(dateFormat.parse("14/12/2013"),events.get(3));
		} catch (ParseException e) {
		}
		assertTrue(venue.getBalance()<startingBalance);
		startingBalance=venue.getBalance();
		try {
			venue.bookEvent(dateFormat.parse("9/12/2013"),events.get(4));
		} catch (ParseException e) {
		}
		assertTrue(venue.getBalance()==startingBalance);
		
		try {
			venue.nextEventDate();
		} catch (Exception e) {
			System.out.print("no events planned");
		}
		try {
			assertTrue(venue.getCurrentDate().equals(dateFormat.parse("14/12/2013")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(" \n"+"Test3 display results :" +" \n");
		venue.display();
	}

	/**
	 * Here we want to test methods of the Venue Class
	 * 
	 * 1. First we create a Venue with a starting balance of 25.
	 * 
	 * 2. We a Venue with a starting balance of 15.
	 * 
	 * 3. We try to book dates of the venue schedule for all the events we have on the events list.
	 * 
	 * 4. Two of the events have the same requesting date, so we expect only the first 
	 *    that asks for booking to be booked, and we also expect a message informing that the booking 
	 *    didn't happen, printed in the system console.
	 * 
	 * 5. We navigate our scheduler to the date of the upcoming event with the nextEventDate() method,
	 * 	  so the current date of our sheduler points at "14/12/2013".
	 * 
	 * 6. We store the number of events in the scheduler with the getEvents() method, and we also store the amount 
	 * 	  of the funds we have at this moment.
	 * 
	 * 7. We make the event happen, with the  makeEventHappen() method.
	 * 
	 * 8. We check that after the concert, the balance has been increased, the current date has moved one day 
	 * 	  to "15/12/2013", and the event has been removed from the scheduler.
	 * 
	 * 9. Finally we can see in the console, the sounds of the bans instruments when executing the concert.
	 * 	  and we display the scheduler status that should only contain 3 events now. 
	 */

	@Test
	public void test4() {
		
		System.out.println("Test4 results :" +" \n");
		System.out.println("Result of Booking" +" \n");
			
		Venue venue = new Venue(25);
		try {
			venue.bookEvent(dateFormat.parse("31/12/2013"),events.get(0));
		} catch (ParseException e) {
		}
		
		try {
			venue.bookEvent(dateFormat.parse("22/12/2013"),events.get(1));
		} catch (ParseException e) {
		}
		try {
			venue.bookEvent(dateFormat.parse("22/12/2013"),events.get(2));
		} catch (ParseException e) {
		}
		try {
			venue.bookEvent(dateFormat.parse("14/12/2013"),events.get(3));
		} catch (ParseException e) {
		}
		try {
			venue.bookEvent(dateFormat.parse("19/12/2013"),events.get(4));
		} catch (ParseException e) {
		}
		try {
			venue.nextEventDate();
		} catch (Exception e) {
			System.out.print("no events planned");
		}
		int balanceBeforeConcert = venue.getBalance();
		int numberEvents = venue.getEvents().size();
		System.out.println("");
		System.out.println("The event starts!!" +" \n");
		venue.makeEventHappen();
		// It is equal only if there is no audience that day.
		assertTrue(venue.getBalance()>=balanceBeforeConcert);
		try {
			assertTrue(venue.getCurrentDate().equals(dateFormat.parse("15/12/2013")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertTrue(venue.getEvents().size() == numberEvents -1);
		
		System.out.println(" \n"+ "Test4 Display results :" +" \n");
		venue.display();
	}
}
