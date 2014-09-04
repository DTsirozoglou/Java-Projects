package exercise10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * ZipsReader is a program for changing the Zip Code format of a given file and prints the renewed data
 * to a file specified by the user. 
 *
 */
public class ZipsReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		// Prompt for the input and output file names
		
		  Scanner console = new Scanner(System.in);
		  System.out.print("Input file: ");
		  String inputFileName = console.next();
		  System.out.print("Output file: ");
		  String outputFileName = console.next();
		  console.close();
		  
		// Construct the Scanner and PrintWriter objects for reading and writing
		  
		  File inputFile = new File(inputFileName);
		  Scanner in = new Scanner(inputFile);
		  PrintWriter out = new PrintWriter(outputFileName);
		  
		// Read the input and write the output
		 		  
		  while (in.hasNextLine())
		  {
			  String line = in.nextLine();
			 
			  // passes each line of the table file as a String to another Scanner constructor
			  Scanner lineScanner = new Scanner(line);
			  String abbreviation = lineScanner.next(); 
			  String name = lineScanner.next(); 
			 
			  // Search all the tokens of the line
			  while (lineScanner.hasNext())
			  {
				  String token = lineScanner.next();
				  
				  // case the token is a three digits number we change how we will print it
				  if (token.matches("\\d{3}"))
				  {
					  name = name +" "+ token +"-"+token;
				  } 
				  else 
					  {
					  	name = name +" "+ token;
					  }
			  }
			  
			  //print to our new .txt the desired output.
			  out.println(abbreviation + " " + name);
			  lineScanner.close();
		  }
		   		  
		  in.close();
		  out.close();
	}
}
