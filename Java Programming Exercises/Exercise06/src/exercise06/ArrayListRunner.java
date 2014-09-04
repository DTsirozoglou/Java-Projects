package exercise06;

import java.util.ArrayList;

/**
 * ArrayListRunner is a program to help us develop some basic skills on working with array lists, by invoking ArrayList methods 
 */

public class ArrayListRunner
{
	public static void main(String[] args)
	{
		ArrayList<String> names = new ArrayList<String>();
		System.out.println(names);
	
		//Invoke add() to enter the following names in sequence: 
		//Alice, Bob, Connie, David, Edward, Fran, Gomez, Harry. Print the ArrayList again.
		
		names.add("Alice");
		names.add("Bob");
		names.add( "Connie");
		names.add( "David");
		names.add( "Edward");
		names.add( "Fran");
		names.add( "Gomez");
		names.add("Harry");	
		System.out.println("The arrow list now contains the following names: " + names);
		
		//Use get() to retrieve and print the first and last names.
		
		String first = names.get(0);
		System.out.println("The first name is: " + first);
		
		String last = names.get(names.size() -1);
		System.out.println("The last name is: " + last);
		
		//Print the size() of the ArrayList.
		System.out.println("The the size of the ArrayList is: " + names.size());
		
		//Use set() to change Alice to Alex.Print the ArrayList to verify the change.
		names.set(0, "Alex");
		System.out.println("The arrow list now contains the following names: " + names);
		
		//Use the alternate form of add() to insert Doug after David.	Print the ArrayList again.
		names.add(4, "Doug");
		System.out.println("The arrow list now contains the following names: " + names);
		
		//Use an enhanced for loop to print each name in the ArrayList.
		int counter = 0; 
		for (String element : names)
		{
			counter++;
			System.out.println("The element of the arrowlist in the potition "+ counter + " is: " + element);
		}
		
		//Create a second ArrayList that is built by calling the ArrayList constructor that accepts 
		//another ArrayList as an argument.Pass names to the constructor to build this list.Then print both lists and see they are equal.
		ArrayList<String> newNames = new ArrayList<String>(names);
		newNames.add("Alice");
		System.out.println("The arrow list newNames now contains the following names: " + newNames);
		if (newNames == names)
		{
			System.out.println("The arrow lists newNames and names are equal");
		}
		else
		{
			System.out.println("The arrow lists newNames and names are not equal");
		}
		
		//Call names.remove(0) to remove the first element.Print both lists.
		//Verify that Alex was removed from names, but not from the second lists
		names.remove(0);
		System.out.println("The arrow list names after the command names.remove(0) now contains the following names: " + names);
		System.out.println("The arrow list newNames after the command names.remove(0) now contains the following names: " + newNames);
		
	}	
} 