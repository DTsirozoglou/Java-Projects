package searchers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet; // Is only used for the Advanced Part.
import java.util.Set; // Is only used for the Advanced Part.

import structures.HTMLlist;
import structures.HashTable;

/**
 * @author Dimitrios Tsirozoglou
 * HashFunction is a Class that provides us with methods for finding a hash value for a String,
 * for constructing a Hash Table from a specific formated file, and for performing searches of strings in
 * a Hash Table.
 */
public class HashFunction {
	private long elapsedTime;		// The total time to construct the HTMLlist from the file.
	private long SearchelapsedTime; // The total time to perform the search of the word.

	public HashFunction (){		
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
	 * This method computes an integer hash value (in the range of the given number) for a given String and returns it.
	 * @param wordToHash The String that we want to know it's value
	 * @param arrSize The range of the number our hash key will have. 
	 * @return The hash value of the given String.
	 */
	public static int stringHashFunction(String wordToHash, int arrSize) {
		int hashKeyValue = 0;				
		for (int i = 0; i < wordToHash.length(); i++) 			
			hashKeyValue = ( hashKeyValue * 271 + (wordToHash.charAt(i)-96)  ) % arrSize;
		return hashKeyValue;		
	}		
	
	/**
	 * This method takes a given filename tries to read it, and constructs a HashTable using chained hashing. 
	 * This means that each entry contains a reference to an HTMLlist. Each field of the HTMLlist
	 * contains a unique word and an HTMLlist with the urls that this word appears.
	 * The file must have a specific format in order to produce the correct HTMLlist.
	 * @param filename The name of the file from which we want to produce the HashTable.
	 * @return The HashTable produced by the file given.
	 * @throws IOException if the file cannot be read.
	 * @throws Exception if the first line of the file is null or if it does not start with the String "*PAGE:".
	 */
	public HashTable readHashFunction (String filename) throws IOException,Exception{
		
		// Open the file given as argument
		BufferedReader infile = new BufferedReader(new FileReader(filename));

		HashTable table = new HashTable();
		HTMLlist url = new HTMLlist(null,null);
		String name;
		
        name = infile.readLine(); //Read the first line
        
        long startMeasur = System.currentTimeMillis();
        // Throw Exception if the first line is not what it supposed to be.
        if ((name == null)||(!name.startsWith("*PAGE:"))){
        	infile.close();
        	throw new Exception ("Change the Format of the file!"
        			+ "All the websites must start with the keyword : *PAGE:");
        }  
        else if (name.startsWith("*PAGE:"))
        {
        	url.str = name.substring(6);
         	name = infile.readLine(); // Read the next line
        }
       
        while (name != null) 
    	{ // Exit if there is none        		
        	 if (name.startsWith("*PAGE:"))
             {//if it is a Page we add it to our current url, and we read the next line.
             	url.str = name.substring(6);
             	name = infile.readLine(); // Read the next line
             }
        	 else
        	 {// Here we know we are reading a word
        		 // Find the hash value of the word
        		 int hashkey = stringHashFunction(name.toLowerCase(), table.arraySize);
        		//Add this word  with it's current url to the HashTable
        		 table.addkey(hashkey,url,name.toLowerCase());
	             name = infile.readLine(); // Read the next line
        	 }
    	}        
        infile.close(); // Close the file
        
        long finishedMeasur = System.currentTimeMillis();
        this.elapsedTime = finishedMeasur - startMeasur;
        // Return the final HashTable of the file.
        return table;
    }
	
	/**
	 * This method searches if the given string exists in the given HashTable.
	 * We first find the hash value of this string with the same hash function we use when we constructed the hash table.
	 * We then search in the given hash table if there is an HTMLlist object 
	 * (with the given string matching the object's str field) for that key we created. 
	 * If there is we return the next field of that object or we return a null list if the string is not found.
	 * @param table The HashTable to search. 
	 * @param word The word to be searched.
	 * @return The next field of the HTMLlist object, that the given string was found.
	 */
	public HTMLlist exists (HashTable table, String word) {
		// Start measuring the time of the search.
		HTMLlist result;
    	long startTime = System.currentTimeMillis();
		int hashkey = stringHashFunction(word, table.arraySize);
		result = table.searchUrls(hashkey,word);
		// Get the time of the search when finished.
    	long finishTime = System.currentTimeMillis();
    	this.SearchelapsedTime = finishTime - startTime; // Total time taken.
		return result;
	}
	
	/**
	 * This method creates a HashSet of the non-duplicate Strings contained in the 
	 * str field of the first given HTMLlist or in the str field of the second given HTMLlist .
	 * @param list1 The first HTMLlist.
	 * @param list2 The second HTMLlist.
	 * @return The Set created by the non-duplicate Strings contained in the str fields of the two given HTMLlists.
	 */
	public Set<String> orHTMLlist(HTMLlist list1,HTMLlist list2){		
		Set<String> result = new HashSet<String>();
		while (list1 != null){
			result.add(list1.str);        
            list1 = list1.next;
        }
		while (list2 != null){
			result.add(list2.str);        
            list2 = list2.next;
        }
		return result;
	}
	
	/**
	 * This method creates a HashSet of the Strings contained in both of the 
	 * str fields of the two given HTMLlists.
	 * @param list1 The first HTMLlist.
	 * @param list2 The second HTMLlist.
	 * @return The Set created by the Strings contained in both of the str fields of the two given HTMLlists.
	 */
	public Set<String> andHTMLlist(HTMLlist list1,HTMLlist list2){		
		Set<String> result = new HashSet<String>();
		while (list1 != null){
			if (Searcher.exists(list2, list1.str))
				result.add(list1.str);			
			list1 = list1.next;
        }		
		return result;
	}
	
	/**
	 * This method print in the System's console all the strings contained in the given Set.
	 * @param result The set that we want visualise.
	 */
	public void displayResult(Set<String> result){
		for (String website : result)
			System.out.println(website);
	}	
}
