package exercise04;
import java.util.Scanner;

public class BetterRockPaperScissors 
{
	
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
		
		// Now we start checking all the possible choices of player 1 winning the game!
		else if (((player1.equals("rock")) && (player2.equals("scissors"))) || ((player1.equals("scissors")) && (player2.equals("paper"))) || ((player1.equals("paper")) && (player2.equals("rock"))))
		{
			System.out.print("Player 1 wins");
		}	
		
		// if none of the above then the winnner is player 2
		else 
		{
				System.out.print("Player 2 wins");
		}
		
	}
}
