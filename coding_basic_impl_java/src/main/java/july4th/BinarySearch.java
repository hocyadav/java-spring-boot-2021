package july4th;

public class BinarySearch {
	public static void main(String[] args) {
//		int[] arr = {5, 6, 7, 8, 9, 10, 1, 2, 3};
//		int key = 6;
//		int i = binarySearch(arr, 0, arr.length - 1, key);
//		System.out.println(i);
//		
//		int t = findPivot(arr, 0, arr.length - 1);
//		System.out.println(t);
		
		int[] arr2 = {4,5,6,7,0,1,2};
		int ii = pivotBS(arr2,0, arr2.length-1, 0);
		System.out.println(ii);
	}

	private static int pivotBS(int[] arr, int i, int j, int key) {
		int pivotIndex = findPivot(arr, i, j);
		System.out.println("pivot :"+pivotIndex);
		//-1 means not rotated
		if(pivotIndex == -1) return binarySearch(arr, i, j, key);
		
		if(key == arr[pivotIndex]) return pivotIndex;
		
		//now go to right sub array or left sub array
		if(arr[0] <= key) {//right side, starting arra value is less than key that means value key will present in this reang
			return binarySearch(arr, 0, pivotIndex - 1, key);//pivot -1 coz pivot we already checked
		} else {
			return binarySearch(arr, pivotIndex + 1, j, key);
		}
	}

	private static int findPivot(int[] arr, int start, int end) {
		if(start > end) return -1;
		if(start == end) return start;
		
		int mid = (start + end)/2;
		if(mid < end && arr[mid] > arr[mid+1]) {
			return mid;
		}
		if(mid > start && arr[mid - 1] > arr[mid]) {
			return mid -1 ;
		}
		
		
		if(arr[start] >= arr[mid]){//wrong check
			return findPivot(arr, start, mid);//= so mid not mid -1 , if test case fail then try mid-1
		} else {
			return findPivot(arr, mid+1, end);
		}
	}

	private static int binarySearch(int[] arr, int start, int end, int key) {
		if(start > end) return -1;
		
		int mid = (start + end)/2;
		if(arr[mid] == key) return mid;
		
		if(key < arr[mid]) {
			return binarySearch(arr, start, mid - 1, key);
		}else {
			return binarySearch(arr, mid + 1, end, key);
		}
	}
}
