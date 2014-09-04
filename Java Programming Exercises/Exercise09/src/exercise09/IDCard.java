package exercise09;

public class IDCard extends Card
{	
	private String idNumber;
	
	public IDCard(String n, String id)
	{ 
		super(n);
		idNumber = id;
	}
	
	public String format()
	{
	  String name = super.format();
	  return "Card Details: " + name + " ID number : " + idNumber;
	}
	
}
