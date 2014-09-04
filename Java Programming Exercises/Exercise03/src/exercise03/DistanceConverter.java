package exercise03;

import java.util.Scanner;

/**DistanceConverter is a program that converts yards to feet and inches.
 */

public class DistanceConverter {

	public static final int feet = 3;
	public static final int inches = 12;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the the yards to be converted: ");
		double yardsN = scan.nextDouble();
		scan.close();
		
		System.out.println("There are " + feet*yardsN + "feet in " + yardsN + "yards");
		System.out.println("There are " + inches*yardsN + "inches in " + yardsN + "yards");
				
	}
	
}
