package searchCmd;

import java.io.*;

import searchers.Searcher1;
import structures.HTMLlist;

/**
 * @author Dimitrios TSirozoglou
 * 
 * SearchCmd3 is an implementation of a search engine.The program again takes the name of the
 * data file used for searching as a parameter.In the program, the given parameter is accessible in args[0] where args
 * is the argument to the main method. The program then constructs a linked list with all the lines from the data file
 * given in args[0]. The objects in  This linked list consists of HTMLlist objects containing three fields. 
 * The three fields are str, next and urls. The fields str and next are like in SearchCmd, but we will now only 
 * use the str field to store words (i.e. not Pages) and each word only occurs once. The object’s urls field will
 * be a pointer to a list of URLs of all pages that contain the word in the str field. 
 * A list of URLs consists of objects with two fields; next and str.The field next is a pointer to the next object
 * in the list and the field str is a URL String.
 * After construction of this data structure, a simple user interface launches, enabling the user 
 * to query the search engine:
 * Given a word, the engine informs the user whether or not the word was present in the input and if it was found 
 * the URLs of all pages where this word occurs are written to the screen.
 */
public class SearchCmd3 
{
	public SearchCmd3(String file)
	{  	        
		Searcher1 searcher =new Searcher1();
		String name;
		// Read the file and create the linked list
		HTMLlist l = new HTMLlist (null,null,null);
		try{
			l = searcher.readHtmlList (file);
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
	            else if (searcher.exists(l, name)==null)
	            	System.out.println ("The word \""+name+"\" has not been found.");	           
	            else 
	                System.out.println ("The word \""+name+"\" has been found in the following Web Sites :"
	                		+ " "+ searcher.exists (l, name).toString());
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
        new SearchCmd3(args[0]);        
    }
}