package ds_3rd_jan;

import java.util.Arrays;

public class dp_edit_distance_in2string {
	public static void main(String[] args) {
		String str1 = "sunday";
		String str2 = "saturday";
		
		int minLen = editDistance(str1, str2, str1.length(), str2.length());
		System.out.println(minLen);
		
		int minLen2 = editDistance2(str1, str2, str1.length(), str2.length());
		System.out.println(minLen2);
	}

	private static int editDistance2(String str1, String str2, int n1, int n2) {
		int[][] dp = new int[n1+1][n2+1];
		
		//str length 0
		for(int i=0; i<=n1; i++)
			dp[i][0] = i;
		
		for(int j=0; j<=n2; j++)
			dp[0][j] = j;
		
		print2dArray(dp);//best way
		print2d_Array(dp);
		print2d_Array_2(dp);//best way 
		//str length 1 to max
		for(int i=1; i<=n1; i++) {//starting from string of length size 1
			for(int j=1; j<=n2; j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1))
					dp[i][j] = dp[i-1][j-1];
				else {
					dp[i][j] = Math.min( dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]) );
				}
			}
		}
		return dp[n1][n2];
	}

	private static void print2dArray(int[][] dp) {
		for(int[] row : dp) {
			System.out.println(Arrays.toString(row));//converting sinle array into string 
		}
		System.out.println();
	}
	
	private static void print2d_Array(int[][] dp) {
		for(int r=0; r<dp.length; r++) {
			for(int c=0; c<dp[r].length; c++) {
				System.out.print(dp[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static void print2d_Array_2(int[][] dp) {
		for(int[] row : dp) {//get row : data type is 1d array
			for(int col : row) {//get col from above row 1d array : data type is int
				System.out.print(col+" ");
			}
			System.out.println();
		}
	}
	

	private static int editDistance(String str1, String str2, int m, int n) {
		
		int[][] dp = new int[m+1][n+1]; // 0 index is for 0 length string
		
		for(int i=0; i<=m; i++) {//i is length // //starting from string of length size 0
			
			for(int j=0; j<=n; j++) {//j is length
				if(i == 0)
					dp[i][j] = j;
				else if(j == 0)
					dp[i][j] = i;
				else if(str1.charAt(i-1) == str2.charAt(j-1))
					dp[i][j] = dp[i-1][j-1];
				else {
					int insert = dp[i][j-1];
					int delete = dp[i-1][j];
					int update = dp[i-1][j-1];
					
					dp[i][j] = Math.min( update, Math.min(insert, delete) ) + 1;
				}
			}
			
		}
		return dp[m][n];
	}
	
	
}
