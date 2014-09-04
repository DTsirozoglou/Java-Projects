package exercise03;

import java.util.Scanner;

/**
 * Annuity is a program that calculate the present value of an annuity 
 * given the PMT the periodic interest and the number of payments 
 */

public class Annuity {

	public static void main(String[] args) {
		// TODO Auto-generated method stubScanner scan = new Scanner(System.in);
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the periodic payment: ");
		double PMT = scan.nextDouble();
		
		System.out.print("Please enter the periodic interest or compound rate ");
		double i = scan.nextDouble();
		
		System.out.print("Please enter the number of payments (years): ");
		double n = scan.nextDouble();
		
		scan.close();
		
		double PVann = PMT * (((Math.pow(1 + i,n-1)-1)/i)/(Math.pow(1+i,n-1)+1));	
		System.out.print("The present value of an annuity for these values is: ");
		System.out.println(PVann);
	}

}
