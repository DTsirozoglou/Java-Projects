package structures;

import searchers.HashFunction;
import searchers.Searcher1;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * A HashTable is a data structure created using chained hashing. This means that each entry
 * contains a reference to an HTMLlist of two fields. Our HashTable can dynamically grow to a
 * number that is double the size of the previous one and it is a Prime number. 
 *
 */
public class HashTable {
	
	public HTMLlist [] theArray;
	public int arraySize;
	private int itemsInArray = 0;
	
	/**
	 * Constructs a HashTable with the prime 941 chosen to be the starting fixed size of our HashTable.
	 * The HashTable is initialised with empty HTMLlist objects of two fields. 
	 */
	public HashTable(){
		
		this.arraySize = 90001;
		theArray = new HTMLlist [arraySize];
		
		for (int i = 0; i < arraySize;i++)
			theArray[i]= new HTMLlist(null,new HTMLlist(null,null));
	}
	
	/**
	 * @return The number of the items added in the HashTable.
	 */
	public int getItemsinArray(){
		return this.itemsInArray;
	}
	
	/**
	 * This method checks if a number is Prime or not, if it is it returns true.
	 * @param number The number to be checked if it is Prime.
	 * @return true if the number is Prime.
	 */
	private boolean isPrime(int number) {
		// Eliminate the need to check versus even numbers
		if (number % 2 == 0)
		 return false;
		// Check against all odd numbers
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0)
		    return false;
		}
		// If we make it here we know it is odd
		return true;
	}
	
	/**
	 * This method finds the next Prime number of a given number and returns it.
	 * @param number The number to find his next Prime.
	 * @return The next Prime of the given number.
	 */
	private int getNextPrime(int number) {
		
		while (!isPrime(number)){
			number+=1;
		}
		return number;		
	}		
	
	/**
	 * This method tries to add an HTMLlist object, that contains a String and an HTMLlist, to the
	 * HashTable in the position of the given Integer key.If the key to add is bigger than our array size
	 * then we dynamically grow our HashTable.
	 * @param keytoAdd The position of the HashTable where we want to add the HTMLlist object.
	 * @param urltoAdd The HTMLlist field of the HTMLlist object that we want to add.
	 * @param word The String field of the HTMLlist object that we want to add.
	 */
	public  void addkey(int keytoAdd, HTMLlist urltoAdd, String word)
	{	
		// If the requested key is bigger than our size, that means that we must increase our HashTable size 
		// so us to be able to store what we want.
		if (keytoAdd>=arraySize){
			this.itemsInArray = 0; // we will empty our table to fill it later.
			// we store the first prime number of the double of our current HashTable size.
			int newArraysize = getNextPrime(2*arraySize);	
			// We store our HashTable in a temporary one.
			HTMLlist [] temp = theArray;
			// We change our HashTable to a new HashTable with a new Array size.
			theArray = new HTMLlist [newArraysize];
			int oldsize =arraySize;
			arraySize=newArraysize;
			// We initialise our HashTable with empty HTMLlist objects of two fields.
			for (int i = 0; i <arraySize;i++)
				theArray[i] = new HTMLlist(null,new HTMLlist(null,null)); 	
			// We navigate through our temporary table
			for (int i = 0; i <oldsize;i++){
					if (temp[i].str != null){
						// we find a new hash code of this string based on our new hash table size
						int hkey = HashFunction.stringHashFunction(temp[i].str,newArraysize);
						// we add the HTMLlist in our HashTable in the key we found in the previous step.
						this.addkey(hkey, temp[i].next, temp[i].str); 
					}
			}
			// Till now we have created the new bigger hash Table and we have added the contains 
			// of the previous one to it. As last step we calculate the new hash value of the word with the
			// key that caused the dynamically growth of the Hash table, and we proceed were we left. That is we will 
			// now add this key in our new table.
			keytoAdd = HashFunction.stringHashFunction(word,newArraysize);
		}
		// If the key is in bounds and it points to a null HTMLlist value in our HashTable 
		// and we have more than one urls to add (this will happen only when we grow the HashTable)
		// then we store the given parameters in this HTMLlist.
		if ((theArray[keytoAdd].str == null)&(theArray[keytoAdd].next.str == null)&(urltoAdd.next !=null)){
			// We store the given String in the str field of the HTMllist object
			theArray[keytoAdd].str = word;
			// Then we add to the next field of the HTMLlist object the hole HTMLlist given as a parameter.
			theArray[keytoAdd].next = urltoAdd;	
			this.itemsInArray +=1; // we have an entry!
		}
		// If the key is in bounds and it points to a null HTMLlist value in our HashTable 
		// then we store the given parameters in this HTMLlist.
		else if ((theArray[keytoAdd].str == null)&(theArray[keytoAdd].next.str == null)){
			// We store the given String in the str field of the HTMllist object
			theArray[keytoAdd].str = word;
			// Then we add to the next field of the HTMLlist object the string field 
			// of the HTMLlist given as a parameter.
			theArray[keytoAdd].next = Searcher1.addToHTMLlist(theArray[keytoAdd].next, urltoAdd.str);	
			this.itemsInArray +=1; // we have an entry!
		}
		// If in the HTMLlist value of the requested key in the HashTable there is already the same word stored
		// then  we add to the next field of the HTMLlist object the String field of the 
		// HTMLlist given as a parameter if it is not already there .
		else if (theArray[keytoAdd].str.equals (word))
			theArray[keytoAdd].next = Searcher1.addToHTMLlist(theArray[keytoAdd].next, urltoAdd.str);
		// If another String is stores in the key we want, then we increase the key value by one 
		// and we call the same method again with the exact same parameters with the exception of 
		// the increased key.
		else addkey((keytoAdd+1),urltoAdd,word);		
	}
	
	/**
	 * This method searches in our HashTable on the given key, if the given word to search equals
	 * the str field of the HTMLlist that exists in that key. If it exists it returns the field next of the list.
	 * @param key The hash key value of the word to search.
	 * @param word The word to search.
	 * @return The contains of the next field of the matching HTMLlist. 
	 */
	public  HTMLlist searchUrls(int key, String word)
	{
		HTMLlist result = new HTMLlist(null,null);
		// If the key is bigger than our HashTable or if the value for that key in our HashTable is null, that means
		// that our HashTable does not contain the requested String so we just return a null HTMLlist.
		if (theArray.length < key || (theArray[key].str == null))
			return result;	
		// If in our key we find a match of the requested string and the String 
		// saved in the HTMLlist str filed in the key value, we return the HTMLlist matching the string.
		else if (theArray[key].str.equals (word))
		{
			result = theArray[key].next;
			return result;	
		}
		// If for that key, the value in our table is not the expected
		else 
		{
			// We increase the key by one, each time searching if there is a match between the requested
			// String to search, and the value of the HTMLlist str field pointed by the key in the HashTable.
			for (int i = key+1; i < theArray.length; i++)
			{
				// If we find a null value stored in this key, that means that this String does not
				// exists in our HashTable, so we just return a null HTMLlist.
				if (theArray[i].str == null)
					return result;
				// If we find a match we return the HTMLlist matching the string.
				else if (theArray[i].str.equals (word)){
					result = theArray[i].next;
					return result;	
				}				
			}
			// In the worst case we have searched the HashTable from the requested key +1 till 
			// the end of it and we haven't found a match,so we just return a null HTMLlist.
			return result;
		}
	}
	
	/**
	 * This method displays in the system console the Contains of the HashTable.
	 */
	public void display(){
		HashTable temp;
    	temp = this;
    	HTMLlist listOfWebsites;	
    	for (int i =0; i <arraySize;i++){
    		listOfWebsites = temp.theArray[i];
        	System.out.println("The word : "+ listOfWebsites.str +" in posotion :"+i+
        			" is in the websites: "+ listOfWebsites.next.toString());
    	}
	}
}
