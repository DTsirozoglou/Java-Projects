package exercise09;

public class CallingCard extends Card
{
	private String card_Number;
	private String pIN;
	
	public CallingCard(String n, String id,String pin)
	{ 
		super(n);
		card_Number = id;
		pIN = pin;
	}
	
	public String format()
	{
	  String name = super.format();
	  return "Card Details: " + name + " Card Number : " + card_Number + " Card PIN : " + pIN;
	}
}
