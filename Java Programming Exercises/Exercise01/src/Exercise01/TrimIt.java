package Exercise01;

/**
2.4. Complete the following program so that it prints the message "abcdefghi".
*/

public class TrimIt {

	public static void main(String[] args) {
		String sentence1 = " abc ";
		String sentence2 = " def";
		String sentence3 = "ghi ";
		
		String message = sentence1.trim().concat(sentence2.trim()).concat(sentence3).trim(); 
		System.out.println(message);
	}

}
