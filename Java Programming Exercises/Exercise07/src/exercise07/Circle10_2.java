package exercise07;

/**
Circle10_2  is a program that can create circles given a radius
and it can compute the  circumference of and the area of an object circle
 */

public class Circle10_2 
{
	private double radius; 
	
	/**Creates an object circle, setting it's radius to the given one. 
	 * @param r the radius of the circle we want to create
	 */
	public Circle10_2 (double r)
	{
		radius = r;
	}
	
	/**A method that gives us the circumference of an object circle
	 * @return the circumference of the circle
	 */
	public double circumference () 
	{
		return (2 * Math.PI * radius);
	}
	
	/**A method that gives us the area of an object circle 
	 * @return the area of the circle
	 */
	public  double areaOfcircle() 
	{
		return (Math.PI * Math.sqrt(radius));
	}

}
