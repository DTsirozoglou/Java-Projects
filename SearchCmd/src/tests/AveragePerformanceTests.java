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
 * AveragePerformanceTests is a junit test to test mostly the performance of our search engines
 * when creating a data structure from a file and when searching for a word in the data structure 
 * each engine created.
 */
public class AveragePerformanceTests {
	
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
		inputFile1 = "itcwww-small.txt";
		inputFile2 = "itcwww-medium.txt";
		inputFile3 = "itcwww-big.txt";		
	}

	/**
	 * In this test we want to measure the performance of our search engines when reading a small txt file.
	 * 
	 * In order to have more reliable performance we calculate the averages of 50 measurements.
	 * 
	 * 1.We measure the time that each search engine spends to create the data structure 
	 *   and we print the average of it in the system console.
	 * 
	 * 2.We measure the time that each search engine spends to search for the popular 
	 *   word "it" in the data structure and we print the average of it in the system console.
	 *   
	 * 3.We check if the number of the words stored in the HashTable equals the number of 
	 *   the HTMLlist produced by the Searcher1.
	 *  
	 * 4.We check that we get the same number of resulted web pages where the word "it" occurs.
	 *  
	 */
	@Test
	public void testWithAsmallFile() {
		System.out.println("-----------------------------------------------------testWithAsmallFile-----------------------------------------------------"+" \n");
		long total=0;
		long totalSearchTime=0;
		HTMLlist l1 = new HTMLlist (null,null);
		HTMLlist l2 = new HTMLlist (null,null);
		HTMLlist l1Result = new HTMLlist (null,null);
		HTMLlist l2Result = new HTMLlist (null,null);
		HTMLlist table = new HTMLlist (null,null);
		HashTable table1;
		int wordsInArray =0;
		
		try{
		// Read the file and create the linked list
		for (int i =0; i <50; i++){
			l1 = new HTMLlist (null,null);
			l1Result = new HTMLlist (null,null);
			l1 = searcher.readHtmlList (inputFile1);
			l1Result= searcher.newExists(l1, "it");
			total += searcher.getElapsedTime();
			totalSearchTime += searcher.getSearchElapsedTime();
		}
		System.out.println("The total average time of the SearchCmd2 engine for creating the HTMLlist of a small file is : "
				+(total/50)+ " milliseconds");
		System.out.println("The total average time of the SearchCmd2 engine for searching the word 'it' in the HTMLlist of a small file is : "
				+(totalSearchTime/50)+ " milliseconds");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
			
		long total1=0;
		long totalSearchTime1=0;
		// Read the file and create the linked list
		for (int i =0; i <50; i++){
			l2 = new HTMLlist (null,null);
			l2Result = new HTMLlist (null,null);
			l2 = searcher1.readHtmlList (inputFile1);
			l2Result = searcher1.exists(l2, "it");
			total1 += searcher1.getElapsedTime();
			totalSearchTime1 += searcher1.getSearchElapsedTime();
		}
		System.out.println("The total average time of the SearchCmd3 engine for creating the HTMLlist of a small file is : "
				+(total1/50)+ " milliseconds");
		System.out.println("The total average time of the SearchCmd3 engine for searching the word 'it' in the HTMLlist of a small file is : "
				+(totalSearchTime1/50)+ " milliseconds");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
			
		long total2=0;
		long totalSearchTime2=0;
		// Read the file and create the linked list
		for (int i =0; i <50; i++){
			table = new HTMLlist (null,null);
			table1 = searcher2.readHashFunction(inputFile1);
			table = searcher2.exists(table1, "it");				
			total2 += searcher2.getElapsedTime();
			totalSearchTime2 += searcher2.getSearchElapsedTime();
			wordsInArray = table1.getItemsinArray() ;
		}
		System.out.println("The total average time of the SearchCmd4 engine for creating the HashTable of a small file is : "
				+(total2/50)+ " milliseconds");		
		System.out.println("The total average time of the SearchCmd4 engine for searching the word 'it' in the HashTable of a small file is : "
				+(totalSearchTime2/50)+ " milliseconds");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

		assertTrue(wordsInArray == l2.getNumberOfItemsOfList(l2));
		assertTrue(table.getNumberOfItemsOfList(table)==l1Result.getNumberOfItemsOfList(l1Result));
		assertTrue(l1Result.getNumberOfItemsOfList(l1Result)==l2Result.getNumberOfItemsOfList(l2Result));
		}
		catch(IOException io){System.out.println("File not found");}   
		catch(Exception io){System.out.println(io);}  
	}
	
	/**
	 * In this test we want to measure the performance of our search engines when reading a medium txt file.
	 * 
	 * In order to have more reliable performance we calculate the averages of 50 measurements.
	 * 
	 * 1.We measure the time that each search engine spends to create the data structure 
	 *   and we print the average of it in the system console.
	 * 
	 * 2.We measure the time that each search engine spends to search for the popular 
	 *   word "it" in the data structure and we print the average of it in the system console.
	 *   
	 * 3.We check if the number of the words stored in the HashTable equals the number of 
	 *   the HTMLlist produced by the Searcher1.
	 *  
	 * 4.We check that we get the same number of resulted web pages where the word "it" occurs. 
	 */
	@Test
	public void testWithAmediumFile() {
		System.out.println("-----------------------------------------------------testWithAmediumFile-----------------------------------------------------"+" \n");
		long total=0;
		long totalSearchTime=0;
		HTMLlist l1 = new HTMLlist (null,null);
		HTMLlist l2 = new HTMLlist (null,null);
		HTMLlist l1Result = new HTMLlist (null,null);
		HTMLlist l2Result = new HTMLlist (null,null);
		HTMLlist table = new HTMLlist (null,null);
		HashTable table1;
		int wordsInArray =0;
		
		try{
		// Read the file and create the linked list
		for (int i =0; i <50; i++){
			l1 = new HTMLlist (null,null);
			l1Result = new HTMLlist (null,null);
			l1 = searcher.readHtmlList (inputFile2);
			l1Result= searcher.newExists(l1, "it");
			total += searcher.getElapsedTime();
			totalSearchTime += searcher.getSearchElapsedTime();
		}
		System.out.println("The total average time of the SearchCmd2 engine for creating the HTMLlist of a medium file is : "
				+(total/50)+ " milliseconds");
		System.out.println("The total average time of the SearchCmd2 engine for searching the word 'it' in the HTMLlist of a medium file is : "
				+(totalSearchTime/50)+ " milliseconds");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
			
		long total1=0;
		long totalSearchTime1=0;
		// Read the file and create the linked list
		for (int i =0; i <4; i++){
			l2 = new HTMLlist (null,null);
			l2Result = new HTMLlist (null,null);
			l2 = searcher1.readHtmlList (inputFile2);
			l2Result = searcher1.exists(l2, "it");
			total1 += searcher1.getElapsedTime();
			totalSearchTime1 += searcher1.getSearchElapsedTime();
		}
		System.out.println("The total average time of the SearchCmd3 engine for creating the HTMLlist of a medium file is : "
				+(total1/4)+ " milliseconds");
		System.out.println("The total average time of the SearchCmd3 engine for searching the word 'it' in the HTMLlist of a medium file is : "
				+(totalSearchTime1/4)+ " milliseconds");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
			
		long total2=0;
		long totalSearchTime2=0;
		// Read the file and create the linked list
		for (int i =0; i <50; i++){
			table = new HTMLlist (null,null);
			table1 = searcher2.readHashFunction(inputFile2);
			table = searcher2.exists(table1, "it");				
			total2 += searcher2.getElapsedTime();
			totalSearchTime2 += searcher2.getSearchElapsedTime();
			wordsInArray = table1.getItemsinArray() ;
		}
		System.out.println("The total average time of the SearchCmd4 engine for creating the HashTable of a medium file is : "
				+(total2/50)+ " milliseconds");		
		System.out.println("The total average time of the SearchCmd4 engine for searching the word 'it' in the HashTable of a medium file is : "
				+(totalSearchTime2/50)+ " milliseconds");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

		assertTrue(wordsInArray == l2.getNumberOfItemsOfList(l2));
		assertTrue(table.getNumberOfItemsOfList(table)==l1Result.getNumberOfItemsOfList(l1Result));
		assertTrue(l1Result.getNumberOfItemsOfList(l1Result)==l2Result.getNumberOfItemsOfList(l2Result));
		}
		catch(IOException io){System.out.println("File not found");}   
		catch(Exception io){System.out.println(io);}  
	}
	
	/**
	 * In this test we want to measure the performance of our search engines when reading a big txt file.
	 * 
	 * In order to have more reliable performance we calculate the averages of 50 measurements.
	 * 
	 * 1.We measure the time that each search engine spends to create the data structure 
	 *   and we print the average of it in the system console.
	 * 
	 * 2.We measure the time that each search engine spends to search for the popular 
	 *   word "it" in the data structure and we print the average of it in the system console.
	 *   
	 * 3.We check if the number of the words stored in the HashTable equals the number of 
	 *   the HTMLlist produced by the Searcher1.
	 *  
	 * 4.We check that we get the same number of resulted web pages where the word "it" occurs. 
	 */
	@Test
	public void testWithAbigFile() {		
		System.out.println("-----------------------------------------------------testWithAbigFile-----------------------------------------------------"+" \n");
		long total=0;
		long totalSearchTime=0;
		HTMLlist l1 = new HTMLlist (null,null);
		HTMLlist l2 = new HTMLlist (null,null);
		HTMLlist l1Result = new HTMLlist (null,null);
		HTMLlist l2Result = new HTMLlist (null,null);
		HTMLlist table = new HTMLlist (null,null);
		HashTable table1;
		int wordsInArray =0;
		
		try{
		// Read the file and create the linked list
		for (int i =0; i <50; i++){
			l1 = new HTMLlist (null,null);
			l1Result = new HTMLlist (null,null);
			l1 = searcher.readHtmlList (inputFile3);
			l1Result= searcher.newExists(l1, "it");
			total += searcher.getElapsedTime();
			totalSearchTime += searcher.getSearchElapsedTime();
		}
		System.out.println("The total average time of the SearchCmd2 engine for creating the HTMLlist of a big file is : "
				+(total/50)+ " milliseconds");
		System.out.println("The total average time of the SearchCmd2 engine for searching the word 'it' in the HTMLlist of a big file is : "
				+(totalSearchTime/50)+ " milliseconds");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
			
		long total1=0;
		long totalSearchTime1=0;
		// Read the file and create the linked list
		for (int i =0; i <1; i++){
			l2 = new HTMLlist (null,null);
			l2Result = new HTMLlist (null,null);
			l2 = searcher1.readHtmlList (inputFile3);
			l2Result = searcher1.exists(l2, "it");
			total1 += searcher1.getElapsedTime();
			totalSearchTime1 += searcher1.getSearchElapsedTime();
		}
		System.out.println("The total average time of the SearchCmd3 engine for creating the HTMLlist of a big file is : "
				+(total1)+ " milliseconds");
		System.out.println("The total average time of the SearchCmd3 engine for searching the word 'it' in the HTMLlist of a big file is : "
				+(totalSearchTime1)+ " milliseconds");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
			
		long total2=0;
		long totalSearchTime2=0;
		// Read the file and create the linked list
		for (int i =0; i <50; i++){
			table = new HTMLlist (null,null);
			table1 = searcher2.readHashFunction(inputFile3);
			table = searcher2.exists(table1, "it");				
			total2 += searcher2.getElapsedTime();
			totalSearchTime2 += searcher2.getSearchElapsedTime();
			wordsInArray = table1.getItemsinArray() ;
		}
		System.out.println("The total average time of the SearchCmd4 engine for creating the HashTable of a big file is : "
				+(total2/50)+ " milliseconds");		
		System.out.println("The total average time of the SearchCmd4 engine for searching the word 'it' in the HashTable of a big file is : "
				+(totalSearchTime2/50)+ " milliseconds");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

		assertTrue(wordsInArray == l2.getNumberOfItemsOfList(l2));
		assertTrue(table.getNumberOfItemsOfList(table)==l1Result.getNumberOfItemsOfList(l1Result));
		assertTrue(l1Result.getNumberOfItemsOfList(l1Result)==l2Result.getNumberOfItemsOfList(l2Result));
		}
		catch(IOException io){System.out.println("File not found");}   
		catch(Exception io){System.out.println(io);}  
		}
}
		
