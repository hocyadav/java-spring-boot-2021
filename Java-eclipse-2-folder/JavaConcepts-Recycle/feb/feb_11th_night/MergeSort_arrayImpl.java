package feb_11th_night;
/**
 * 
 * @author Hariom Yadav | 11-Feb-2020
 *
 */
public class MergeSort_arrayImpl {

	public static void main(String[] args) {
		int[] arr = {100, 1, 2, 34, 5, 21, 7};
		print(arr);
		
		mergeSort(arr, 0, arr.length - 1);//recursion 1
		
		print(arr);
	}

	private static void mergeSort(int[] arr, int start, int end) {
		if(start >= end) {
			return;
		}
		
		int mid = (start + end)/2;
		mergeSort(arr, start, mid );//FCS : think as temp1
		mergeSort(arr, mid + 1, end);//FCS : think as temp2
		
		merge2SortedArray(arr, start, mid, end); // operation on temp1 and temp2
		
	}

	//1. declare 2 array
	//2. fill 2 array
	//3. with the help of 2 array fill original array
	private static void merge2SortedArray(int[] arr, int start, int mid, int end) {
		int left 	= (mid - start) + 1;
		int right 	= end - mid;
		
		int[] leftArray 	= new int[left];
		int[] rightArray 	= new int[right];
		
		for(int i = 0; i < left; i++)
			leftArray[i] 	= arr[start + i];
		
		for(int i = 0; i < right; i++)
			rightArray[i] 	= arr[mid + 1 + i];
		
		int i = 0, j = 0, k = start;
		while(i < left && j < right) {
			if(leftArray[i] < rightArray[j])
				arr[k++] = leftArray[i++];
			else
				arr[k++] = rightArray[j++];
		}
		
		//copy remaining
		while(i < left) {
			arr[k++] = leftArray[i++];
		}
		
		while(j < right) {
			arr[k++] = rightArray[j++];
		}
	}

	private static void print(int[] arr) {
		for(int n : arr) {
			System.out.print(n+" ");
		}
		System.out.println();
	}

}
/**
100 1 2 34 5 21 7 
1 2 5 7 21 34 100 

*/