package exercise05;

/**
 * A simple program that prints a loop control variable.
 */
public class SimpleLoop2 {
	public static void main(String[] args) {
		int i = 0;
		int limit = 6;
		while (i < limit) {
			System.out.println("i = " + i);
			//i++;
		}
	}
}

/**
If our program does not have our loop control variable "i" increased in each iteration, "i" will be always smaller
than our limit, and the loop will go forever!
*/