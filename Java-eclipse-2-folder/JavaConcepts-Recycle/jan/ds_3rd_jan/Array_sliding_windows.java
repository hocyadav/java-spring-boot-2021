package ds_3rd_jan;

/**
 * 
 * @author Hariom Yadav | 03-Jan-2020
 *
 */
public class Array_sliding_windows {
	public static void main(String[] args) {
		int[] arr = {10, 20, 30, 40, 50};
		int maxSum = fun(arr, arr.length, 2);
		System.out.println(maxSum);
		
		int maxSum2 = fun2(arr, arr.length, 2);
		System.out.println(maxSum2);
	}

	//TC : n
	private static int fun2(int[] arr, int length, int k) {
		//1. find k value and store
		int localMax = 0;
		for(int i=0; i<k; i++)
			localMax += arr[i];
		//2. for loop after k +nextVale -privious1stValueInPriviousWindow
		int currentMax = localMax;
		for(int j=k; j<length; j++) {
			currentMax += arr[j] - arr[j-k];
			localMax = Math.max(localMax, currentMax);//this line is combination of IF + assignment
		}
		return localMax;
	}

	//TC : n*k
	private static int fun(int[] arr, int length, int k) {
		int maxSum = Integer.MIN_VALUE;
		
		for(int i=0; i<=length-k; i++) {
			int currentSum = 0;
			for(int j=0; j<k; j++) {
				currentSum += arr[i+j];
			}
			maxSum = Math.max(maxSum, currentSum);
		}
		return maxSum;
	}
}
/**
90
90
*/