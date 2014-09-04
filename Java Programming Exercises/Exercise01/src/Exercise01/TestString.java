package Exercise01;

/**
A program that constructs a String object and tests the methods toLowerCase and to UpperCase 
*/

public class TestString {
	
	public static void main(String[] args) {
		
		String testString = "This is a Test";
		String smallTestString = testString.toLowerCase();
		System.out.println(smallTestString);
		
		String bigTestString = smallTestString.toUpperCase();
		System.out.println(bigTestString);

	}
}
