package exercise05;

import java.util.Scanner;

/**
 * EvenOrOdd is a program that Keeps up five numbers of even and odd integers the user types,
 * and print “Done” when finished, so the user won’t try to type another integer.
 * Finally, it prints out the number of even and odd integers that were entered.
 */
public class EvenOrOdd {
	
	/**
	 * We ask the user to gives us a value for "n", we store that value to "limit" and we loop to get the sum.
	 */
	public static void main(String[] args) 
	{
		
		Scanner scan = new Scanner(System.in);
		
		int evenCounter = 0;
		int oddCounter  = 0;
		
		for (int i = 0; i <= 4; i++)
		{
			// Read the integer "number" inserted from the user
			System.out.print("Enter any integer ");
			int number = scan.nextInt();
			
			// check if the number is even or odd
			if ((number / 2) * 2 == number)
			{
				System.out.println("The integer " + number+" is even");
				evenCounter++;
			}
			else
			{
				System.out.println("The integer " + number+" is odd");
				oddCounter++;
			}
		}
		
		System.out.println("Done");
		System.out.println("the number of even that were entered: "+ evenCounter);
		System.out.println("the number of odd that were entered: "+ oddCounter);
		scan.close();
	}

}
