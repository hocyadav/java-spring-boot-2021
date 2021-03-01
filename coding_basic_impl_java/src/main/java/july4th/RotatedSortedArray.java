package july4th;

public class RotatedSortedArray {
	public static void main(String args[]) 
	{ 
		// Let us search 3 in below array 
		int arr1[] = {4,5,6,7,0,1,2}; 
		int n = arr1.length; 
		int key = 0; 
		System.out.println("Index of the element is : "
				+ pivotedBinarySearch(arr1, n, key)); 
	}

	private static int pivotedBinarySearch(int[] arr, int n, int key) {
		int pivot = findPivot(arr, 0, n-1); 

		// If we didn't find a pivot, then  
		// array is not rotated at all 
		if (pivot == -1) 
			return binarySearch(arr, 0, n-1, key); 

		// If we found a pivot, then first  
		// compare with pivot and then 
		// search in two subarrays around pivot 
		if (arr[pivot] == key) 
			return pivot; 
		if (arr[0] <= key) 
			return binarySearch(arr, 0, pivot-1, key); 
		return binarySearch(arr, pivot+1, n-1, key); 
	}

	private static int binarySearch(int[] arr, int low, int high, int key) {
		if (high < low) 
			return -1; 

		/* low + (high - low)/2; */       
		int mid = (low + high)/2;   
		if (key == arr[mid]) return mid; 
		
		if (key > arr[mid]) 
			return binarySearch(arr, (mid + 1), high, key); 
		else
			return binarySearch(arr, low, (mid -1), key); 
	}

	private static int findPivot(int[] arr, int low, int high) {
		// base cases 
		if (high < low)   
			return -1; 
		if (high == low)  
			return low; 

		/* low + (high - low)/2; */
		int mid = (low + high)/2; 
		
		if (mid < high && arr[mid] > arr[mid + 1]) 
			return mid; 
		if (mid > low && arr[mid] < arr[mid - 1]) 
			return (mid-1); 
		
		if (arr[low] >= arr[mid]) 
			return findPivot(arr, low, mid-1); 
		return findPivot(arr, mid + 1, high); 
	} 
}
