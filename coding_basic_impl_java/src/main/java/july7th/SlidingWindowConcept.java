package july7th;
/** https://www.geeksforgeeks.org/window-sliding-technique/ **/
public class SlidingWindowConcept {
	public static void main(String[] args) {
		int[] arr = {10,20,30,10,60};
		int k = 2;
		slidingWinConcept(arr, k);//Sliding windows means  --> convert 2 for loop into 1 for loop
	}

	private static void slidingWinConcept(int[] arr, int k) {
		int result = Integer.MIN_VALUE;
		//get k length sum
		int firstKsum = 0;
		for(int j = 0; j < k; j++) {
			firstKsum += arr[j];
		}
		
		int currentSum = firstKsum;
		for(int j = k; j < arr.length; j++) {
			currentSum += arr[j] - arr[j - k];
			result = Math.max(result, currentSum);
		}
		System.out.println(result);
	}
}
