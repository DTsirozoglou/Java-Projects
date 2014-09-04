package exercise06;

import java.util.Scanner;

/**
 * Exercise1 is a program that contains a few experiments with arrays. 
 */

public class Exercise1 {

	public static void main(String[] args) 
	
	{
		//Create an array x of doubles with an initialiser list that contains the following values: 
		// 8, 4, 5, 21, 7, 9, 18, 2, 100
		double[] array =  {8, 4, 5, 21, 7, 9, 18, 2, 100};
		
		//Print the number of items in the array by using the expression x.length.
		System.out.println("The number of items in the array is: " + array.length);
		
		//Print the first array item x[0].
		System.out.println("The first array item is: " + array[0]);
		
		//Print the last array item.Be careful to choose the right index.
		System.out.println("The last array item is: " + array[8]);
		
		//Print the expression x[x.length – 1]. Why is this value the same as in part d?
		System.out.println("The last array item is: " + array[array.length-1]);		//the value is the same as before, 
																					//because the number of the index now is 
																					//our array length witch is 9 minus 1.
		//Use a standard for loop to print all the values in the array with labels.
		//For instance, you can print like: x[3]: 21 
		for (int i = 0; i <= array.length-1; i++)
		{	
			System.out.println("The array item: array["+i+"] is: " + array[i]);
		}
		
		//Use a standard for loop to print all the values in the array in reverse order, with labels.
		//For instance, you can print like: x[3]: 21
		for (int j = 8; j >= 0;j--)
		{	
			System.out.println("The array item: array["+j+"] is: " + array[j]);
		}
		
		//Use an enhanced for loop to compute the sum of all elements in the array and then print it.
		double sum=0;
		for (double element:array)
		{
			sum = sum + element;			
		}
		System.out.println("The sum of the elements of the array is: " + sum);
		
		//Use a for loop to find a value read from System.in in the array.
		//If no such value exists, print an appropriate message. 
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the double to be checked:");
		
		double val = scan.nextDouble();
		int counter = 0;
		
		for (double element:array)
		{	
			if (element == val)
			{
				counter++;
			}						
		}
		
		if (counter == 0)
		{
			System.out.print(" No such value exists in the array" );
		}
		scan.close();
		
	}

}
