package exercise05;

import java.util.Scanner;

/**
 * GCD is a program that that computes the greatest common divisor of two given integers.
 */

public class GCD 
{
	public static void main(String[] args) 
	{
	
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the first integer: ");
		int x = in.nextInt();
		System.out.println("x = " + x);
		System.out.println("Enter the second integer: ");
		int y = in.nextInt();
		System.out.println("y = " + y);
		
		// Your gcd computation code goes here
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
	    
		in.close();
		System.out.println("the greatest common divisor of two given integers is: "+ larger);
		
	}
}