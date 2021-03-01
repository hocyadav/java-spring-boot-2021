package ds_algo_28th_nov;
/**
 * 
 * @author Hariom Yadav | 28-Nov-2019
 *
 */

public class MergeSort_impl_array {

	public static void main(String[] args) {
		int[] arr = {12,1,4,2,5};
		
		System.out.print("Input : ");
		printArray(arr);
		
		mergeSortArray(arr,0,arr.length-1);
		
		System.out.print("Output: ");
		printArray(arr);
		
	}
	
	private static void printArray(int[] arr) {
		for(int i=0; i< arr.length;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
	
	private static void mergeSortArray(int[] arr, int start, int end) {
		if(start>=end) return;
		else {
			//1 mid  
			int mid = (start + end)/2;
			
			//2 rec for both half
			mergeSortArray(arr, start, mid);
			mergeSortArray(arr, mid+1, end);
			
			//3 sort both half
			sortTwoSortedArray(arr, start, mid, end);
		}
		
	}
	/**
	 * merge 2 sorted array
	 * @param arr
	 * @param start
	 * @param mid
	 * @param end
	 */
	private static void sortTwoSortedArray(int[] arr, int start, int mid, int end) {
		//left, right side array length
		int l = (mid - start) +1;//end - start +1
		int r = end - mid;
		
		//array declare left and right
		int[] temp_left = new int[l];
		int[] temp_right = new int[r];
		
		//fill temp array
		for(int i=0;i<l; i++)
			temp_left[i] = arr[start+i];//step increment
		
		for(int j=0; j<r; j++)
			temp_right[j] = arr[mid+1 + j];
		
		//compare and copy in main array
		int i=0, j=0, k=start;
		
		while(i<l && j<r) {
			if(temp_left[i] < temp_right[j]) {
				arr[k] = temp_left[i];
				k++; i++;
			}else {
				arr[k] = temp_right[j];
				k++; j++;
			}
		}
		
		//copy remaining data from temp array into main array
		while(i < l) {
			arr[k] = temp_left[i];
			k++; i++;
		}
		
		while(j < r) {
			arr[k] = temp_right[j];
			k++; j++;
		}
		
	}

}
/**
 * 
Input : 12 1 4 2 5 
Output: 1 2 4 5 12 
 * 
 */
