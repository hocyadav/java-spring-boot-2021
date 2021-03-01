package july29th;

public class SlidingWindow_fixedWindow_withMinimunSUm {
	public static void main(String[] args) {
		int[] arr = { 10, 4, 2, 5, 6, 3, 8, 1 };
		int fixedWindowSize = 3;
		slidingWindow_MinSUm(arr,fixedWindowSize);
	}

	//get 1st window size 
	//travers from k to len
	//get local sum and update min  local sum as result 
	private static void slidingWindow_MinSUm(int[] arr, int windowSize) {
		int result = Integer.MAX_VALUE;
		
		int firstWindowSum = 0;
		for (int i = 0; i < windowSize; i++)
			firstWindowSum += arr[i];
		System.out.println("1st fixed window sum : "+firstWindowSum);
		
		int localSum = firstWindowSum;
		//int l = 0;//2 pointer its like rubber - one end is left and other end is right
		for (int r = windowSize; r < arr.length; r++) {
			localSum += (arr[r] - arr[r - windowSize]);
			System.out.println("fixed window sum : "+localSum);
			result = Integer.min(result, localSum);//here we can store index also 
		}
		System.out.println("min fixed window sum : "+result);
	}
}
/**
1st fixed window sum : 16
fixed window sum : 11
fixed window sum : 13
fixed window sum : 14
fixed window sum : 17
fixed window sum : 12
min fixed window sum : 11


*/