package exercise11;

/**
 * The subclass "Classes" inherits from the superclass Groups. Every Classes is a Group!
 *
 */
public class Classes extends Groups
{
  private int classePriority;
 
  /**
 * When a group of classes is constructed we give it the highest priority "1", and we want the user to define the priority of this
 * classes group among the other groups of the Classes.
 * @param pr is the user defined priority for this classes group
 */
public Classes(int pr)
  {
	  classePriority= pr;
	  this.setPriority(1); 
  }
   
  /**
 * @return the priority of this class group among others class groups
 */
public int getClassePriority() 
	{
		return this.classePriority;
	}	
}