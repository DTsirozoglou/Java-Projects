package exercise05;

/**
 * A simple program that prints a loop control variable.
 */
public class SimpleLoop {
	public static void main(String[] args) {
		int i = 0;
		int limit = 6;
		while (i < limit) {
			System.out.println("i = " + i);
			i++;
		}
	}
}

/**
  The output that the program produces is the following: 
i = 0
i = 1
i = 2
i = 3
i = 4
i = 5

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