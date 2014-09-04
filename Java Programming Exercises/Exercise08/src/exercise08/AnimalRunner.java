package exercise08;

import java.util.*;

public class AnimalRunner
{
	public static void main(String[] args)
	{
//		ArrayList dogcatList = new ArrayList();
//		dogcatList.add(new Dog("Fred"));
//		dogcatList.add(new Cat("Wanda"));
		
		ArrayList<Speakable> speakable = new ArrayList<Speakable>();
		speakable.add(new Dog("Fred"));
		speakable.add(new Cat("Wanda"));
				
		for (Object obj : speakable)
		{
			((Speakable) obj).speak();
		}
	}
}

	
