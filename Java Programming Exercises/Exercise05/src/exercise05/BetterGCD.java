package exercise05;
import java.util.Scanner;

/**
 * BetterGCD is a program  that computes the greatest common divisor of multiple pairs of two given integers.
 */

public class BetterGCD 
{
	public static void main(String[] args) 
	{
	
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the first integer or press Q to exit the program: ");	
		
		while (in.hasNextDouble())
		{	
			int x = in.nextInt();
			System.out.println("x = " + x);
			System.out.println("Enter the second integer: ");
			int y = in.nextInt();
			System.out.println("y = " + y);
			
			int larger;
			int smaller;
			
			if (x>y)
			{
				larger = x;
				smaller = y;
				
			}
			
		    // When the number are equal i choose the second number "y" as the larger one.
			else 
			{
				larger = y;
				smaller = x;
			}
			
			// when the number are equal the gcd is this number!
			while (larger != smaller)
			{
				larger = larger - smaller;
				
				if (larger<smaller)
				{
					int temp = larger;
					larger = smaller;
					smaller = temp;
				}	
			}

			System.out.println("the greatest common divisor of two given integers is: "+ larger);
			System.out.println("Enter the next pair of integers or press Q to exit the program: ");	
		}
	    
		in.close();
		System.out.println("Terminated");
		
	}
}