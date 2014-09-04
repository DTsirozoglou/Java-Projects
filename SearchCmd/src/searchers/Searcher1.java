package searchers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import structures.HTMLlist;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * Searcher1 is a class that provides us methods for constructing Linked list consists
 * of HTMLlist objects containing three fields,and for performing different kind of searches in an HTMLlist.
 *
 */
public class Searcher1 {
	private long elapsedTime;		// The total time to construct the HTMLlist from the file.
	private long SearchelapsedTime; // The total time to perform the search of the word.
	public Searcher1(){
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
	 * This method takes a given filename tries to read it, and constructs an HTMLlist as a linked list.
	 * Each field of the list contains an HTMLlist with a unique word, an HTMLlist with the urls that this word appears
	 * and a pointer to the next field of the linked list.
	 * The file must have a specific format in order to produce the correct HTMLlist.
	 * @param filename The name of the file from which we want to produce the HTMLllist.
	 * @return The HTMLlist produced by the file given.
	 * @throws IOException if the file cannot be read.
	 * @throws Exception if the first line of the file is null or if it does not start with the String "*PAGE:".
	 */
	public HTMLlist readHtmlList (String filename) throws IOException,Exception 
    {
        String name;
        HTMLlist start, current, tmp, urls,currentUrl,tempUrl,urls2,listUrl;

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
        // Add this name to our urls HTMLlist.
        urls = new HTMLlist(name.substring(6),null);
        currentUrl = urls;        
        // Read the next line to Lower Case cause we want to store all the words appearing
        // in the web sites to lower case
        name = infile.readLine().toLowerCase(); 
        // Store the last read name and the created urls list to the HTMLlist we are going to return. 
        start = new HTMLlist (name, null, urls);// This is out first word in the list that appears in this url.
        current = start;
        name = infile.readLine(); // Read the next line
        
        while (name != null)
        {// Exit if there is none        
        	
        	while ((name != null) && (!name.contains("*PAGE:")))
	        {// We are reading only words here. 
	            if (!Searcher.exists (start, name.toLowerCase()))
	            {// if the word we read isn't already in our start list we add it.
	            	tmp = new HTMLlist(name.toLowerCase(),null,currentUrl);
	                current.next = tmp;
	                current = tmp;            // Update the linked list
	                name = infile.readLine(); // Read the next line
	            }
	            else 
	            {// If the word is already in the start list, we must update 
	            	// its urls list if needed.
	            	listUrl = this.exists(start, name.toLowerCase()); // We store the HTMLlist of the word's Urls.
	            	urls2 = addToHTMLlist(listUrl, currentUrl.str); // We try to add the current url to the above list.
	            	updateUrl(start,name.toLowerCase(),urls2); // We update the list of urls of the word.
	            	name = infile.readLine(); // Read the next line
	            }
	        }        	
            if (name != null)
            {// Here we read a Page.So we make our currentUrl pointer, point to this page
		        String test = name.substring(6);
		        tempUrl = new HTMLlist(test,null);
		        currentUrl = tempUrl;
		        name = infile.readLine(); // Read the next line
            }
        }
        infile.close(); // Close the file
        
        long finishedMeasur = System.currentTimeMillis();
        this.elapsedTime = finishedMeasur - startMeasur;
        // Return the final HTMLlist of the file.
        return start;
    }
    
    /**
     * This method searches for the given word in the given HTMLlist. If the word is found it returns
     * the url field of the given HTMLlist where the word is found.
     * @param l The HTMLlist to be searched.
	 * @param word The string to search in the given HTMLlist.
     * @return The HTMLlist of the urls that the given word appears.
     */
    public HTMLlist exists (HTMLlist l, String word){
    	// Start measuring the time of the search.
    	long startTime = System.currentTimeMillis();
    	
    	HTMLlist urls=null;    
        while (l != null){
            if (l.str.equals (word)){
                urls = l.url;
                return urls;
            }
            l = l.next;
        }
        
        // Get the time of the search when finished.
    	long finishTime = System.currentTimeMillis();
    	this.SearchelapsedTime = finishTime - startTime; // Total time taken.
        return urls;
    } 
    
    /**
     * This method adds a given string in the given HTMLlist, if this string does not already exists in the HTMLlist.
     * It returns a new HTMLlist with or without the string.
     * @param url The HTMLlist we want to add the given String.
     * @param ur The string that we want to add.
     * @return A new updated HTMLlist.
     */
    public static HTMLlist addToHTMLlist (HTMLlist url, String ur)
    {
    	String next;
    	HTMLlist urls,current,tmp;
    	// This is the HTMLlist we are going to return. We start by initialising  
    	// with the given String.
    	urls = new HTMLlist(ur,null);
    	current = urls;
    	
    	// If the given HTMLlist is null, return the urls we have created.
    	if (url.str == null)
    		return urls;
    	next = url.str;	
    	
    	// If this String already exists then return the given HTMLlist as it was.
    	if(Searcher.exists(url, ur))
    		return url;
    	else
    	{// If it does not exist we must add it!
	        while (next != null)
	        {// Exit if there is none
                 tmp = new HTMLlist(next,null);
                 current.next = tmp;
                 current = tmp;            // Update the urls list
                 if (url.next != null) // Read the next line if not null
                 {
                	 next = url.next.str;
                	 url=url.next;
                 }
                 else // We don't have anything else to add.
                	 break;
	        }
    	}	      
    	// Return the List we created.
        return urls;
      }
    
	/**
	 * This method updates the Urls HTMLlist (the url field) of a word stored in a Linked list.
	 * @param start The linked list to update.
	 * @param name  The word that it's urls will be updated.
	 * @param urls2 The HTMLlist containing the new urls of the word given.
	 */
	private void updateUrl(HTMLlist start,String name,HTMLlist urls2)
    {
    	while (start != null){    		
            if (start.str.equals (name)){
               start.url = urls2;
               break;
            }
            start = start.next;
        }
    }
}
