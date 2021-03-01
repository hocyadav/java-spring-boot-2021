package march14th;

public class Matrix_rotation_outplace {
	public static void main(String[] args) {
		int[][]arr= {{1,2,3,10,17},{4,5,6,11,18},{7,8,9,12,19},{13,14,15,16,20},{21,22,23,24,25}};
		printArry(arr);
		//rotate2dArrayClockWise_OutPlace(arr);
		rotate2dArrayClockWise_InPlace(arr);
		printArry(arr);
		
		
	}
	
	private static void rotate2dArrayClockWise_InPlace(int[][] a) {
		
		//traverse matrix --> update 4 value at a time i.e. cyclic form
		//i , j 
		//j , (row_len - row_num - 1)
		//(row_len - row_num - 1) , (col_len - col_num - 1)
		//(col_len - col_num - 1) , i
		int N = a.length;
		for(int i = 0 ; i < N / 2; i++) {
			for(int j = i ; j < N - i - 1; j++) {

				int rowEndIndex = N - i - 1;
				int colEndIndex = N - j - 1;
				
				//swap 4 items in cyclic order
				int temp 					= a[i][j]; 
				
				a[i][j] 					= a[colEndIndex][i]; 
				a[colEndIndex][i] 			= a[rowEndIndex][colEndIndex]; 
				a[rowEndIndex][colEndIndex] = a[j][rowEndIndex]; 
				a[j][rowEndIndex] 			= temp; 
				
				//swap - working
//	            a[i][j] = a[N - j - 1][i]; 
//	            a[N - j - 1][i] = a[N - i - 1][N - j - 1]; 
//	            a[N - i - 1][N - j - 1] = a[j][N - i - 1]; 
//	            a[j][N - i - 1] = temp; 
			}
		}
		
		
	}

	private static void rotate2dArrayClockWise_OutPlace(int[][] arr) {
		int[][] arr2 = new int[arr[0].length][arr.length];//new matrix row length of old matrix become column and col become row len
		
		
		//traverse matrix
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr2.length; j++) {
				arr2[]
			}
		}
		
	}

	private static void printArry(int[][] arr) {
		int len = arr.length;
		for(int r=0; r<len; r++) {
			for(int c=0; c<len; c++)
				System.out.print(arr[r][c]+"	");
			System.out.println();
		}
		System.out.println("-------------------------");
	}
	
}
