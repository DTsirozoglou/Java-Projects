package searchCmd;

import java.io.*;
import java.util.Scanner;
import java.util.Set;

import searchers.HashFunction;
import structures.HTMLlist;
import structures.HashTable;

/**
 * @author Dimitrios TSirozoglou
 * 
 * SearchCmdAdvanced is an advanced implementation of a search engine.The program again takes the name of the data file 
 * used for searching as a parameter.In the program, the given parameter is accessible in args[0] where args
 * is the argument to the main method. The program then constructs a HashTable using chained hashing as in the SearchCmd4. 
 * After construction of this data structure, a simple user interface launches, enabling the user 
 * to query the search engine. The difference here is we have added support for the boolean operators AND and OR to 
 * the search engine. The resulting search engine should accept three kinds of input strings::
 * A simple word as before, where the engine informs the user whether or not the word was present in the input and if it was found 
 * the URLs of all pages where this word occurs are written to the screen.
 * A word AND another word,where the engine informs the user whether or not the words were present in the input and 
 * if they were found, the URLs of all pages where both words occur are written to the screen.
 * A word OR another word,where the engine informs the user whether or not the words were present in the input and 
 * if they were found, the URLs of all pages where those words occur are written to the screen.
 * In case of AND and OR, if one of the word can not be found in our HashTable, we give the user only the urls of the 
 * other word, informing him at the same time which of the words wasn't found.
 */
public class SearchCmdAdvanced
{
	public SearchCmdAdvanced(String file)
	{
		
		HashFunction searcher =new HashFunction();
		String line;
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
	            line = inuser.readLine(); // Read a line from the terminal
	            
	            if (line == null || line.length() == 0) 	            	
	                quit = true; 
	            else 
	            {
	            	String symbol= "";
	            	String word1,word2;	   
	            	// passes the line read as a String to a Scanner constructor
	                Scanner lineScanner = new Scanner(line);
	                // The words of the input must be separated with a space.
	        		lineScanner.useDelimiter(" ");
	        		// we make the word that we want to search to lower case 
		            // because our search engine is not case sensitive.
	        		word1 = lineScanner.next().toLowerCase();
	        		if (!lineScanner.hasNext())
	        		{//If there is anything else following the word we perform our usual search.
	        			HTMLlist result = searcher.exists(l, word1);
	                	if (result.str == null)
	                		System.out.println ("The word \""+word1+"\" has not been found.");
	                	else 
	                		System.out.println ("The word \""+word1+"\" has been found in the following Web Sites : "+ result.toString());
	           		}
	        		else
	        		{//If it has next. it must be one of the supported symbols AND or OR 
	        			symbol = lineScanner.next();
	        			if ((!symbol.equals("AND"))&(!symbol.equals("OR")))
	        					//here we know that the user made an input mistake.
	        					System.out.println("Incorrect Syntax used!");
	        			else if ((symbol.equals("AND"))||(symbol.equals("OR")))
	        					{// If it is one of the supported symbols we check what is next
	        						if (!lineScanner.hasNext())
	        							//if there is nothing next we inform the user of making an input mistake.
	        							System.out.println("Incorrect Syntax used!");
	        						else
	        						{//here we take the second word we want to search
	        							word2 = lineScanner.next().toLowerCase();
	        							// We store the results of urls of both words.
	        							HTMLlist result1 = searcher.exists(l, word1);
	        							HTMLlist result2 = searcher.exists(l, word2);
	        							if ((result1.str !=null)&(result2.str !=null)){
	        								if (symbol.equals("AND")){
	        									//if the symbol is AND we call andHTMLlist method
	        									//and print the result of the method in the screen.
	        									Set<String> result3 = searcher.andHTMLlist(result1, result2);
		            							System.out.println("The list of pages where both word “"+word1+"” and “"+word2+"” occur is :");
		            							searcher.displayResult(result3);
	        								}
	        								else{
	        									//Here we know that we have an OR symbol so we we call orHTMLlist method
	        									//and print the result of the method in the screen.
	        									Set<String> result3 = searcher.orHTMLlist(result1, result2);
		            							System.out.println("The list of pages where either word “"+word1+"” or “"+word2+"” occur is :");
		            							searcher.displayResult(result3);	        									
	        								}
	        							}
	        							//none of the words have urls.
	        							else if ((result1.str==null)&(result2.str==null))
	        								System.out.println ("The words have not been found.");
	        							//The first word has not been found in our HashTable.
	        							else if (result1.str==null){
	        								System.out.println ("The word \""+word1+"\" has not been found.");
	        								System.out.println ("The word \""+word2+"\" has been found in the following Web Sites : "+ result2.toString());	        								
	        							}
	        							//The second word has not been found in our HashTable.
	        							else{
	        								System.out.println ("The word \""+word2+"\" has not been found.");
	        								System.out.println ("The word \""+word1+"\" has been found in the following Web Sites : "+ result1.toString());
	        							}     		        			
	        						}	        				
	        					}
    	            }
	        		lineScanner.close();
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
        new SearchCmdAdvanced(args[0]);        
    }
}