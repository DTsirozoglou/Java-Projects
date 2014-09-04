package exercise07;

/**
Geometry is a program that can compute the  circumference of a circle and the area of a circle, given the radius r
 */

public class Geometry 
{
	
	/**A method that computes the circumference of a circle with a given radius r
	 * @param r is the given radius
	 * @return the circumference
	 */
	public static double circumference(double r) 
	{
		return (2 * Math.PI * r);
	}
	
	/**A method computes the area of a circle with a given radius r 
	 * @param r is the given radius
	 * @return the area of a circle
	 */
	public static double areaOfcircle(double r) 
	{
		return (Math.PI * Math.sqrt(r));
	}

}
