package exercise03;

public class Expressions {
	
	public static void main(String[] args)
	{
		int a = 3;
		int b = 4;
		int c = 5;
		int d = 17;
		
		System.out.println((a + b)/ c);
		// 3 and 4 are added with sum 7
		// 7 is divided by 5 with quotient 1
		
		System.out.println(a + b / c);
		// 4 is divided by 5 with quotient 0
		// 3 is added to 0 with sum 3
		
		System.out.println(a++);
		// We increment 3 by 1 to 4
		
		System.out.println(a--);
		// We decrement 3 by 1 to 2
		
		System.out.println(a + 1);
		// 3 and 1 are added with sum 4
		
		System.out.println(d % c);
		// 17 modulo 5 equals to 2
		
		System.out.println(d / c);
		// 17 is divided by 5 with quotient 3.4
		
		System.out.println(d % b);
		// 17 modulo 4 equals to 1
		
		System.out.println(d / b);
		// 17 is divided by 4 with quotient 4.25
		
		System.out.println(d + a / d + b);
		// 3 is divided by 17 with quotient 0
		// 17 and 0 are added with sum 17
		// 17 and 4 are added with sum 21
		
		System.out.println((d + a) / (d + b));
		// 17 and 3 are added with sum 20
		// 17 and 4 are added with sum 21
		// 20 is divided by 21 with quotient 0
		
		System.out.println(Math.sqrt(b));
		// The square of 4 equals to 2.0 (Math gives us double)
		
		System.out.println(Math.pow(a, b));
		// The power of 3 to 4 equals to 81.0
		
		System.out.println(Math.abs(-a));
		// the absolute  value of (-3) equals to 3
		
		System.out.println(Math.max(a, b));
		// Compares 3 and 4 and gives us the maximum witch is 4
	}

}
