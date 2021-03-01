package jan_15th;
/**
 * 
 * @author Hariom Yadav | 15-Jan-2020
 * Find max windows sum, window size is k
 */
public class Array_window_concept {

	public static void main(String[] args) {
		int[] arr = {10,20,30,40,50};
		int k = 6;
		
		int sum = fun(arr,k);
		System.out.println(sum);
		
	}

	private static int fun(int[] arr, int k) {
		
		if(k > arr.length) {
			return -1;
		}
		
		int intialWindowSum = 0;
		//1. 1st k window size
		for(int i = 0; i < k; i++) {
			intialWindowSum += arr[i];
		}
		
		//2. remaining window size , 
			//if window size greater then update sum
		int currentWindowSum = intialWindowSum;
		for(int i = k; i <= arr.length-1; i++) {
			currentWindowSum += arr[i] - arr[i-k];
			intialWindowSum = Math.max(currentWindowSum, intialWindowSum);
		}
		return intialWindowSum;
	}

}
