package exercise05;

import java.util.Scanner;
/**
 * Counts the number of years from a year input by the user until the year 3000.
 */
public class CountYears 
{
	public static void main(String[] args) 
	{
		int millennium = 3000;
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the current year: ");
		int year = in.nextInt();
		int nyear = year;
		while (nyear != millennium)
		{
			nyear++;
		}
		System.out.println("Another " + (nyear - year) + " years to the millennium.");
		in.close();
	}
}

// why when i give as the current year a number larger than millennium the programm doesn't loop for ever?
// why we need while? we can only do millenium-year!