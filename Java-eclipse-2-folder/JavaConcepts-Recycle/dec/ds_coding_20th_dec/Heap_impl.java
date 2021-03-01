package ds_coding_20th_dec;
/**
 * 
 * @author Hariom Yadav | 20-Dec-2019
 *
 */

public class Heap_impl {
	public static void main(String[] args) {
		//sorted array -> non lead node -> build heap -> heapify
		
		int[] arr = {1,3,5,7,8,9,10};
		print(arr, arr.length);
		
		buildHeap(arr,arr.length);
		print(arr, arr.length);
		
		int newLength = deleteFromHeap(arr,arr.length);
		System.out.println(newLength);
		print(arr, newLength);
		
	}

	private static int deleteFromHeap(int[] arr, int length) {
		//1. get root value and last value + swap both value
		//2. call heapify from non leaf node to 0
		//3. return length - 1;
		
		arr[0] = arr[length-1];
		int new_length = length-1;
		for(int i = new_length/2 ; i>=0; i--)
			heapify(arr, length, i);
		return length-1;
	}

	private static void print(int[] arr, int len) {
		for(int i=0; i<len; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	private static void buildHeap(int[] arr, int length) {
		//1. non leaf node
		//2. for loop -> heapify
		
		int nn = length/2 - 1;
		for(int i = nn; i >= 0; i--)
			heapify(arr,length,i);
	}

	private static void heapify(int[] arr, int length, int i) {
		//1 find all index
		//2 find largest index
		//3 check input root index is same or not -> if different then swap + heapify
		
		int largest = i;
		int left	= i*2 + 1;
		int right	= i*2 + 2;
		
		if(left < length && arr[left] > arr[largest])
			largest = left;
		if(right < length && arr[right] > arr[largest])
			largest = right;
		
		if(i != largest) {
			int t 	= arr[i];
			arr[i]	= arr[largest];
			arr[largest] = t;
			
			heapify(arr,length, largest);
		}
	}
}

/**

1 3 5 7 8 9 10 
10 8 9 7 3 1 5 
6
9 8 5 7 3 1 

 */
