package ds_9th_Nov_Night;
/**
 * 
 * @author Hariom Yadav - Nov 9, 2019
 *
 */
//data structure used is array
public class Heap_BuildHeap_heapifyMin_Max_Delete {
	public static void main(String[] args) {
		int[] arr = {12,1,3,34,9,5};
		int len = arr.length;
		//build heap
		for(int i:arr)
			System.out.print(i+" ");
		System.out.println();
		
		buildHeap(arr, len);
		
		for(int i:arr)//not sorted : only root is greater
			System.out.print(i+" ");
		System.out.println();
		
		int deleteValue = 12;//we cant delete any value from heap : only root is deleted i.e. min or max
		System.out.println("old len before delete :"+ len);
		
		len = deleteRootNode(arr, len);//or call as delete 1st index node from array
		System.out.println("new len after delete  :"+ len);
		
		System.out.print("Top element deleted : ");
		for(int i=0; i<len; i++)
			System.out.print(arr[i]+" ");
		
		//building min heap : internally calls min heapify
		buildHeapMin(arr, len);
		System.out.println();
		for(int i=0; i<len; i++)
			System.out.print(arr[i]+" ");
		
	}
	
	/**
	 * Delete 1st index from array (heap array) and then internally call heapify to make heap array
	 * //we cant delete any value from heap : only root is deleted i.e. min or max
	 * @param arr 
	 * @param len 
	 * @return
	 */
	private static int deleteRootNode(int[] arr,int len) {

		//1 swap with last index value
		arr[0] = arr[len - 1];//len - 1 will give 1st index
		
		//2 decrease length
		--len;
		
		//call heapify
		heapify(arr, len,0);// len is new len that is less than input , 0 is index that value is changed
		
		return len;
	}
	/**
	 * Build heap from given array
	 * @param arr
	 * @param len
	 */
	private static void buildHeap(int[] arr, int len) {
		//find non leaf node
		int nn = (len/2) -1;
		for(int i=nn; i>=0; i--) {
			heapify(arr, len, i);
		}
	}
	
	private static void buildHeapMin(int[] arr, int len) {
		//find non leaf node
		int nn = (len/2) -1;
		for(int i=nn; i>=0; i--) {
			heapifyMin(arr, len, i);
		}
	}
	
	/**
	 * make root as max
	 * @param arr
	 * @param len
	 * @param index
	 */
	private static void heapify(int[] arr, int len, int index) {
		//find all index
		int largest = index;
		int left = index*2 + 1;
		int right = index*2 +2;
		//parent node : (i-1)/2
		
		//find largest value index
		if(left < len && arr[left] > arr[largest])//largest update
			largest = left;
		if(right < len && arr[right] > arr[largest])//largest updated 
			largest = right;
		
		//if largest not same as index root then swap + call hepify
		if(largest != index) {
			//swap
			int t = arr[index];
			arr[index] = arr[largest];
			arr[largest] = t;
			
			//call heapify
			heapify(arr, len, largest);
		}
	}
	
	private static void heapifyMin(int[] arr, int len, int index) {
		//find all index
		int largest = index;
		int left = index*2 + 1;
		int right = index*2 +2;
		
		//find largest value index
		if(left < len && arr[left] < arr[largest])//largest update
			largest = left;
		if(right < len && arr[right] < arr[largest])//largest updated 
			largest = right;
		
		//if largest not same as index root then swap + call hepify
		if(largest != index) {
			//swap
			int t = arr[index];
			arr[index] = arr[largest];
			arr[largest] = t;
			
			//call heapify
			heapify(arr, len, largest);
		}
	}

}
