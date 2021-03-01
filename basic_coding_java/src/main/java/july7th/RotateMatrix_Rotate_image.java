package july7th;

public class RotateMatrix_Rotate_image {
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3},
				{4,5,6},
				{7,8,9}};

		//		printMat(matrix);
		//		clockwiseRotate(matrix);//reverse row -> swap symmetric
		printMat(matrix);
		antiClockwiseRotate2(matrix);//swap symmetric -> reverse col
//		antiRotate(matrix);
		printMat(matrix);

	}




	//logic : first reverse row (i.e. row side up down)
	//then swap symmetric(i.e. transpose matrix)
	private static void clockwiseRotate(int[][] matrix) {
		int s = 0;
		int e = matrix.length - 1;
		while(s < e) {
			int[] t = matrix[s];
			matrix[s] = matrix[e];
			matrix[e] = t;
			s++; e--;
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix[0].length; j++) {
				int t = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = t;
			}
		}

	}

	//swap symmetric -> reverse col
	private static void antiClockwiseRotate(int[][] matrix) {
		//swap symmetric
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i; j < matrix[0].length; j++) {
				int t = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = t;
			}
		}

		//reverse col

	}
	private static void antiClockwiseRotate2(int[][] matrix) {
		//reverse col
		int f = 0;
		int l = matrix[0].length - 1;
		while(f < l) {
			//traverse row
			for(int i = 0; i < matrix.length; i++) {//swap 1st col with last col..i.e. i,f -> i,l
				int t = matrix[i][f];
				matrix[i][f] = matrix[i][l];
				matrix[i][l] = t;
			}
			f++; l--;
		}

		//swap symmetric
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix[0].length; j++) {
				int t = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = t;
			}
		}

	}
	private static void printMat(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	//from : https://leetcode.com/problems/rotate-image/discuss/18872/A-common-method-to-rotate-the-image/139220
	public static void antiRotate(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		for(int first=0, last=cols-1; first<last; first++,last--) {
			
			for(int i=0; i<matrix.length; i++) {
				int tmp = matrix[i][first];
				matrix[i][first] = matrix[i][last];
				matrix[i][last] = tmp;
			}
			
		}
		
		for(int i = 0; i < rows; i++){
			for(int j = i+1; j < cols; j++){
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		}
	}


}
