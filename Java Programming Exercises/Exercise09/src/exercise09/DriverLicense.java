package exercise09;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DriverLicense extends Card
{
	private int expiration_year;
	
	public DriverLicense(String n, int year)
	{ 
		super(n);
		expiration_year = year;
	}
	
	public String format()
	{
	  String name = super.format();
	  return "Card Details: " + name + " Expiration Year : " + expiration_year;
	}
	
	public boolean isExpired()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		int currentYear = calendar.get(Calendar.YEAR);
		if (currentYear <expiration_year)
		{
			return false;
		}
		else return true;
	}
	
}
