package exercise05;

/**
 * A simple program that prints a loop control variable.
 */
public class SimpleLoope3 {
	public static void main(String[] args) {
		int i = 6;
		int limit = 99;
		while (i < limit) {
			System.out.println("i = " + i);
			i += 2;
		}
	}
}

/**
  The output that the program produces is the following: 
i = 6
i = 8
i = 10
i = 12
i = 14
i = 16
i = 18
...
i = 98
 */