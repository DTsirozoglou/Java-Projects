package workshop;

/**
 * Elves can be toyMakers, PresentWrappers and Reindeer feeders.All elves should inherit from this abstract class, Elf,
 * that takes one parameter in its constructor which is a reference to the workshop it belongs to.
 * This is saved in a protected variable, so subclasses can use it.
 */

public abstract class Elf
{
	protected WorkShop reference;
	
	public Elf()
	{
		
	}
	
	//An abstract method, work(), for subclasses to implement.
	abstract  void work();
}
