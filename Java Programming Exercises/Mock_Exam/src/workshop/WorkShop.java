package workshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * A workShop class aggregates the Present class,the Reindeer class and the Elf class.
 * That because it contains objects of those classes.
 *
 */
public class WorkShop 
{
	private List<Present> presents= new ArrayList<Present>(); 
	private Set<Reindeer> reindeers= new HashSet<Reindeer>();
	private Set<Elf> elfs = new HashSet<Elf>();
	
	/**
	 * Constructs a WorkShop by trying to initialise it from a file
	 */
	public WorkShop() 
	{
		try {
			this.initializeWorkshop();
		} catch (FileNotFoundException | IllegalArgumentException e) {
			this.presents = new ArrayList<Present>();
			this.reindeers = new HashSet<Reindeer>();
			this.elfs = new HashSet<Elf>();	
		}	
	}
	
	/**
	 * Add a Present to the Present list of the Workshop
	 * @param pres the present to add.
	 */
	public void addPresent(Present pres)
	{
		this.presents.add(pres);
	}
	
	/**
	 * The method enquires the Presents in the workshop
	 * @return all the Presents currently in the WorkShop
	 */
	public List<Present> getPresents()
	{
		return this.presents;
	}
	
	/**
	 * The method enquires all the unwrapped Presents in the workshop.
	 * @return all the unwrapped Presents currently in the WorkShop
	 */
	public List<Present> getUnwrappedPresents()
	{
		List<Present> UnwrappedPresents = new ArrayList<Present>();
		
		for (Present pres : this.getPresents())
		{
			if (pres.wrapped == false)
			{
				UnwrappedPresents.add(pres);
			}
		}
		return UnwrappedPresents;
	}
	
	/**
	 * The method creates a Reindeer with his hunger set to maximum (100), and add it 
	 * in the WorkShop Set of Reindeer.
	 */
	public void createHungryReindeer()
	{
		Reindeer hungReindeer = new Reindeer();
		this.reindeers.add(hungReindeer);
	}
	
	/**
	 * The method enquires all the reindeer currently on the workshop.
	 * @return the reindeer of the WorkShop.
	 */
	public Set<Reindeer> getReindeers()
	{
		return this.reindeers;
	}
	 
	/**
	 * The method creates a toy maker Elf and adds it to the WorkShop set of Elf. 
	 */
	public void createToyMaker()
	{
		ToyMaker toyMakerElf = new ToyMaker(this);
		this.elfs.add(toyMakerElf);
	}
	
	/**
	 * The method creates a Present Wrapper Elf and adds it to the WorkShop set of Elf.
	 */
	public void createPresentWrapper()
	{
		PresentWrapper presentWrapperElf = new PresentWrapper(this);
		this.elfs.add(presentWrapperElf);
	}
	
	/**
	 * The method creates a Reindeer Feeder Elf and adds it to the WorkShop set of Elf.
	 */
	public void createReindeerFeeder()
	{
		ReindeerFeeder reindeerFeederElf = new ReindeerFeeder(this);
		this.elfs.add(reindeerFeederElf);
	}
	
	/**
	 * This method make all Elf ,currently on the workshop, work!
	 */
	public void work()
	{
		for (Elf elf : this.elfs)
		{
			elf.work();
		}
	}
	
	/**
	 * This method is used by workShop constructor to initialise the workshop from a file.
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 */
	private void initializeWorkshop() throws FileNotFoundException, IllegalArgumentException
	{
		this.presents = new ArrayList<Present>();
		this.reindeers = new HashSet<Reindeer>();
		this.elfs = new HashSet<Elf>();	
	    Scanner console1 = new Scanner(System.in);
	    System.out.print("Input file to initialize the workShop: ");
	    String inputFileName = console1.next();
	    console1.close();
	   
	// Construct the Scanner for reading the file
		  
	   File inputFile = new File(inputFileName);
	   Scanner in = new Scanner(inputFile);
	   while (in.hasNextLine())
	   {
			  String line = in.nextLine();
			 
			  // passes each line of the table file as a String to another Scanner constructor
			  Scanner lineScanner = new Scanner(line);
			  lineScanner.useDelimiter(":");
			  String elf = lineScanner.next();
			  String number =lineScanner.next(); 
//			  System.out.println(number);
//			  System.out.println(elf);
			  
			  if (elf.contains("toymakers"))
			  {
				  for (int i =0; i <Integer.parseInt(number); i++)
					{
						this.createToyMaker();
					}		
			  }
			  else if (elf.contains("presentwrappers"))
			  {
				  for (int i =0; i <Integer.parseInt(number); i++)
					{
						this.createPresentWrapper();
					}		
			  }
			  else if (elf.contains("reindeerfeeders"))
			  {
				  for (int i =0; i <Integer.parseInt(number); i++)
					{
						this.createReindeerFeeder();
					}		
			  }
			  else if (elf.contains("reindeer"))
			  {
				  for (int i =0; i <Integer.parseInt(number); i++)
					{
						this.createHungryReindeer();
					}		
			  }
			  else 
			  {
				  lineScanner.close();
				  throw new IllegalArgumentException("This is not an Integer value");
			  }
			  lineScanner.close();
	   }
		   		  
		  in.close();
	}
	
	/* 
	 * This method overrides the "toString" method of the object.
	 * It returns the number of elves, the number of presents, how many of these are unwrapped,
	 * and a list of all reindeer (and how hungry they are)
	 */
	public String toString()
	{
		String deersInfo= "";
		for (Reindeer deer : this.getReindeers() )
		{
			deersInfo = deersInfo + " " + deer.toString();
		}
		if (deersInfo == "")
		{
			deersInfo = "There are not any Reindeers in the Workshop!";
		}
		return " The number of elves currently in the workshop is: " + this.elfs.size() + " The number of presents is : " +this.getPresents().size()
				+ " The number of uwrapped presents is: " + this.getUnwrappedPresents().size() + " The List of the Reinders is: "+deersInfo;
	}
}
