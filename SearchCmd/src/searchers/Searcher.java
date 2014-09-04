package searchers;

import java.io.*;

import structures.HTMLlist;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * Searcher is a class that provides us methods for constructing Linked list as HTMLlist and
 * for performing different kind of searches in an HTMLlist.
 *
 */
public class Searcher 
{
	private long elapsedTime;		// The total time to construct the HTMLlist from the file.
	private long SearchelapsedTime; // The total time to perform the search of the word.
	public Searcher()
	{}
	
	/**
	 * Searches if a String exists in the str field of the given HTMLlist.
	 * Returns true if it finds it.
	 * @param l The HTMLlist to be searched.
	 * @param word The string to search in the given HTMLlist.
	 * @return true If there is a match.
	 */
	public static boolean exists (HTMLlist l, String word){
        while (l != null)
        {
            if (l.str.equals (word)) 
                return true;
            l = l.next;
        }
        return false;
    }
    	
	/**
	 * @return The total time to construct the HTMLlist from the file.
	 */
	public long getElapsedTime(){
		return this.elapsedTime;
	}
	
	/**
	 * @return The total time to perform the search of the word.
	 */
	public long getSearchElapsedTime(){
		return this.SearchelapsedTime;
	}
	
    /**
     * Searches if a String exists in the str field of the given HTMLlist.
	 * Navigate through the given HTMLlist and adds to a new HTMLlist all the web sites where
	 * this string is appearing.
     * @param l The HTMLlist to be searched.
     * @param word The string to search in the given HTMLlist.
     * @return the HTMLlist with the web sites where the given word exists or a null HTMLlist if the word 
     * 		   can not be found. 
     */
    public HTMLlist newExists (HTMLlist l, String word){
    	
    	// Start measuring the time of the search.
    	long startTime = System.currentTimeMillis();
    	
    	HTMLlist pagesFound = null;
    	HTMLlist navigate = null;
    	HTMLlist current = null; 
    	HTMLlist tmp = null;
    	String page = null;
    	// We keep a boolean to track if the web page is already in the pagesFound HTMLlist.
    	boolean already;
    	while (l != null){     	      	
    		already = false;
        	if (l.str.startsWith("*PAGE:"))
        		// in that page exists all the words that we are going to read till we
        		// find another page.
        		page = l.str.substring(6);
        	else if (l.str.equals (word)){
        		// the first time we find a match, we add the page that we are currently 
        		// to the pagesFound HTMLlist
        		if (pagesFound==null){
        			pagesFound = new HTMLlist(page,null);
        			current = pagesFound;
        		}
        		else{
        			// if we find a match and we have already added pages to our
        			// pagesFound list, we must navigate through our List to see
        			// if this page is already there or not.
        			navigate = pagesFound;
	        		while (navigate!=null && !already)
	        		{
	        			if (navigate.str == page)
	        				already = true;
	        			navigate = navigate.next;
	        		}
	        		// here we know if this page is already in our HTMLlist
	        		if (already == false){
	        			// if it is not, we update the pagesFound lists with adding this page.
	        			tmp = new HTMLlist(page,null);
	                    current.next = tmp;
	                    current = tmp; 
	        		}
        		}
            }
			l = l.next;
        }	
    	
    	// Get the time of the search when finished.
    	long finishTime = System.currentTimeMillis();
    	this.SearchelapsedTime = finishTime - startTime; // Total time taken.
    	// Return the web pages found for the word requested.
        return pagesFound;
    }
    
	/**
	 * This method takes a given filename tries to read it, and constructs an HTMLlist as a linked list
	 * where each field contains one line of the file, and a pointer to the next line of the file till the
	 * end of the file. The file must have a specific format in order to produce the correct HTMLlist.
	 * @param filename The name of the file from which we want to produce the HTMLllist.
	 * @return The HTMLlist produced by the file given.
	 * @throws IOException if the file cannot be read.
	 * @throws Exception if the first line of the file is null or if it does not start with the String "*PAGE:".
	 */
	public HTMLlist readHtmlList (String filename) throws IOException,Exception
    {
        String name;
        HTMLlist start, current, tmp;

        // Open the file given as argument
        BufferedReader infile = new BufferedReader(new FileReader(filename));

        name = infile.readLine(); //Read the first line
        long startMeasur = System.currentTimeMillis();
        // Throw Exception if the first line is not what it supposed to be.
        if ((name == null)||(!name.startsWith("*PAGE:"))){
        	infile.close();
        	throw new Exception ("Change the Format of the file!"
        			+ "All the websites must start with the keyword : *PAGE:");
        }
        start = new HTMLlist (name, null);
        current = start;
        name = infile.readLine(); // Read the next line
        
        while (name != null) 
        {    // Exit if there is none
        	if (name.startsWith("*PAGE:"))
        		// if it is a web page we store it as it is.
        		tmp = new HTMLlist(name,null);
        	else
        		// if it is a word then we store it to Lower case cause 
        		// we don't want our search engine to be case Sensitive.
        		tmp = new HTMLlist(name.toLowerCase(),null);
            current.next = tmp;
            current = tmp;            // Update the linked list
            name = infile.readLine(); // Read the next line
        }
        infile.close(); // Close the file

        // Return the final HTMLlist of the file.
        long finishedMeasur = System.currentTimeMillis();
        this.elapsedTime = finishedMeasur - startMeasur;
        return start;
    }
    
    
}