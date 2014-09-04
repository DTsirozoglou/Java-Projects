package exercise07;

/**
//Tests the methods of the Geometry and Circle10_2 classes
@param args not used
*/

public class TestGeometry 
{
	public static void main(String[] args)
	{
		// Test the methods of Geaometry class
		System.out.println("The area is : " + Geometry.areaOfcircle(2));
		System.out.println("The circumference is : " + Geometry.circumference(2));
		
		// Test the methods of Circle10_2 class
		Circle10_2 circle = new Circle10_2(2);   //we create an object with radius 2
		System.out.println("The area is : " + circle.areaOfcircle());
		System.out.println("The circumference is : " + circle.circumference());
		
	}

}
