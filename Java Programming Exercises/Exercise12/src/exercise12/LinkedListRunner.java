package exercise12;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This is a Program for testing the link lists and the list iterators.
 *
 */
public class LinkedListRunner {

	public static void main(String[] args) 
	{		
		//Build a class called LinkedListRunner with a main method that instantiates a 
		//LinkedList<String>.		
		LinkedList<String> linkList = new LinkedList<String>();
		
		//Add some strings to the linked list:
		linkList.addFirst("iii");
		linkList.addFirst("hhh");
		linkList.addFirst("ggg");
		linkList.addFirst("fff");
		linkList.addFirst("eee");
		linkList.addFirst("ddd");
		linkList.addFirst("ccc");
		linkList.addFirst("bbb");
		linkList.addFirst("aaa");
		
		//Build a ListIterator<String>
		ListIterator<String> iterator = linkList.listIterator();
		
		//use the iterator to walk sequentially through the linked list using 
		//hasNext and next.
		while (iterator.hasNext())
		{
			//print each string encountered
			String word = iterator.next();
			System.out.println(word);
		}
		
		//use the hasPrevious and previous methods to walk backwards through the list.
		while (iterator.hasPrevious())
		{
			// examine each string and remove all the strings that begin with a vowel.
			String word = iterator.previous();
			if (word.startsWith("a") || word.startsWith("e") || word.startsWith("i") || word.startsWith("o") || word.startsWith("u"))     
	        {
				iterator.remove();
	        }
		}
		
		//use the iterator to walk again sequentially through the linked list using 
		//hasNext and next.
		while (iterator.hasNext())
		{
			//print each string that remains in the linked list.
			String word = iterator.next();
			System.out.println(word);
		}
	}

}
