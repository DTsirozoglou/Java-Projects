/**
A class to test the Postcard class.
*/
public class PostcardPrinter
{
	public static void main(String[] args)
	{
		/**
		Tests the methods of the Postcard class
		*/
		
		String text = "I am having a great time on the island of Java.\nWeather is great. Wish you were here!";
		Postcard postcard = new Postcard("Janice", text);
		postcard.setRecipient("Sue");
		postcard.print();
		postcard.setRecipient("Tim");
		postcard.print();
	}
}