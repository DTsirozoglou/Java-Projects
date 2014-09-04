package exercise06;

/**
 * Arrays is a program that tests a method that is passed an array x of doubles and an integer rotation amount n. 
 * the method rotate creates a new array with the items of x moved forward by n positions. 
 */

public class Arrays
{
	public static void main(String[] args)
	{
		double[] x = {8, 4, 5, 21, 7, 9, 18, 2, 100};
		System.out.println("Before rotation:");
		
		for (int i = 0; i < x.length; i++)
		{
			System.out.println("x[" + i + "]: " + x[i]);
		}
		
		double[] f = rotate(x, 12);
		System.out.println("After rotation:");
		
		for (int i = 0; i < f.length; i++)
		{
			System.out.println("f[" + i + "]: " + f[i]);
		} 
	}
// Your method goes here.	
	
	/**
	 * The method rotate takes two parameters and returns an array.
	 * @param x is an array x of doubles
	 * @param n is an integer rotation amount
	 * @return  a new array with the items of x moved forward by n positions
	 */
	
	public static double[] rotate(double[] x, int n)
	{
		double[] temp= new double[x.length];
		
		// We check and we fix the rotation if needed.
		while (n>x.length)
		{
			n = n - x.length;
		}
		
		// We create a new table with the elements of x rotated.
		for (int i = 0; i < x.length; i++)
		{
			if (i<n)
			{
				temp[(x.length - n)+i] = x[i];
			}
			else 
			{
				temp[i-n] = x[i];
			}
		}
		
		return temp;
				
	}
	
	
}