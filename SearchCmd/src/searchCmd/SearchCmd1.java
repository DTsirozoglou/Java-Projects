package searchCmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import searchers.Searcher;
import structures.HTMLlist;

/**
 * @author Dimitrios TSirozoglou
 * 
 * SearchCmd1 is an implementation of a small search engine. The program takes the name of the data file 
 * used for searching as a parameter.In the program, the given parameter is accessible in args[0] where args
 * is the argument to the main method. The program then constructs a linked list (see data structures) with 
 * all the lines from the data file given in args[0]. The objects in the linked list contain the fields str and next.
 * The field str contains a line from the data file and next points to the next object in the list. 
 * After reading the data file, a simple user interface launches, enabling the user to query the search engine:
 * Given a word, the engine informs the user whether or not the word was present in the input.
 */
public class SearchCmd1 {

    public static void main (String[] args) throws IOException {
        String name;

        // Check that a filename has been given as argument
        if (args.length != 1) {
            System.out.println("Usage: java SearchCmd <datafile>");
            System.exit(1);
        }

        Searcher searcher =new Searcher();
        // Read the file and create the linked list
        HTMLlist l = new HTMLlist (null,null);
		try {
			l = searcher.readHtmlList (args[0]);
		
        // Ask for a word to search
	        BufferedReader inuser =
	            new BufferedReader (new InputStreamReader (System.in));
	
	        System.out.println ("Hit return to exit.");
	        boolean quit = false;
	        while (!quit) {
	            System.out.print ("Search for: ");
	            // we make the word that we want to search to lower case 
	            // because our search engine is not case sensitive.
	            name = inuser.readLine().toLowerCase(); // Read a line from the terminal
	            if (name == null || name.length() == 0) {
	                quit = true;
	            } else if (Searcher.exists (l, name)) {
	                System.out.println ("The word \""+name+"\" has been found.");
	            } else {
	                System.out.println ("The word \""+name+"\" has NOT been found.");
	            }
            }
		}
        catch(IOException io){System.out.println("File not found");}   
        catch(Exception io){System.out.println(io);}  
	}
}