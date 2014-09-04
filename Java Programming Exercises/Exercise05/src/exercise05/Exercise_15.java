package exercise05;
import java.util.Random;
 
/**
A program that simulates the drawing of one card
*/ 
public class Exercise_15 
{
	public static void main(String[] args) 
	{
		Random generator = new Random();
		int color = generator.nextInt(3);
		int card = generator.nextInt(12);
		String number;  
		String suit;
		
		if (card==0)
		{
			number = "Ace";
		}
		else if (card==1)
		{
			number = "Two";
		}
		else if (card==2)
		{
			number = "Three";
		}
		else if (card==3)
		{
			number = "Four";
		}
		else if (card==4)
		{
			number = "Five";
		}
		else if (card==5)
		{
			number = "Six";
		}
		else if (card==6)
		{
			number = "Seven";
		}
		else if (card==7)
		{
			number = "Eight";
		}
		else if (card==8)
		{
			number = "Nine";
		}
		else if (card==9)
		{
			number = "Ten";
		}
		else if (card==10)
		{
			number = "Jack";
		}
		else if (card==11)
		{
			number = "Queen";
		}
		else 
		{
			number = "King";	
		}
		
		if (color == 0)
		{
			suit = "Spades";
		}
		else if (color == 1)
		{
			suit = "Hearts";
		}
		else if (color == 2)
		{
			suit = "Diamonds";
		}
		else 
		{
			suit = "Clubs";
		}
		System.out.println("This is " +number+ " of "+suit);
	}
}
