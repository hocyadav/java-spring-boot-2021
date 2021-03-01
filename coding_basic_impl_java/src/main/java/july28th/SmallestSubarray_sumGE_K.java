package july28th;

/** smallest sub-array sum >= K**/
public class SmallestSubarray_sumGE_K {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,0};
		int sum = 21;
		smallestSubArray(arr, sum);
	}

	//left..right stable windows : sum < K
	//unstable window : sum > K (update result + remove from left)
	private static void smallestSubArray(int[] arr, int sum) {
		int result = Integer.MAX_VALUE;
		
		int l = 0;
		int windowSum = 0;
		
		for (int r = 0; r < arr.length; r++) {
			windowSum += arr[r];//stable window
			
			while(windowSum > sum && l <= r) {//unstable window : loop is like Map in case of K unique char
				result = Integer.min(result, r - l + 1);
				windowSum -= arr[l];//trying to make stable window
				l++;
			}
			
		}
		
		System.out.println(result);
	}
}
