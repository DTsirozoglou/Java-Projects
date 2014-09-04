/**
 An employee has a name , and a salary that can be changed 
 */

public class Employee {
 
	private String name;
	private double salary;
	
	/**
	 Constructs an employee with the name and the salary given 
	 @employeeName the name of the employee
	 @currentSalary the salary of the employee
	 */
	
	public Employee(String employeeName, double currentSalary) {
		
		name = employeeName;
		salary = currentSalary;
		
	}
	
	/**
	 The method gets the name of the employee
	 @return the name of the employee 
	 */
	
	public String getName(){
		
		return name;
		
	}
	
	/**
	 The method gets the salary of the employee
	 @return the salary of the employee 
	 */
	
	public double getSalary(){
		
		return salary;
		
	}
	
	/**
	Raise the employee’s salary by a certain percentage
    @param byPercent the percentage of the salary raise
	*/
	
	public void raiseSalary(double byPercent){
		
		salary = salary + (salary*byPercent)/100;
		
	}

}
