package june13th;

public class SlidingWindow_concept {
	public static void main(String[] args) {
		int[] arr = { 1, 4, 2, 10, 2, 3, 1, 0, 20 }; //24
		int k = 4;
		int result = slidingWindow_BruteForce(arr, k);//output : max sum
		System.out.println(result);
		
		int result2 = slidingWindow_Better(arr, k);//output : max sum
		System.out.println(result2);
		
	}
	
	private static int slidingWindow_BruteForce(int[] arr, int k) {
		
		//null check
		int result = 0;
		if(arr.length == 0 || k == 0) {
			return result;
		}
		result = Integer.MIN_VALUE;
		//2 for loop
		for (int i = 0; i < arr.length - k + 1; i++) {
			int localSum = 0;
			for (int j = 0; j < k; j++) {
				localSum += arr[i + j];
				result = Math.max(localSum, result);
			}
		}
		return result;
		
	}

	private static int slidingWindow_Better(int[] arr, int k) {
		int result = 0;
		
		if(arr.length == 0 || k == 0) {
			return result;
		}
		
		//1st window sum
		int firstWindSum = 0;
		for (int i = 0; i < k; i++) {
			firstWindSum += arr[i];
		}
		
		int localSum = firstWindSum;//assign local with 1st window sum, in brute force we assign 0
		for (int i = k; i < arr.length; i++) {
			localSum += arr[i] - arr[i - k];
			result = Math.max(localSum, result);
		}
		return result;
	}
}
