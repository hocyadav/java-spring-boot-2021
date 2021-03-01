package june._2020.leetcode;

public class NxM_Maze_DP {
	static int row = 3;
	static int col = 3;
	static int[][] mat = { 
					{ 1, 1, 0 }, 
					{ 0, 1, 0 },
					{ 0, 1, 1 } 
					};;
	static int[][] temp = new int[row][col];
	
	public static void main(String[] args) {
		printMatrix();
		boolean solveMaze = mazeUtil(0, 0);
		System.out.println(solveMaze);
	}

//	//optional
//	private static boolean solveMaze(int x, int y) {
//		if(mazeUtil(x, y) == false) {
//			return false;
//		}
//		printMatrix();
//		return true;
//	}

	//this is main logic
	private static boolean mazeUtil(int x, int y) {
		if(x == row - 1 && y == col - 1) {//recursion base case \
			temp[x][y] = 1;
			return true;
		}
		if(safeNode(x,y)) {			//backtracking
			temp[x][y] = 1;
			if(mazeUtil(x + 1, y)) return true;
			if(mazeUtil(x, y + 1)) return true;
			temp[x][y] = 0;			//backtracking
		}
		return false;
	}

	private static boolean safeNode(int x, int y) {
		return (x >= 0 && x < row 		//x boundary
				&& y >= 0 && y < col 	//y boundary
				&& mat[x][y] == 1) 		//value is 1
				? true : false;
	}

	private static void printMatrix() {
		int r = mat.length;
		for (int i = 0; i < r; i++) {
			int c = mat[0].length;
			for (int j = 0; j < c; j++) {
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
