package a1;

public class FloodFill {
	/**
	 * Print a 2-dimensional array using least 3 spaces for each value.
	 * Values for each row of the array appear on a single line, and each row
	 * appears on its own line.
	 * 
	 * @param arr a two-dimensional array
	 * @throws IllegalArgumentException if the specified array is empty
	 */
	public static void printArray(int[][] arr) {
		testArrayNotEmpty(arr);
		int rows = numRows(arr);
		int cols = numCols(arr);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int val = arr[r][c];
				System.out.printf("%3d", val);
			}
			System.out.println();
		}
	}
	
	
	// YOUR CODE GOES HERE


	// numRows
	public static int numRows(int[][] arr) {
		return arr.length;
	}
	
	// numCols
	public static int numCols(int[][] arr) {
		if (numRows(arr) == 0) {
			return 0;
		} 
		return (arr[0].length);
	}

	//testArrayNotEmpty
	public static void testArrayNotEmpty(int[][] arr) {
		int rows = numRows(arr);
		if (rows <= 0){
			throw new IllegalArgumentException("rows <= 0");			  
		}
		int cols = numCols(arr);
		if (cols <= 0){
			throw new IllegalArgumentException("cols <= 0");
		}
	}

	// isValidIndex
	public static boolean isValidIndex(int row, int col, int[][] arr) {
		return (row >= 0 && row < numRows(arr) && col >= 0 && col < numCols(arr));
	}


	// floodFill
	public static void floodFill(int row, int col, int[][] arr, int target, int repl) {
		testArrayNotEmpty(arr);
		int r, c;
		if (! isValidIndex(row, col, arr)) {
			throw new IllegalArgumentException("index is not valid");
		}
		if (arr[row][col] != target) {
			return;
		} 
		arr[row][col] = repl;
	
		// up
		r = row - 1;
		c = col;
		if (isValidIndex(r, c, arr)) {
			floodFill(r, c, arr, target, repl);
		}

		//down
		r = row + 1;
		c = col;
		if (isValidIndex(r, c, arr)) {
			floodFill(r, c, arr, target, repl);
		}

		// left
		r = row;
		c = col - 1;
		if (isValidIndex(r, c, arr)) {
			floodFill(r, c, arr, target, repl);
		}

		// right
		r = row;
		c = col + 1;
		if (isValidIndex(r, c, arr)) {
			floodFill(r, c, arr, target, repl);
		}
	}

	// init
	public static void init(int[][] arr, int border, int interior) {
		testArrayNotEmpty(arr);
		int rows = numRows(arr);
		int cols = numCols(arr);

		// top and bottom borders
		for (int c = 0; c < cols; c++) {
			arr[0][c] = border;
			arr[rows - 1][c] = border;
		}

		// left and right borders
		for (int r = 0; r < rows; r++) {
			arr[r][0] = border;
			arr[r][cols - 1] = border;
		}

		// interior
		for (int r = 1; r < (rows - 1); r++) {
			for (int c = 1; c < (cols - 1); c++){
				arr[r][c] = interior;
			}
		}
	}
	
	
	// YOUR CODE ENDS HERE
	//
	// AVOID CHANGING main UNTIL YOUR CODE CAN SUCCESSFULLY FILL THE
	// DEFAULT STARTING ARRAY GIVEN TO YOU
	
	/**
	 * Flood fills an array printing the array before and after filling, and drawing
	 * an image of the array as it is filled.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {

		final int ROWS = 9;
		final int COLS = 9;
		int[][] arr = new int[ROWS][COLS];
		final int BORDER = 0;
		final int INTERIOR = 1;
		final int REPLACEMENT = 6;
		init(arr, BORDER, INTERIOR);
		
		System.out.println("number of rows = " + FloodFill.numRows(arr));
		System.out.println("number of columns = " + FloodFill.numCols(arr));
		FloodFill.testArrayNotEmpty(arr);
		System.out.println("index [1, 1] valid = " + FloodFill.isValidIndex(1, 1, arr));
		

		try {
			// UNCOMMENT ONE OF THE FOLLOWING TWO LINES IF YOU WANT TO TRY A
			// DIFFERENT ARRAY
			
			//arr = FloodFillUtil.readArray("face.txt"); 
			arr = FloodFillUtil.readArray("random.txt"); 
		}
		catch (Exception x) {
			System.out.println(x.getMessage()); 
			System.exit(1); 
		}
		 

		printArray(arr);
		floodFill(3, 3, arr, INTERIOR, REPLACEMENT);
		System.out.println();
		printArray(arr);

		// show an image of the stable configuration
		FloodFillUtil.draw(arr);
	}

}
