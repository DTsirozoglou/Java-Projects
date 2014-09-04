package exercise10;

import java.util.Scanner;
public class TextIO
{
	public static void main(String[] args)
	{
		String input1 = "Now is the time for all good men to come to the aid of their country.";
		String input2 = "abcdefghijklmnopqrstuvwxyz0123456789";
		String input3 = "a1b2c3 d4";
		String input4 = "Line1\nLine2\nLine3\nLine4";
		
//		a) Read String input1 with a Scanner and print each word.
		Scanner a = new Scanner(input1);
		System.out.println("a) Print each word of input1 :");
		while (a.hasNext())
		{
			System.out.println(a.next());
		}
		a.close();
		
//		b) Read String input2 with a Scanner and print each character.
		Scanner b = new Scanner(input2);
		System.out.println("b) Print each character of input2 :");
		b.useDelimiter("");
		while (b.hasNext())
		{
			System.out.println(b.next());
		}
		b.close();
		
//		c) Read String input3 with a Scanner and print each character and whether it is a letter, a digit, or whitespace.
		Scanner c = new Scanner(input3);
		System.out.println("c) Print each character and whether it is a letter, a digit, or whitespace of input2 :");
		c.useDelimiter("");
		while (c.hasNext())
		{
			Character scanit = c.next().charAt(0);  
			if (Character.isDigit(scanit))
			{
				System.out.println(scanit +" is a digit!");
			}
			else if (Character.isLetter(scanit))
			{
				System.out.println(scanit +" is a Letter!");
			}
			else
			{
				System.out.println(scanit +" is a White Space!");
			}
		}
		c.close();
		
//		d) Read String input4 with a Scanner and print each line on a different line.
		
		Scanner d = new Scanner(input4);
		System.out.println("d) Print each line on a different line of input4 :");
		while (d.hasNext())
		{
			System.out.println(d.next());
		}
		d.close();
		
	}
} 
