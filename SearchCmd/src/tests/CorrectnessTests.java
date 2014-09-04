package tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import searchers.HashFunction;
import searchers.Searcher;
import searchers.Searcher1;
import structures.HTMLlist;
import structures.HashTable;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * CorrectnessTests is a junit test, made to test the behaviour of all of our Search engines, 
 * both in good and in erroneous situations.  
 *
 */
public class CorrectnessTests {

	private static String inputFile1,inputFile2,inputFile3;
	private Searcher searcher 		=new Searcher();
	private Searcher1 searcher1 	=new Searcher1();
	private HashFunction searcher2 	=new HashFunction();
	
	/**
	 * In order to run the tests make sure that you have stored the
	 * files below in the right path in order to be read. 
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		// Check that a filename has been given as argument
		inputFile1 = "wrongFileFormat.txt";
		inputFile2 = "itcwww-medium.txt";
		inputFile3 = "emptyFirstLine.txt";			
	}

	
	/**
	 * In the next three test case we want to check if our search engines 
	 * respond correctly by throwing Exceptions, when a the file given cannot 
	 * be found, or when a file does not have the format we support.
	 * 
	 */
	@Test
	public void testFileNotFound() {
		
		boolean notFound1 = false;
		boolean notFound2 = false;
		boolean notFound3 = false;
		HTMLlist l1 = new HTMLlist (null,null);
		try {
			l1 = searcher.readHtmlList ("notExists.txt");
		} catch (IOException e) {
			notFound1=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(notFound1);
		
		try {
			l1 = searcher1.readHtmlList ("notExists.txt");
		} catch (IOException e) {
			notFound2=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(notFound2);
		
		try {
			HashTable table;
			table = searcher2.readHashFunction("notExists.txt");
		} catch (IOException e) {
			notFound3=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(notFound3);		
	}
	
	@Test
	public void testEmptyFirstLine() {
		
		boolean notFound1 = false;
		boolean notFound2 = false;
		boolean notFound3 = false;
		HTMLlist l1 = new HTMLlist (null,null);
		try {
			l1 = searcher.readHtmlList (inputFile3);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			notFound1=true;			
		}
		assertTrue(notFound1);
		
		try {
			l1 = searcher1.readHtmlList (inputFile3);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			notFound2=true;			
		}
		assertTrue(notFound2);
		
		try {
			HashTable table;
			table = searcher2.readHashFunction(inputFile3);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			notFound3=true;			
		}
		assertTrue(notFound3);		
	}
	
	@Test
	public void testWrongFileFormat() {
		
		boolean notFound1 = false;
		boolean notFound2 = false;
		boolean notFound3 = false;
		HTMLlist l1 = new HTMLlist (null,null);
		try {
			l1 = searcher.readHtmlList (inputFile1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			notFound1=true;			
		}
		assertTrue(notFound1);
		
		try {
			l1 = searcher1.readHtmlList (inputFile1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			notFound2=true;			
		}
		assertTrue(notFound2);
		
		try {
			HashTable table;
			table = searcher2.readHashFunction(inputFile1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			notFound3=true;			
		}
		assertTrue(notFound3);		
	}
	
	/**
	 * Here we want to test the behaviour of our search engines when the word to be searched does not exist.
	 * 
	 * 1.We create a HashTable from the inputFile2 using the SearchCmd4 engine. 
	 * 
	 * 2.We search in the HashTable for the word "asdsad" and we store the results.
	 * 
	 * 3.As this word does not exist in our file the result must be null, so we check it.
	 * 
	 * 4.We perform the same procedure using the SearchCmd2 engine.
	 * 
	 * 5.We perform the same procedure using the SearchCmd3 engine.
	 * 
	 */
	@Test
	public void testWrongUserInput() {
	
		HTMLlist result = new HTMLlist (null,null);
		try {
			HTMLlist l1,l2,result1,result2;
			HashTable table;
			table = searcher2.readHashFunction(inputFile2);
			result = searcher2.exists(table, "asdsad");
			assertTrue(result.str ==null);	
			
			l1 = searcher.readHtmlList (inputFile2);
			result1 = searcher.newExists(l1, "asdsad");
			assertTrue(result1 == null);	
			
			l2 = searcher1.readHtmlList (inputFile2);
			result2 = searcher1.exists(l2, "asdsad");
			assertTrue(result2 == null);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();		
		}	
	}
	
	/**
	 * Here we want to test the correctness of the results of our Search engines.
	 * 
	 * 1. We create a HashTable from the inputFile2 using the SearchCmd4 engine. 
	 * 
	 * 2. We create a HTMLlist from the inputFile2 using the SearchCmd2 engine.
	 * 
	 * 3. We create a HTMLlist from the inputFile2 using the SearchCmd3 engine.
	 * 
	 * 4. We check that the entries in the HashTable are the same with the number of 
	 *    items in the list created by the SearchCmd3's engine. We must have the exact
	 *    same amount of words recorded in our structures.
	 * 
	 * 5. We search in the created data structures for the word "it" and we store the results.
	 * 
	 * 6. We check if the results produced by the search engines - if the HTMLlists - are equal.  
	 * 
	 * 7. We search in the created data structures for the word "in" and we store the results.
	 * 
	 * 8. We check if the results produced by the search engines - if the HTMLlists - are equal.   
	 */
	@Test
	public void testCorrectness() {
	
		try {
			HTMLlist l1,l2,result1,result2,result;
			HashTable table;
			table = searcher2.readHashFunction(inputFile2);			
			l1 = searcher.readHtmlList (inputFile2);			
			l2 = searcher1.readHtmlList (inputFile2);
			
			assertTrue(table.getItemsinArray() == l2.getNumberOfItemsOfList(l2));
			
			result = searcher2.exists(table, "it");
			result1 = searcher.newExists(l1, "it");
			result2 = searcher1.exists(l2, "it");
			
			assertTrue(result.equals(result1));
			assertTrue(result1.equals(result2));
			
			result = searcher2.exists(table, "in");
			result1 = searcher.newExists(l1, "in");
			result2 = searcher1.exists(l2, "in");
			
			assertTrue(result.equals(result1));
			assertTrue(result2.equals(result1));
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();		
		}	
	}

}
