package exercise04;

import java.util.Scanner;

public class Planets
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a planet number from the sun: ");
		int planet = scan.nextInt();
		// Your code goes here …
		
		if (planet == 1) 
		{
			System.out.print("Mercury");
		}
		else if (planet == 2) 
		{
			System.out.print("Venus");
		}
		else if (planet == 3) 
		{
			System.out.print("Earth");
		}
		else if (planet == 4) 
		{
			System.out.print("Mars");
		}
		else if (planet == 5) 
		{
			System.out.print("Jupiter");
		}
		else if (planet == 6) 
		{
			System.out.print("Saturn");
		}
		else if (planet == 7) 
		{
			System.out.print("Uranus");
		}
		else if (planet == 8) 
		{
			System.out.print("Neptune");
		}
		else
		{
			System.out.print("Please enter an Integer number from 1 to 8: ");
		}
		scan.close();
	}
}
