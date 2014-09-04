/**
A class to test the VendingMachine class.
*/
public class VendingMachineTester
{
	/**
	Tests the methods of the VendingMachine class
	*/
	
	public static void main(String[] args)
	{
		VendingMachine machine = new VendingMachine();
		machine.fillUp(10); // Fill up with ten cans
		machine.insertToken();
		machine.insertToken();
		System.out.print("Token count: ");
		System.out.println(machine.getTokenCount());
		System.out.println("Expected: 2");
		System.out.print("Can count: ");
		System.out.println(machine.getCanCount());
		System.out.println("Expected: 18");
		
		VendingMachine machine2 = new VendingMachine(50);
		machine2.fillUp(10); // Fill up with ten cans
		machine2.insertToken();
		machine2.insertToken();
		System.out.print("Token count: ");
		System.out.println(machine2.getTokenCount());
		System.out.println("Expected: 2");
		System.out.print("Can count: ");
		System.out.println(machine2.getCanCount());
		System.out.println("Expected: 58");
	}
}