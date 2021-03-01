package feb_28th;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RotateArray90degree {
	public static void main(String[] args) {
		int[][] arr = {
				{1, 2, 3, 4}, 
				{5, 6, 7, 8}, 
				{9, 10, 11, 12},
				{13, 14, 15, 16}
				};
//		System.out.println("Input ");
//		print2DArray(arr);
//		int m = arr.length;
//		int n = arr[0].length;
		
		//m1 : new array + 2 for loop -> logic : arr2[c][m - r - 1] = arr[r][c]
		//rotate90degree(arr, m, n);
		
		//m2 : step 1 :transpose matrix + step 2 :flip horizontally
		//matrixTranspose(arr);//1. swap diagonally : https://www.youtube.com/watch?v=IdZlsG6P17w&list=WL&index=97&t=16s
	
		 Map<Character,Integer[]> map = new HashMap();
		 map.put('A', new Integer[4]);
		 
		 Integer[] intArr = map.get('A');
		 Arrays.fill(intArr, new Integer(0));
		 intArr[0]++;
		 System.out.println(Arrays.asList(intArr));
	}

	//step 1: logic : swap(arr[i][j], arr[j][i])
	private static void matrixTranspose(int[][] arr) {
		for(int r = 0; r < arr.length; r++) {//row by row
			for(int c = r; c < arr.length; c++) {//c start from r
				//if(r == c) continue; //optimize code
				int t = arr[r][c];
				arr[r][c] = arr[c][r];
				arr[c][r] = t;
			}
		}
		print2DArray(arr);
		flipHorizontal(arr);
	}

	//step 2: logic : swap(arr[i][j] , arr[i][n - 1 - j])
	private static void flipHorizontal(int[][] arr) {
		for(int r = 0; r < arr.length; r++) {//row by row
			for(int c = 0; c < arr.length/2; c++) {//col by col
				int t = arr[r][c];
				arr[r][c] = arr[r][arr.length - 1 - c];//arr.length - 1 will give last index, - c will traverse from last, i.e. when ever from right side c will incerease then it will decrese from left side so that it will come close 
				arr[r][arr.length - 1 - c] = t;
			}
		}
		print2DArray(arr);
	}

	private static void rotate90degree(int[][] arr, int m, int n) {
		int[][] arr2 = new int[n][m];
		
		for(int r = 0; r < m; r++) {
			for(int c = 0; c < n; c++) {//c start from 0
				//System.out.println(r + " " + c);
				System.out.println(c +" "+ (m - r -1));
				arr2[c][m - r - 1] = arr[r][c];
				//c , m - r - 1 : left side variable 0,1,2,3 and right side fix as 3 :output : 	03,01,02,03
				//r , c : left side fixed 0, and right side variable 0,1,2,3 : 		output : 	00,01,02,03
			}
		}
		System.out.println("Output ");
		print2DArray(arr2);
	}

	private static void print2DArray(int[][] arr) {
		for(int r = 0; r < arr.length; r++) {
			for(int c = 0; c < arr[r].length; c++) {
				System.out.print(arr[r][c]+"	");
			}
			System.out.println();
		}
		System.out.println("----------------");
	}
}
