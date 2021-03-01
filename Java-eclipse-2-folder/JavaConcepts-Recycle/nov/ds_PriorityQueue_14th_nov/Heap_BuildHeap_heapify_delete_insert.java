package ds_PriorityQueue_14th_nov;
/**
 * 
 * @author Hariom Yadav - Nov 14, 2019
 *
 */

//ds - array

public class Heap_BuildHeap_heapify_delete_insert {
	final static int MAX = 1000;
	public static void main(String[] args) {
		int[] arr 	= new int[6];
		
		arr[0]	= 12;
		arr[1] 	= 13;
		arr[2] 	= 1;
		arr[3] 	= 4;
		arr[4] 	= 8;
		arr[5] 	= 5;
		
		int len = arr.length;
		System.out.println("array length : "+len);
		//build heap
		printArry(arr, len);
		buildHeap(arr, len);
		
		printArry(arr, len);
		
		
		int d = deleteFromRoot(arr,len);
		System.out.println("after delete root element - len of array : "+d);
		//now print with new len and check the new heap 
		printArry(arr, len-1);
		
		int key = 100;
		//insertAtLastNode(arr, len, key);//send old len, since after deletion one indx present where we can add
		insertAtLastNode_M2_simple(arr, len, key);
		System.out.println("after  insert new value ");
		printArry(arr, len);
		
		
	}
		
	/**
	 * 
	 * @param arr
	 * @param len
	 * @param key
	 * insert code is same like build heap
	 */
	private static void insertAtLastNode_M2_simple(int[] arr, int len, int key) {
		//insert at last node
		arr[len-1]	= key;
		
		//find non leaf node and call heapify - like isert coed 
		int nn = len/2 -1;
		for(int i=nn ;i>= 0; i--) {
			heapify(arr, len, i);
		}
		
	}

	private static int deleteFromRoot(int[] arr, int len) {
		//delete 1st elelment == swap last elelemt with root
		arr[0] = arr[len-1];
		
		//decrease len
		--len;
		
		//call hepify - root 
		heapify(arr, len, 0);
		return len;
	}

	private static void printArry(int[] arr, int len) {
		System.out.print("Level order : ");
		for(int i=0; i< len; i++)
			System.out.print(arr[i]+" ");
		
		System.out.println();
	}

	private static void buildHeap(int[] arr, int len) {//order -: pri, pro, defa, public
		//find non leaf node
		int nn = len/2 - 1;
		
		for(int i = nn; i >= 0 ; i--)
			heapify(arr, len, i);
	}
	
	private static void heapify(int[] arr, int len, int i) {
		//find all index
		int largest = i;
		int left 	= i*2 + 1;
		int right 	= i*2 + 2;
		
		//find largest value index
		if(left < len && arr[left] > arr[largest])
			largest = left;
		if(right < len && arr[right] > arr[largest])
			largest = right;
		
		//if input root index and largest not same then swap and call heapify
		if(i != largest ) {
			//swap
			int t 			= arr[largest];
			arr[largest] 	= arr[i];
			arr[i]			= t;
			//heapify - largest index
			heapify(arr, len, largest);
		}
	}
	
	//TODO : similarly heapifyUpward_MinHeap_Call_During_Insertion
	
}
