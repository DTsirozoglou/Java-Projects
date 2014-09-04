package exercise03;

/**
A class to test the Variables class.
*/
public class VariablesTester {
	
	public static void main(String[] args)
	{
		// Initialise each variable with any appropriate value. Print out the name 
		// of each variable and its value
		Variables vars = new Variables();
		System.out.print("The variable inte is: ");
		System.out.println(vars.getInte());
		System.out.print("The variable lInt is: ");
		System.out.println(vars.getlInt());
		System.out.print("The variable sInt is: ");
		System.out.println(vars.getsInt());
		System.out.print("The variable doub is: ");
		System.out.println(vars.getDoub());
		System.out.print("The variable fl is: ");
		System.out.println(vars.getFl());
		System.out.print("The variable ch is: ");
		System.out.println(vars.getCh());
		System.out.print("The variable bool is: ");
		System.out.println(vars.isBool());
		
		// Prints the name and the values of our constants
		
		System.out.print("The constant FI is: ");
		System.out.println(vars.FI);
		System.out.print("The constant FL is: ");
		System.out.println(vars.FL);
		System.out.print("The constant FS is: ");
		System.out.println(vars.FS);
		System.out.print("The constant FD is: ");
		System.out.println(vars.FD);
		System.out.print("The constant FF is: ");
		System.out.println(vars.FF);
		System.out.print("The constant FC is: ");
		System.out.println(vars.FC);
		System.out.print("The constant FB is: ");
		System.out.println(vars.FB);
		
		//	Modify the value of each var iable with an assignment statement 
		//  and print out the names of the variables and their new values.
		vars.setInte(2);
		vars.setlInt(1232231);
		vars.setBool(false);
		vars.setCh('e');
		vars.setDoub(43.3);
		vars.setFl(23232321);
		
		System.out.print("The new variable inte is: ");
		System.out.println(vars.getInte());
		System.out.print("The new variable lInt is: ");
		System.out.println(vars.getlInt());
		System.out.print("The new variable sInt is: ");
		System.out.println(vars.getsInt());
		System.out.print("The new variable doub is: ");
		System.out.println(vars.getDoub());
		System.out.print("The new variable fl is: ");
		System.out.println(vars.getFl());
		System.out.print("The new variable ch is: ");
		System.out.println(vars.getCh());
		System.out.print("The new variable bool is: ");
		System.out.println(vars.isBool());
		
	}
}
