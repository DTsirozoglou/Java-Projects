/**
 A Postcard has a message a sender and different recipients
 */
 
public class Postcard
{
	private String message;
	private String sender;
	private String recipient;
	
	/**
	 Constructs a Postcard with a given message and Sender and an empty recipient 
	 @aSender the name of the Sender
	 @aMessage the message to send
	 */
	
	public Postcard(String aSender, String aMessage)
	{	
		message = aMessage;
		sender = aSender;
		recipient = "";
	}
	
	/**
	 The method sets the recipient to the given name
	 @return the name of the recipient
	 */
	
	public String setRecipient(String name)
	{
		
		recipient = name;
		return recipient;
	}
	
	/**
	A method to display the contents of the postcard
    */
	
	public void print()
	{
		System.out.println("Dear " + recipient + "\n");
		System.out.println(message + "\n");
		System.out.println("Best regards " + sender + "\n");
				
	}
}