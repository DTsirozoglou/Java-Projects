package exercise08;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A program that adds the three rectangles below to a list,
 * constructs a rectangle comparator, sorts the list, and prints the sorted list and the 
 * expected values.
 */

public class TestRectComp 
{
	public static void main(String[] args)
	{
		// Create three rectangles
		Rectangle rect1 = new Rectangle(5, 10, 20, 30); 
		Rectangle rect2 = new Rectangle(10, 20, 30, 15); 
		Rectangle rect3 = new Rectangle(20, 30, 45, 10);
		
		// Put the rectangles into a list 
		ArrayList<Rectangle> list = new ArrayList<Rectangle>();
		list.add(rect1); 
		list.add(rect2); 
		list.add(rect3); 
		
		// Construct a rectangle comparator
		Comparator<Rectangle> comp = new RectangleComparator();
		
		// Call the library sort method 
		Collections.sort (list, comp);
		
		// Print out the sorted list 
		for (int i = 0; i < list.size(); i++) 
		{
			Rectangle r = list.get(i); 
			System.out.println(r.getWidth() + " " + r.getHeight()); 
		}
	}

}
