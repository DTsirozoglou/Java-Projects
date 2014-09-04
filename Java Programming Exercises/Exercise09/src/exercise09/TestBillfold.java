package exercise09;

public class TestBillfold 
{
	
	public static void main(String[] args)
	{
		IDCard card1 = new IDCard("Dimitris","155");
		CallingCard card2 = new CallingCard  ("Peter","12321323","2222");
		Billfold folder = new Billfold ();
		folder.addCard(card1);
		folder.addCard(card2);
		System.out.println(folder.formatCards());
		
		DriverLicense card4 = new DriverLicense("Dimitris",2005);
		CallingCard card3 = new CallingCard  ("Peter","12321323","2222");
		Billfold folder1 = new Billfold ();
		folder1.addCard(card3);
		folder1.addCard(card4);
		System.out.println(folder1.formatCards());
		System.out.println(folder1.getExpiredCardCount());
		
	}
	
}
