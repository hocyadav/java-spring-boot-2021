package concept_4th_jan;
/**
 * 
 * @author Hariom Yadav | 04-Jan-2020
 *https://www.geeksforgeeks.org/window-sliding-technique/
 */
public class Sliding_WIndow_concept {

	public static void main(String[] args) {
		int[] arr = {10,30,20,40,10,1};
		int k = 2;
		
		slidingWindow(arr, arr.length, k);
		slidingWindow2(arr, arr.length, k);
	}
	
	//TC : n*k //2 for loop : 1st for loop n-k times 2nd for loop k times
	//Brute Force 
	private static void slidingWindow2(int[] arr, int length, int k) {
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<=length-k; i++) {
			int currentMax = 0;
			for(int j=0; j<k; j++) {
				currentMax += arr[i+j];
			}
			max = Math.max(currentMax, max);
		}
		
		System.out.println(max);
	}
	
	//TC : n
	//Window Sliding Technique
	private static void slidingWindow(int[] arr, int length, int k) {
		int firstKMax = 0;
		for(int i=0; i<k; i++)
			firstKMax += arr[i];
		
		int currentMax = firstKMax;
		for(int i=k; i<length; i++) {
			currentMax += arr[i] - arr[i-k];
			firstKMax = Math.max(currentMax, firstKMax);
		}
		System.out.println(firstKMax);
	}
}
// 60
