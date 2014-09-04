package exercise10;

import java.io.IOException;
import java.util.Scanner;

public class ThrowingIO 
{
	public static void main(String[] args)
	{
		int x;
		try 
		{
			x = getInt();
			System.out.println(x);
		} 
		catch (IOException e) 
		{
			System.out.println(e);
		}
	}

	public static int getInt() throws IOException
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
			throw new IOException();
		}			
	}
}