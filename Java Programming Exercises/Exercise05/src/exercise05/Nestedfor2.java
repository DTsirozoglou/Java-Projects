package exercise05;

//Exercise 7.1
public class Nestedfor2 {

	public static void main(String[] args) 
	{		
		for (int i = 1; i <= 5; i++)
		{
			for (int j = 1; j <= i; j++)
			{
				System.out.print("X");
			}

			System.out.println();
		}
	}

}