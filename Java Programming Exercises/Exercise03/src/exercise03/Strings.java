package exercise03;

public class Strings {

	public static void main(String[] args) {
		
		String inputString = "The quick brown fox jumps over the lazy dog";
		
		//make outputString = "Tempus fugit";
		String s1 = inputString.substring(0,1);
		String s2 = inputString.substring(2,3);
		String s3 = inputString.substring(22,24);
		String s4 = inputString.substring(21,22);
		String s5 = inputString.substring(24,25);
		String s6 = inputString.substring(16,17);
		String s7 = inputString.substring(42);
		String s8 = inputString.substring(6,7);
		String s9 = inputString.substring(31,32);
		
		String outputString = s1+s2+s3+s4+s5+" "+s6+s4+s7+s8+s9;
		System.out.println(s7);
	}

}
