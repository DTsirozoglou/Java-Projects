package workshop;

/**
 * The Present is a very simple class – it just has a boolean property, wrapped, along with appropriate 
 * getter/setter. When a present is created the default state is false.
 *
 */
public class Present {
	
	boolean wrapped ;
	
	/**
	 * Constructs a Present with the default state set to false.
	 */
	public Present()
	{
		wrapped = false;
	}

	/**The method enquires the status of the Present object
	 * @return true if the Present is wrapped- false otherwise.
	 */
	public boolean isWrapped()
	{
		if (this.wrapped == true)
		{
			return true;
		}
		else return false;
	}
	
	/**The method set the status of the present , to the one given in the parameter.
	 * @param wrap the new status of the present (true for wrapped)
	 */
	public void setWrapped(boolean wrap)
	{
		this.wrapped = wrap;
	}
}
