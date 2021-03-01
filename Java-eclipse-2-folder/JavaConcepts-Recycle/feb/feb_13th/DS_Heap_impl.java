package feb_13th;
/**
 * 
 * @author Hariom Yadav | 13-Feb-2020
 *
 */
public class DS_Heap_impl {
	public static void main(String[] args) {
		int[] arr = {1,6,2,3,90,24,4};
		print(arr, arr.length);
		buildHeap(arr, arr.length);
		print(arr, arr.length);
		int new_len = deleteFromRoot(arr, arr.length);
		System.out.println("after delete len : "+new_len);
		print(arr, new_len);
		
		
		int new_len2 = deleteFromRoot(arr, new_len);//calling again delete and taking old length after delete
		System.out.println("after delete len : "+new_len2);
		print(arr, new_len2);
		
		int new_len3 = deleteFromRoot(arr, new_len2);
		System.out.println("after delete len : "+new_len3);
		print(arr, new_len3);

		
		insertAtLastNode(arr, new_len3, 99);//add new element and come to top since it is largest
		print(arr, new_len3);

		
		
		
	}

	private static void insertAtLastNode(int[] arr, int new_len3, int key) {
		//insert at last node and in for loop call heapify
		arr[new_len3-1] = key;
		
		int nn = new_len3/2 - 1;
		for(int i = nn; i >= 0; i--) {
			heapify(arr, new_len3, i);
		}
		
	}

	private static int deleteFromRoot(int[] arr, int length) {
		//1. swap root with last
		//2. decrement len
		//3. call heapify new index 0
		//return new len
		
		arr[0] = arr[length-1];
		length--;
		heapify(arr, length, 0);
		return length;
	}

	private static void print(int[] arr, int len) {
		System.out.print("array : ");
		for(int i = 0; i < len; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	private static void buildHeap(int[] arr, int len) {
		//call non leaf node to 0 call heapify
		int nn_node = (len / 2) - 1;//nn leaf = half - 1 or use half , both will give same ans, remove - 1 and check output
		for(int k = nn_node; k >= 0; k--) {
			heapify(arr, len, k);
		}
	}

	private static void heapify(int[] arr, int len, int index) {
		//store 3 index
		int largest = index;
		int left	= (index * 2) + 1;
		int right	= (index * 2) + 2;
		
		//find min
		if(left < len && arr[largest] < arr[left]) {
			largest = left;
		}
		if(right < len && arr[largest] < arr[right]) {
			largest = right;
		}
		//swap
		if(largest != index) {
			int temp = arr[largest];
			arr[largest] = arr[index];
			arr[index] = temp;
			
			heapify(arr, len, largest);
		}
	}
}
/**
array : 1 6 2 3 90 24 4 
array : 90 6 24 3 1 2 4 
after delete len : 6
array : 24 6 4 3 1 2 
after delete len : 5
array : 6 3 4 2 1 
after delete len : 4
array : 4 3 1 2 
array : 99 4 1 3 

*/