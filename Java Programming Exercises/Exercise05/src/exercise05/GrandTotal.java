package exercise05;

import java.util.Scanner;

/**
 * GrandTotal is a program that reads values from the user and then it prints the grand total 
 * and the number of entries the user typed.
 *
 */
public class GrandTotal
{
	
	/**
	 * We ask the user to gives us a value for "n", we store that value to "limit" and we loop to get the sum.
	 */
	public static void main(String[] args) 
	{
		// Read the integer "n" inserted from the user
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a number : positive, negative, or zero, or press q to quit : ");
		double sum = 0;
		int counter = 0;
		
		// we will exit the loop when read from the scanner a value that is not double.
		while (scan.hasNextDouble())
		{
			double value = scan.nextDouble();
			sum += value;
			counter++;
		} 
		System.out.println("the grand total is: " + sum +" and the number of entries were: " + counter);
		scan.close();
	}

}