package exercise04;

public class BadIfs
{
	public static void main(String[] args)
	{
		int x = 9;
		int y = 3;
		int z = 7;
	
		if (x < y)
		{
			if (x < z)
			{
				System.out.println("aaa");
			
			}
			else
			{
			System.out.println("bbb");
		    }
		}
		
		else 
		{
			System.out.println("ccc"); 
		}
		
		System.out.println("ddd");
		
		if (y > z)
		{
			if (z > x) 
			{
				System.out.println("eee"); 
			}
			else
			{ 
				System.out.println("fff");
			}
		}
		else 
		{
			System.out.println("ggg");
		} 
	}
}