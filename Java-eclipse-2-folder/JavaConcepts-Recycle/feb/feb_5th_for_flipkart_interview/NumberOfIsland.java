package feb_5th_for_flipkart_interview;

public class NumberOfIsland {

	public static void main(String[] args) {
		int[][] arr1 = {
				{1,0,0,1,1},
				{1,1,0,0,0},
				{1,1,0,1,1},
				{1,1,0,1,1}
		};
		int[][] arr = arr1;//copy original array 
		int t = numberOfIsland(arr);
		System.out.println(t);
	}

	private static int numberOfIsland(int[][] arr) {
		//1. error check
		if(arr == null || arr.length == 0) {
			return 0;
		}
		
		int numIsland = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				if(arr[i][j] == 1) {
					numIsland += dfs(arr, i , j);
				}
			}
		}
		return numIsland;
	}

	private static int dfs(int[][] arr, int i, int j) {
		//1. boundry , error check
		if(i < 0 || i >= arr.length || j < 0 || j >= arr[i].length || arr[i][j] == 0) {
			return 0;
		}
		//2. sink island
		arr[i][j] = 0;
		
		//3. sink others island
		dfs(arr, i + 1, j);
		dfs(arr, i - 1, j);
		dfs(arr, i, j + 1);
		dfs(arr, i, j - 1);
		
		return 1;
	}
}
// 3