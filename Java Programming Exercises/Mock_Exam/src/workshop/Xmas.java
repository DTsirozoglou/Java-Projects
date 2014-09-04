package workshop;

import java.util.Scanner;

public class Xmas {

	public static void main(String[] args) {
		
		WorkShop initialWorkShop = Santa.getWorkshop();;
//		System.out.println(initialWorkShop.toString());
//		Santa santa = new  Santa(); 
//		System.out.println(Santa.getWorkshop().toString());
		Scanner console = new Scanner(System.in);
		System.out.println("Enter the number of the toyMakers elfs to be created: ");
		int toyNum = console.nextInt();	

		for (int i =0; i <toyNum; i++)
		{
			initialWorkShop.createToyMaker();
		}		
		System.out.println(initialWorkShop.toString());
		
		System.out.println("Enter the number of the ReindeerFeedr elfs to be created: ");
		int feedersNum = console.nextInt();		
		for (int i =0; i <feedersNum; i++)
		{
			initialWorkShop.createReindeerFeeder();
		}
		System.out.println(initialWorkShop.toString());
		
		System.out.println("Enter the number of the PresentWrapper elfs to be created: ");
		int wrappersNum = console.nextInt();
		for (int i =0; i <wrappersNum; i++)
		{
			initialWorkShop.createPresentWrapper();
		}
		System.out.println(initialWorkShop.toString());
		
		System.out.println("Enter the number of the Reindeers to be created: ");
		int reindeersNum = console.nextInt();		
		for (int i =0; i <reindeersNum; i++)
		{
			initialWorkShop.createHungryReindeer();
		}
		
		
		System.out.println("Press 0 to make the Elfs created in the workshop work! ");
		int startWork = console.nextInt();
		if (startWork==0)
		{
			initialWorkShop.work();
		}
		System.out.println(initialWorkShop.toString());
		
		System.out.println("Press 0 too send Santa to deliver the presents");
		int startDelivery = console.nextInt();
		if (startDelivery==0)
		{
			Santa.deliverPresents();
		}
		System.out.println(initialWorkShop.toString());

		console.close();
	}

}
