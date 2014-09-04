package exercise05;

import java.util.Scanner;

/**
 * Gauss is a program for computing the sum of : "1 + 2 + … + n " where "n" is any positive integer that can be inserted by the user.
 *
 */
public class Gauss {
	
	/**
	 * We ask the user to gives us a value for "n", we store that value to "limit" and we loop to get the sum.
	 */
	public static void main(String[] args) 
	{
		// Read the integer "n" inserted from the user
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter any positive integer ");
		int limit = scan.nextInt();
		
		int i = 0;
		int sum = 0;
		
		while (i < (limit+1)) 
		{
			sum = sum + i;
			i ++;
		}
		System.out.println("Sum = " + sum);
		scan.close();
	}

}
