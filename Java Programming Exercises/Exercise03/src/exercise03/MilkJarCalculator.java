package exercise03;

public class MilkJarCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				double milk = 5.5; // gallons
		double jarCapacity = 0.75; // gallons
		int completelyFilledJars = (int) (milk / jarCapacity);
	// The wrong with the previous program was that it was trying to store a double value to an integer.
	// I used cast (int) function to the result of (milk / jarCapacity) 
	// as we need only the completely filled jars.So we just discard the fractional part 	
		System.out.println(completelyFilledJars);
		

	}

}
