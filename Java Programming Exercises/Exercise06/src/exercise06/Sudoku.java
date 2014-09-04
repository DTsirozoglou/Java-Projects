package exercise06;

public class Sudoku
{
public static void main(String[] args)
{
		// Row and column Latin but with invalid subsquares
		String config1 = "1234567892345678913456789124567891235678912346" 
		+ "78912345789123456891234567912345678";
		
		String[][] puzzle1 = makeSudoku(config1);
		
		if (isValidSudoku(puzzle1))
		{
			System.out.println("This puzzle is valid.");
		}
		
		else
		{
			System.out.println("This puzzle is invalid.");
		}
		
		System.out.println(getPrintableSudoku(puzzle1));
		System.out.println("--------------------------------------------------");
		
		// Row Latin but column not Latin and with invalid subsquares
		String config2 = "12345678912345678912345678912345678912345678"
		+ "9123456789123456789123456789123456789";
		
		String[][] puzzle2 = makeSudoku(config2);
		
		if (isValidSudoku(puzzle2))
		{
			System.out.println("This puzzle is valid.");
		}
		
		else
		{
			System.out.println("This puzzle is invalid.");
		} 
		
		System.out.println(getPrintableSudoku(puzzle2));
		System.out.println("--------------------------------------------------"); 
		
		// A valid sudoku
		String config3 = "25813764914698532779324685147286319558149273663"
				+ "9571482315728964824619573967354218";
		String[][] puzzle3 = makeSudoku(config3);
		if (isValidSudoku(puzzle3))
		{
			System.out.println("This puzzle is valid.");
		}
		else 
		{
			System.out.println("This puzzle is invalid.");
		} 
		
		System.out.println(getPrintableSudoku(puzzle3));
		System.out.println("--------------------------------------------------"); 
}
		
public static String[][] makeSudoku(String s)	
	{
		int SIZE = 9;
		int k = 0;
		String[][] x = new String[SIZE][SIZE];
		
		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				x[i][j] = s.substring(k, k + 1);
				k++;
			}
		}
		return x;
	}

public static String getPrintableSudoku(String[][] x)
	{
		int SIZE = 9;
		String temp = "";
		for (int i = 0; i < SIZE; i++)
		{
			if ((i == 3) || (i == 6))
			{	
				temp = temp + "=================\n";
			}
			for (int j = 0; j < SIZE; j++)
			{
				if ((j == 3) || (j == 6))
				{
					temp = temp + " || ";
				}
				temp = temp + x[i][j];
			}
			temp = temp + "\n";
		}
		return temp;
	}
	
/**
 * The method isValidSudoku takes a two dimensional string array and returns if it is a valid sudoku or not
 * @param x is the  two dimensional array string representation of the Sudoku
 * @return true if it is a valid sudoku
 */
public static boolean isValidSudoku(String[][] x)
	{
		return rowsAreLatin(x) && colsAreLatin(x) && goodSubsquares(x);
	}	

/**
 ** The method rowsAreLatin takes a two dimensional string array and returns if it has valid rows for the Sudoku.
 * @param x is the  two dimensional array string representation of the Sudoku
 * @return true if all of the rows are valid
 */

public static boolean rowsAreLatin(String[][] x)
	{ 
	
		for (int i=0;i<9;i++)
		{
			// we create an array of one row each time and we call the method rowIsLatin to check if this row is valid.
			int[] row = new int[9];
			for (int j=1;j<10;j++)
			{
				row[j-1]=Integer.parseInt(x[i][j-1]);
			}
			
			//At any time if one of the rows is not valid then Sudoku is also invalid.
			if (rowIsLatin(row)==false)
			{
				return false;
			}
		}
		return true;
	}


/**The method rowIsLatin takes an integer array. and checks if it satisfy the "Latin property".
 * @param x is an array with the integer values of a single Sudoku line (row|column)
 * @return true if the line is valid
 */
public static boolean rowIsLatin(int[] x)
	{
		//We start by initializing the found array to all false values 
		//to indicate that none of the symbols 1, 2, …, 9 have yet be found in the current row.
		boolean[] found = new boolean[9] ;
		
		for (int k=0; k<9;k++)
		{
			found[k]=false;
		}
		
		for (int i=0; i<9;i++)
		{
			// we keep a counter to see at the end if the element checked was among the desired numbers. 
			int elemCounter=0;
			
			for (int j=1;j<10;j++)
			{
				if (x[i] ==j)
				{
					if (found[j-1]==false)
					{
						found[j-1]=true;
						elemCounter++;
					}
					//if found[j-1]==true, then it means that this element is repeated so it is invalid.
					else
					{
						return false;
					}
				}
			}
			
			// if the element was not between 1 to 9.
			if (elemCounter==0)
			{
				return false;
			}				
		}
		return true;	
	}
/**
 ** The method colsAreLatin takes a two dimensional string array and returns if it has valid columns for the Sudoku.
 * @param x is the  two dimensional array string representation of the Sudoku
 * @return true if all of the columns are valid
 */

public static boolean colsAreLatin(String[][] x)
	{ 
	
		for (int i=0;i<9;i++)
		{
			// we create an array of one column each time and we call the method rowIsLatin to check if the created array satisfies the "Latin property".
			int[] column = new int[9];
			for (int j=1;j<10;j++)
			{
				column[j-1]=Integer.parseInt(x[j-1][i]);
			}
			
			//At any time if one of the columns is not valid then Sudoku is also invalid.
			if (rowIsLatin(column)==false)
			{
				return false;
			}
		}
		return true;
	}

/** The method goodSubsquares creates an array of one subsquare each time and checks if the created array satisfies the "Latin property"
 * @param x is the  two dimensional array string representation of the Sudoku
 * @return true if all of the Subsquares of array x are valid
 */
public static boolean goodSubsquares(String[][] x)
	{
		int ColumnCount=0;
		int rowCount = 0;
		
		// We loop 9 times to check 1 subsquare at time.
		for (int k=0;k<9;k++)
		{
			int l = 0;
			int[] subsquare = new int[9];
			
			//We fill our new integer array with the values of the subsquare
			for (int i=0+rowCount;i<3+rowCount;i++)
			{
				for (int j= 0+ColumnCount;j<3+ColumnCount;j++)
				{
					subsquare[l] = Integer.parseInt(x[i][j]);
					l++;
				}
			}
			
			//We check if the subsquare satisfies the "Latin" property.
			if (rowIsLatin(subsquare)==false)
			{
				return false;
			}
			
			//with this way we move through the subsquares of Sudoku so we ensure we check all of the nine.
			ColumnCount = ColumnCount+3;
			if (ColumnCount>7)
			{
				ColumnCount = 0;
				rowCount = rowCount+3;
			}
		}
		return true;
	}
}