package exercise05;

import java.util.Scanner;

/**
 * GaussFor is a program for computing the sum of : "1 + 2 + … + n " where "n" is any positive integer that can be inserted by the user.
 * implementation of Gauss program using for loop
 */
public class GaussFor {
	
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
		}
		System.out.println("Sum = " + sum);
		scan.close();
	}

}
