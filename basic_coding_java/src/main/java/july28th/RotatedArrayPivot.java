package july28th;

public class RotatedArrayPivot {
	public static void main(String[] args) {
		int arr1[] = {4,5,6,7,8,0,1,2}; 
		printArray(arr1);
		
		int target = 0;
		int pivotBS = findElementInRotatedArray(arr1, arr1.length, target);
		System.out.println(pivotBS);
	}
	
	private static int  findElementInRotatedArray(int[] arr, int n, int key) {
		int p = findPivot(arr, 0, n - 1);
		System.out.println(p);
		
		if(p == -1) {
			return binarySearch(arr, 0, n - 1, key);
		}
		if(arr[p] == key) {
			return p;
		}
		
		if(arr[0] <= key) //our target is on left side of pivot
			return binarySearch(arr, 0, p - 1, key);
		 else 
			return binarySearch(arr, p + 1, n - 1, key);
		
	}

	private static int binarySearch(int[] arr, int low, int high, int key) {
		if(high < low) return -1;

		//int mid = low + (high - low)/2;//start + end /2
		int mid = (low + high)/2;//start + end /2
		
		if(arr[mid] == key) return mid;
		
		if(key <= arr[mid]) {//so target is coming before mid
			return binarySearch(arr, low, mid, key);
		}else {
			return binarySearch(arr, mid + 1, high, key);
		}
	}

	private static int findPivot(int[] arr, int low, int high) {
		if(low > high ) return -1;
		if(low == high) return low;
		
		int mid = low + (high - low)/2;
		if(mid < high && arr[mid] > arr[mid + 1]) {
			return mid;
		}
		if(mid > low && arr[mid - 1] > arr[mid]) {
			return mid - 1;
		}
		//go left / right
		if(arr[low] >= arr[mid]) {
			return findPivot(arr, low, mid - 1);
		} else {
			return findPivot(arr, mid + 1, high);
		}
		
	}

	private static void printArray(int[] arr1) {
		for (int i : arr1) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
}
