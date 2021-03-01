package interview_amazon_23rd_nov;

public class MatrixRoation90 {

	public static void main(String[] args) {
		int[][] arr = {
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,16}
				
		};
		printArry(arr);
		rotateArry(arr);
		printArry(arr);
	}

	private static void rotateArry(int[][] arr) {
		int len = arr.length;
		for(int i=0; i<len/2; i++) {
			for(int j=0; j<len-i-1; j++) {
				int temp1 = arr[i][j];
				int r = i;
				int c = j;
				//swap
				for(int k=0; k<4; k++) {
					int new_r = c;
					int new_c = len - c - 1;
					int temp2 = arr[new_r][new_c];
					arr[new_r][new_c] = temp1;
					temp1 = temp2;
					int x1 = r;
					r = c;
					c = len - x1 - 1;
				}
				
				
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
