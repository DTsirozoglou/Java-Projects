package searchCmd;

import java.io.*;

import searchers.HashFunction;
import structures.HTMLlist;
import structures.HashTable;


/**
 * @author Dimitrios TSirozoglou
 * 
 * SearchCmd4 is a better implementation of a search engine.The program again takes the name of the data file 
 * used for searching as a parameter.In the program, the given parameter is accessible in args[0] where args
 * is the argument to the main method. The program then constructs a HashTable using chained hashing. 
 * This means that each entry in the HashTable contains a reference to an HTMLlist object containing two fields. 
 * The two fields are str and next. In the field str we store the word and in the linked list next we store all the
 * Pages that this word occurs. 
 * The use of a HashTable will have a drastic effect on both the time it takes to initialise the search engine
 * and the time it takes to perform individual searches.
 * After construction of this data structure, a simple user interface launches, enabling the user 
 * to query the search engine:
 * Given a word, the engine informs the user whether or not the word was present in the input and if it was found 
 * the URLs of all pages where this word occurs are written to the screen.
 */
public class SearchCmd4 
{
	public SearchCmd4(String file)
	{		
		HashFunction searcher =new HashFunction();
		String name;
		// Read the file and create the Hash Table
		HashTable l;
		try{
			l = searcher.readHashFunction(file);
			// Ask for a word to search
			BufferedReader inuser =
		            new BufferedReader (new InputStreamReader (System.in));

	        System.out.println ("Hit return to exit.");
	        boolean quit = false;
	        
	        while (!quit) 
	        {
	            System.out.print ("Search for: ");
	            // we make the word that we want to search to lower case 
	            // because our search engine is not case sensitive.
	            name = inuser.readLine().toLowerCase(); // Read a line from the terminal
	            
	            if (name == null || name.length() == 0)
	                quit = true; 
	            else{
	            	HTMLlist result = searcher.exists(l, name);
	            	if (result.str == null)
	            		System.out.println ("The word \""+name+"\" has not been found.");
	            	else
	                System.out.println ("The word \""+name+"\" has been found in the following Web Sites : "+ result.toString());
	            }
	        }
		}
		catch(IOException io){System.out.println("File not found");}   
		catch(Exception io){System.out.println(io);}  
	}

    public static void main (String[] args) throws IOException 
    {      
        // Check that a filename has been given as argument
        if (args.length != 1) 
        {
            System.out.println("Usage: java SearchCmd <datafile>");
            System.exit(1);
        }
        new SearchCmd4(args[0]);        
    }
}