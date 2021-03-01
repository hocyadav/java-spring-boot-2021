package interview_amazon_23rd_nov;

public class MatixRotation {

	public static void main(String[] args) {
		
	int[][]mat= {{1,2,3,10,17},{4,5,6,11,18},{7,8,9,12,19},{13,14,15,16,20},{21,22,23,24,25}};
	printMat(mat);	
	rotate(mat.length,mat);
	System.out.println("**********************");
	printMat(mat);	
	}
	
	public static void printMat(int[][] mat) {
		int n=mat.length;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(mat[i][j]+", ");
			}
			System.out.println();

		}
	}
	public static void rotate(int size,int[][] mat) {
		
		int n=size;
		for(int i=0;i<size/2;i++) {
			
			for(int j=i;j<n-i-1;j++) {
				int sub=mat[i][j];
				int x=i;
				int y=j;
				for(int k=0;k<4;k++) {
					int temp=mat[y][n-x-1];
					mat[y][n-x-1]=sub;
					sub=temp;
					int x1=x;
					x=y;
					y=n-x1-1;					
				}
			}
		}
	}
}
