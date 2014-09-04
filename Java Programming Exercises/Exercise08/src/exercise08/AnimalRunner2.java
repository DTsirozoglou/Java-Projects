package exercise08;

public class AnimalRunner2 
{
	public static void main(String[] args)
	{
		Dog d1 = new Dog("Fred");
		d1.speak();
		Object obj = new Dog("Connie");
		((Dog) obj).speak();
		
		Object obj2 = new Cat("Niaou");
		//((Cat) obj2).speak();
		((Dog) obj2).speak();
	}
	
}
