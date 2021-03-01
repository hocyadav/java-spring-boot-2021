package feb_25th;
/**
 * 
 * @author Hariom Yadav | 25-Feb-2020
 *
 */
public class MaximumSumSubArray_KadensAlgo {
	public static void main(String[] args) {
		int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
		largestSubArraySum(arr);
		
		int[] arr2 = {-2, -3, -4, -11, -2, -12, -5, -3};
		largestSubArraySumHandleIfAllArrayNegative(arr2);
	}

	private static void largestSubArraySumHandleIfAllArrayNegative(int[] arr) {
		
		int localMax = arr[0];
		int result = arr[0];
		for(int r = 0; r < arr.length; r++) {
			localMax = Math.max(arr[r], localMax + arr[r]);
			result = Math.max(localMax, result);
		}
		
		System.out.println("largestSubArraySumHandleIfAllArrayNegative "+result);
	}

	private static void largestSubArraySum(int[] arr) {
		if(arr == null || arr.length == 0) {
			System.out.println("");
			return;
		}
		
		//1 pointer traverse
		int localMax = 0;
		int result = 0;
		int startIndex = 0;
		int endIndex = 0;
		int s = 0;
		for(int r = 0; r < arr.length; r++) {
			localMax += arr[r];
			if(result < localMax) {
				result = localMax;
				//extra
				startIndex = s;//set start index globally
				endIndex = r;//end index is nothing but single pointer traversal point
			}
			//result = Math.max(result, localMax);
			if(localMax < 0) {
				localMax = 0;
				//extra
				s = r + 1;//start index
			}
		}
		System.out.println("largestSubArraySum");
		System.out.println(result);
		System.out.println(startIndex);
		System.out.println(endIndex);
	}
}
/**
 * 
largestSubArraySum
7
2
6
largestSubArraySumHandleIfAllArrayNegative -2

 */
