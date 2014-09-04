package exercise12;

import java.util.HashSet;
import java.util.Set;

/**
 * This is a Program for testing the Hash set. In this exercise we see that:
 * A Set does not allow duplicates, so when we add a duplicate value to the hash set, it will just ignore it.
 * The second point of the exercise is that when we add values to a hash set, they are stored unordered. So when we
 * print them, the values are printed in the order stored on the hash set and not on the order that we added them.
 */
public class HashSetRunner {

	public static void main(String[] args) 
	{		
		//Build a class called HashSetRunner with a main method that instantiates a HashSet<String>.
		//Exercise 9.1 Why is the variable hashSet of type Set rather than type HashSet?
		// We decide to store the reference to a Set<String> variable, for two reasons.
		// the first is that we can easily change from a Hash Set to Tree Set (if we want to visit the set's elements in sorted order.)
		// And the second is that after the construction of the object "hashset" the implementation no longer matters
		// ,only the interface is important (The Set interface.)
		Set<String> hashSet = new HashSet<String>();
		
		//Add some strings to the Hash set:
		hashSet.add("iii");
		hashSet.add("hhh");
		hashSet.add("ggg");
		hashSet.add("fff");
		hashSet.add("eee");
		hashSet.add("ddd");
		hashSet.add("ccc");
		hashSet.add("bbb");
		hashSet.add("aaa");
		
		//We add again the same strings to the Hash set:
		hashSet.add("iii");
		hashSet.add("hhh");
		hashSet.add("ggg");
		hashSet.add("fff");
		hashSet.add("eee");
		hashSet.add("ddd");
		hashSet.add("ccc");
		hashSet.add("bbb");
		hashSet.add("aaa");
		
		//use an enhanced for loop to walk sequentially through the hash set
		for (String word : hashSet)
		{
			//print each string encountered
			System.out.println(word);
			
		}
		
	}

}
