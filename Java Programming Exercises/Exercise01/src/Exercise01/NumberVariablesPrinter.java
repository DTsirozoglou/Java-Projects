package Exercise01;

public class NumberVariablesPrinter {

	public static void main(String[] args) 
	{
		double n1 = 150;
		double n2 = n1;
		n2 = n2 * 20; // Grow n2
		System.out.println(n1);
		System.out.println(n2);
	}

}
