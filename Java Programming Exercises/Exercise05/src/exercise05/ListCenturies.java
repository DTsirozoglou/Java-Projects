package exercise05;

/**
 * ListCenturies is a program that produces a listing of inclusive dates, 
 * from the fifth century B.C. through the fifth century A.D.
 */

public class ListCenturies 
{

	public static void main(String[] args) 
	{
		for (int i = -5; i <= 5; i++)
		{
			if (i <0)
			{
				System.out.println("Century " +Math.abs(i)+ " BC " +((Math.abs(i)-1)*100)+"-"+(Math.abs(i)*100-1));
			}
			else if (i >0)
			{
				System.out.println("Century " +i+ " AD " +((i-1)*100)+"-"+(i*100-1));
			}
		}
	}

}