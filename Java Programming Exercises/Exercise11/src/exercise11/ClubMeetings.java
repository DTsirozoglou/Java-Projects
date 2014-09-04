package exercise11;

/**
 * The subclass "ClubMeetings" inherits from the superclass Groups. We can say that every "ClubMeetings" is a Group!
 *
 */
public class ClubMeetings extends Groups
{ 
  /**
 * When a group of other is constructed we give it the medium priority "3"
 */
public ClubMeetings()
  {
	this.setPriority(3);
  }

}