package exercise04;

import java.util.Scanner;

public class RockPaperScissors {

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Player 1: Choose rock, scissors, or paper:");
		String player1 = scan.next().toLowerCase();
		System.out.println("Player 2: Choose rock, scissors, or paper:");
		String player2 = scan.next().toLowerCase(); 
		scan.close();
		//(your code goes here…)
		
		// we check if it is a tie
		if (player1.equals(player2))
		{
			System.out.print("It is a tie");
		}
		
		// Now we start checking all the possible choices of player 1 
		// compared to player2 excluding the possibility of choosing the same. 
		else if (player1.equals("rock"))
		{
			if (player2.equals("scissors"))
			{
				System.out.print("Player 1 wins");
			}
			else
			{
				System.out.print("Player 2 wins");
			}
		}
		
		else if (player1.equals("paper"))
		{
			if (player2.equals("scissors"))
			{
				System.out.print("Player 2 wins");
			}
			else
			{
				System.out.print("Player 1 wins");
			}
		}
		
		else if (player1.equals("scissors"))
		{
			if (player2.equals("paper"))
			{
				System.out.print("Player 1 wins");
			}
			else
			{
				System.out.print("Player 2 wins");
			}
		}

	}

}
