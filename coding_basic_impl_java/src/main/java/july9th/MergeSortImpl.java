package july9th;

public class MergeSortImpl {
	public static void main(String[] args) {
		int[] arr = {100,3,44,6,8,2};
		print(arr);
		mergeSort(arr, 0, arr.length - 1);
		print(arr);
	}

	private static void print(int[] arr) {
		for (int i : arr) {
			System.out.print(i+" ");
		}
		System.out.println();
	}

	private static void mergeSort(int[] arr, int start, int end) {
		if(start >= end) 
			return;
		
		int mid = (start + end) / 2;
		
		mergeSort(arr, start, mid);
		mergeSort(arr, mid + 1, end);
		
		merge2SortedArray(arr, start, mid, end);
	}

	private static void merge2SortedArray(int[] arr, int start, int mid, int end) {
		//1. get left sorted array len and right array len
		int leftArrayLen__ = mid - start + 1;
		int rightArrayLen__ = end - mid;
		//2. create temp array
		int[] leftTempAry = new int[leftArrayLen__];
		int[] rightTempAry = new int[rightArrayLen__];
		//3. fill temp array
		for(int i = 0; i < leftArrayLen__; i++) {
			leftTempAry[i] = arr[start + i];
		}
		for (int i = 0; i < rightArrayLen__; i++) {
			rightTempAry[i] = arr[mid + 1 + i];
		}
		//4. store sorted arry from 2 temp array to original arr
		int l = 0;
		int r = 0;
		int k = start;//this is for storing into arr
		
		while(l < leftArrayLen__ && r < rightArrayLen__) {
			if(leftTempAry[l] < rightTempAry[r]) {
				arr[k++] = leftTempAry[l++];
			}else 
				arr[k++] = rightTempAry[r++];
		}
		
		//copy remaining temp array into arr
		while(l < leftArrayLen__) {
			arr[k++] = leftTempAry[l++];
		}
		
		while(r < rightArrayLen__) {
			arr[k++] = rightTempAry[r++];
		}
	}
}
