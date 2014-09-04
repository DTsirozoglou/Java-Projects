package Exercise01;

import java.awt.Rectangle;

/**
The following program creates a new Rectangle 
prints its coordinates and can apply the grow method
*/

public class RectanglePrinter 
{

	public static void main(String[] args) 
	{
		Rectangle r1 = new Rectangle(0, 0, 100, 50);
		Rectangle r2 = new Rectangle(0, 0, 100, 50);
		//Rectangle r2 = r1;
		// Applying the mutator grow
		r2.grow(10, 20);		
		System.out.println(r1);
		System.out.println(r2);
	}

}
