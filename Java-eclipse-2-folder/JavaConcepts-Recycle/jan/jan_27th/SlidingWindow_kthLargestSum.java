package jan_27th;
/**
 * 
 * @author Hariom Yadav | 27-Jan-2020
 *
 */
public class SlidingWindow_kthLargestSum {
	public static void main(String[] args) {
		int[] arr = {1,3,4,1,0,6,10};
		int k = 3;
		int sum = slidingSum(arr, k);
		System.out.println(sum);
	}

	private static int slidingSum(int[] arr, int k) {
		int firstKsum = 0;
		for(int i = 0; i < k; i++) {
			firstKsum += arr[i];
		}
		
		int maxSlidingSum = Integer.MIN_VALUE;
		int localSum = firstKsum;
		for(int i = k; i < arr.length;i++) {
			localSum += arr[i] - arr[i-k];
			maxSlidingSum = Math.max(localSum, maxSlidingSum);
		}
		return maxSlidingSum;
	}
}
/**
16
*/