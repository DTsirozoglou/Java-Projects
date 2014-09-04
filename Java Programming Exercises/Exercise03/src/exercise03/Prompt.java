package exercise03;
import java.util.Scanner;
/**
 * Prompt is a program that prompts the user to enter two integers. Print the smaller of the two 
numbers entered.
 */
public class Prompt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//To obtain a Scanner  object
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the first integer: ");
		int first = scan.nextInt(); 
		System.out.print("Please enter the second integer: ");
		int second = scan.nextInt(); 
		System.out.print("The smaller of the two numbers entered is: ");
		System.out.println(Math.min(first, second));
		scan.close();

	}

}
