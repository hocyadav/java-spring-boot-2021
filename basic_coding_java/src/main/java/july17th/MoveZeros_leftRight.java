package july17th;

public class MoveZeros_leftRight {
	public static void main(String[] args) {
		int[] arr = {0, 1, 3, 0, 33, 12, 0};
		printArray(arr);
		moveZeroRightSide(arr);
		printArray(arr);
		
		int[] arr2 = {0, 10, 0, 0, 3, 1, 0};
		printArray(arr2);
		moveZeroLeftSide(arr2);
		printArray(arr2);
		
	}
	/** logic : take nonzero index that will only store non zero value while traversing**/
	private static void moveZeroRightSide(int[] arr) {
		int nonZeroIndex = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != 0) {//this is our target
				arr[nonZeroIndex] = arr[i];
				nonZeroIndex++;
			}
		}
		
		for(int i = nonZeroIndex; i < arr.length; i++) {
			arr[i] = 0;
		}
	}

	private static void moveZeroLeftSide(int[] arr) {
		int nonZeroIndex = arr.length - 1;
		
		for(int i = arr.length - 1; i >= 0; i--) {
			if(arr[i] != 0) {//this is our target
				arr[nonZeroIndex] = arr[i];
				nonZeroIndex--;
			}
		}
		
		for(int i = nonZeroIndex; i >= 0; i--) {
			arr[i] = 0;
		}
	}

	private static void printArray(int[] arr) {
		for (int i : arr) {
			System.out.print(i+" ");
		}
		System.out.println();
	}


}
/**
0 1 3 0 33 12 0 
1 3 33 12 0 0 0 
0 10 0 0 3 1 0 
0 0 0 0 10 3 1 

*/