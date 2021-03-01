package july4th;

public class RotatedSortedArray2 {
	public static void main(String args[]) 
	{ 
		int arr1[] = {5, 6, 7, 8, 9, 10, 1, 2, 3}; 
		int n = arr1.length; 
		int key = 3; 
		System.out.println("Index of the element is : "
				+ pivotedBinarySearch(arr1, n, key)); 
	}

	private static int pivotedBinarySearch(int[] arr, int n, int key) {
		int pivot = findPivot(arr, 0, n-1); 

		if (pivot == -1) return binarySearch(arr, 0, n-1, key); 

		if (arr[pivot] == key) return pivot; 
		
		if (arr[0] <= key) 
			return binarySearch(arr, 0, pivot-1, key);
		else
			return binarySearch(arr, pivot+1, n-1, key); 
	}
	private static int findPivot(int[] arr, int low, int high) {
		if (high < low) return -1; 
		if (high == low) return low; 

		int mid = (low + high)/2;   
		
		if (arr[low] >= arr[mid]) //search area left sub array
			return findPivot(arr, low, mid -1 );
		else //right sub array
			return findPivot(arr, mid + 1, high); 
	} 

	private static int binarySearch(int[] arr, int low, int high, int key) {
		if (high < low) return -1; 
		
		int mid = (low + high)/2;   
		if (key == arr[mid]) return mid; 
		
		if (key > arr[mid]) 
			return binarySearch(arr, (mid + 1), high, key);
		else
			return binarySearch(arr, low, (mid -1), key); 
	}
}
