/**
A class to test the Employee class.
*/
public class EmployeeTester 
{
	/**
	Tests the methods of the Employee class
	@param args not used
	*/
	
	public static void main(String[] args)
	{
		// create an employee
		Employee first = new Employee("Dimitris Tsirozoglou", 1000);
		// test the method getName
		System.out.println("The first employee is " + first.getName());
		System.out.println("Expected: Dimitris Tsirozoglou");
		// test the method getSalary
		System.out.println("The salary of the first employee is " + first.getSalary());
		System.out.println("Expected: 1000.0");
		// Use the mutator to change the salary of the employee
		first.raiseSalary(10);
		System.out.println("The new salary of the first employee is " + first.getSalary());
		System.out.println("Expected: 1100.0");
		
	}
}

