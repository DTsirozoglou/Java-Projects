package Exercise01;

/**
2.2. Complete the following program so that it prints the message 
"the quick brown fox jumps over the lazy dog".
*/

public class ConcatDemo {

	public static void main(String[] args) {
		String animal1 = "quick brown fox";
		String animal2 = "lazy dog";
		String article = "the";
		String action = "jumps over";
		
		String message =  article.concat(" ").concat(animal1).concat(" ").concat(action).concat(" ").concat(article).concat(" ").concat(animal2);
		System.out.println(message);

	}

}
