package algo_25th_nov;
/**
 * 
 * @author Hariom Yadav | 25-Nov-2019
 *
 */
public class MergeSort_impl_using_Array {
	public static void main(String[] args) {
		int[] arr = {2,4,1,6,9,8,3};
		print_(arr);
		
		int start	= 0;
		int end		= arr.length-1;
		mergeSort(arr,start, end);
		
		print_(arr);
	}

	private static void print_(int[] arr) {
		for(int i:arr)
			System.out.print(i+" ");
		System.out.println();
	}

	private static void mergeSort(int[] arr, int start, int end) {//find min, call recursive left right, merger both 
		if(start >= end)//return when arary become single value 
			return;
		else {
			int mid = (start+end)/2;
			mergeSort(arr, start, mid);//1st sorted arry - from start index to min
			mergeSort(arr, mid+1, end);//2nd sorted arry - from mid+1 index to end
			mergeTwoSortedArray(arr, start, mid, end);//merge 2 sorted array
		}
	}

	private static void mergeTwoSortedArray(int[] arr, int start, int mid, int end) {
		//size of 2 sub array
		int leftSize	= (mid - start) + 1;//end - start + 1
		int rightSize	= (end - mid);		//start index is mid+1
		
		//2 temp array + copy all left and right array content
		int[] leftArr 	= new int[leftSize];
		int[] rightArr	= new int[rightSize];
		
		//copy
		for(int i=0; i<leftSize; i++)
			leftArr[i] 	= arr[start + i];//initial start = 0, i = 0
		
		for(int j=0; j<rightSize; j++)
			rightArr[j]	= arr[mid+1 + j];//increment by step, intial step is mid+1 and inc by j
		
		int i = 0, j = 0;
		int k = start;
		
		while(i<leftSize && j<rightSize) {
			if(leftArr[i] <= rightArr[j]) {//left sub array is smaller
				arr[k] = leftArr[i]; 
				i++;
			}else {//right sub array is smaller
				arr[k] = rightArr[j]; 
				j++;
			}
			k++;
		}
		//copy remaining elements
		//copy left temp arry
		while(i<leftSize) {
			arr[k] = leftArr[i];
			k++; i++;
		}
		
		while(j<rightSize) {
			arr[k] = rightArr[j];
			k++; j++;
		}
	}
}
/**

2 4 1 6 9 8 3 
1 2 3 4 6 8 9 

 */
