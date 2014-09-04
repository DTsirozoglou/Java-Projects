package exercise06;

public class Peevish
{
	public static void main(String[] args)
	{
		boolean[] door;
		
		// We will not use door[0]
		final int NODOORS = 101;
		final boolean OPEN = true;
		final boolean CLOSED = false;
		
		// Initialise the doors
		door = new boolean[NODOORS];
		
		for (int i = 0; i < NODOORS; i++)
		{
			door[i] = CLOSED;
		}
		// Print the state of each door (only doors 1-100)
		for (int i = 1; i < NODOORS; i++)
		{
			if (door[i] == true)
			{
				System.out.println("Door " + i + " is open.");
			} 
			else
			{
				System.out.println("Door " + i + " is closed.");
			}
		}
		// Your code here
		
		
		//The inner loop control the door number visited on a single pass,
		//and the outer loop control the number of passes.
		//The pattern door it is an object with two states represented by an array.
		
		for (int j =1; j <101;j++)
		{
			for (int i = j; i < NODOORS; )
			{
				if (door[i] == true)
				{
					door[i] = CLOSED;
				} 
				else
				{
					door[i] = OPEN;
				}
				i = j+i;
			}
		}
		
		//Print the state of each door after the 100th pass.
		for (int i = 1; i < NODOORS; i++)
		{
			if (door[i] == true)
			{
				System.out.println("The Door " + i + " after the 100th pass is open.");
			} 
			else
			{
				System.out.println("Door " + i + " after the 100th pass is closed.");
			}
		}
		
	}
}