package exercise08;

import java.util.Comparator;
import java.awt.Rectangle;
public class RectangleComparator implements Comparator<Rectangle>
{
	/**
	Compares two Rectangle objects.
	@param r1 the first rectangle
	@param r2 the second rectangle
	@return 1 if the area of the first rectangle is larger than the area of
	the second rectangle, -1 if the area of the first rectangle is
	smaller than the area of the second rectangle or 0 if the two
	rectangles have the same area
	*/
	
	public int compare(Rectangle r1, Rectangle r2)
	{
		double area1 = r1.getWidth()* r1.getHeight();
		double area2 = r2.getWidth()* r2.getHeight();
		
		if (area1>area2)
		{
			return 1;
		}
		else if (area1<area2)
		{
			return -1;
		}
		else
		{
			return 0;
		}
		
	} 
}