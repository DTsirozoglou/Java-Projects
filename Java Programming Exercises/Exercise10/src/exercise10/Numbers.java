package exercise10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Numbers is a program that read each line of the file numbers.txt,
 * that convert the numbers in each line to doubles, 
 * and finally prints each row of numbers and their total
 *
 */

public class Numbers {
	
	public static void main(String[] args) throws FileNotFoundException {
			
			// Prompt for the input file name
		  Scanner console = new Scanner(System.in);
		  System.out.print("Input file: ");
		  String inputFileName = console.next();
		  console.close();
		  
		// Construct the Scanner and PrintWriter objects for reading and writing
		  
		  File inputFile = new File(inputFileName);
		  Scanner in = new Scanner(inputFile);
		  
		// Read the input and write the output
		  			 		  
			  while (in.hasNextLine())
			  {
				  double rowSum = 0;
				  String line = in.nextLine();
				  Scanner lineScanner = new Scanner(line);
				  
				  while (lineScanner.hasNext())
				  {
					  double num = Double.parseDouble(lineScanner.next());
					  System.out.print(num + " ");
					  rowSum += num;
				  }
				  System.out.println("Has a total of : " + rowSum);
				  lineScanner.close();
			  }
			  in.close();
	}
}
