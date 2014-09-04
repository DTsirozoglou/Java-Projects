package exercise10;

import java.util.Scanner;

public class Throwing
{
	public static void main(String[] args)
	{
		int x = getInt();
		System.out.println(x);
	}

	public static int getInt() throws IllegalArgumentException
	{
	// your code goes here
		Scanner console = new Scanner(System.in);
		System.out.print("Enter an integer: ");
		if (console.hasNextInt())
		{				
			String input = console.next();
			int x = Integer.parseInt(input);
			console.close();
			return x;
		}
		
		else 
		{
			console.close();
			throw new IllegalArgumentException("This is not an Integer value");
		}
	}
}