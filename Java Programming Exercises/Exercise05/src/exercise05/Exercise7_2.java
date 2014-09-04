package exercise05;

import java.util.Scanner;

/**
 * Exercise7_2 (Why we have to use nested loop to produce this output?)
 */
public class Exercise7_2 {
	
	/**
	 * We ask the user to gives us a value for "n", we store that value to "limit" and we loop to get the sum.
	 */
	public static void main(String[] args) 
	{
		// Read the integer "n" inserted from the user
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter any positive integer ");
		int limit = scan.nextInt();
		
		int sum = 0;
		
		for (int i = 0; i <= limit; i++)
		{
			sum = sum + i;
			System.out.println("The sum of positive integers from 0 to " +i+ " is " + sum);
		}
		
		scan.close();
	}

}
