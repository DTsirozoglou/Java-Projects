package exercise04;
import java.util.Scanner;

public class CircleOverlap
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Input the radius of the first circle: ");
		double radius1 = in.nextDouble(); 
		double xcenter1 = 0;
		double ycenter1 = 0;
		System.out.print("Input the radius of the second circle: ");
		double radius2 = in.nextDouble(); 
		double xcenter2 = 40;
		double ycenter2 = 0;
		in.close();
		
		if ((radius1 >= (xcenter2 + radius2 )) || (radius2 >= (xcenter2 + radius1)))
		{
			System.out.print("The two circles are mutually contained.");
		}
		else if ((radius1 <= (xcenter2 -radius2 )) ||(radius2 <= (xcenter2 -radius1 )))
		{
			System.out.print("The two circles are disjoint.");
		}
		else
		{
			System.out.print("The two circles are overlapping.");
		}
	}
}