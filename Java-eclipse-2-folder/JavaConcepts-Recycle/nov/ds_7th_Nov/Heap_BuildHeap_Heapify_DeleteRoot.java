package ds_7th_Nov;
/**
 * 
 * @author Hariom Yadav - Nov 7, 2019
 *
 */
//ds - arry, methods
public class Heap_BuildHeap_Heapify_DeleteRoot {
	static int len;//use when we delete and update len
	
	public static void main(String[] args) {
		int[] arr = {100,50,200,10,60,120};
		len = arr.length;
		BuildHeap(arr, len);//max heaps
		System.out.print("level order: ");
		for(int i = 0; i<len;i++) {//level order
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		
		deleteFromRoot(arr,len);//deletion only possible from root
		System.out.print("level order: ");
		for(int i = 0; i<len;i++) {//level order
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		
		deleteFromRoot(arr,len);//deletion only possible from root
		System.out.print("level order: ");
		for(int i = 0; i<len;i++) {//level order
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		
		deleteFromRoot(arr,len);//deletion only possible from root
		System.out.print("level order: ");
		for(int i = 0; i<len;i++) {//level order
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		
		
		
	}
	
	static void deleteFromRoot(int[] arr, int len) {//3 line code -> 2line
		//swap root - last node
		arr[0] = arr[len-1];
		//length of arr -1
		Heap_BuildHeap_Heapify_DeleteRoot.len--;
		//call heapify for root with new len value
		heapify(arr, Heap_BuildHeap_Heapify_DeleteRoot.len, 0);
		
		
		//2 line code
		//arr[0] = arr[len-1];
		//heapify(arr, --Heap_BuildHeap_Heapify_DeleteRoot.len, 0);
		
	}
	
	
	static void BuildHeap(int[]arr, int len) {
		//find non leaf node : half-1 : half = n/2
		int nn = len/2 -1;
		
		for(int i=nn; i>=0; i--) {
			heapify(arr, len, i);
		}
	}
	
	static void heapify(int[] arr, int len, int index) {
		//1. find all index
		int largest = index;
		int left = index*2 + 1;
		int right = index*2 + 2;
		
		//2. find index of largest value - by checking 1st left
		if(left < len && arr[left] > arr[largest])
			largest = left;
		if(right < len && arr[right] > arr[largest])//largest updated above not same as index
			largest = right;
		
		
		//3. if largest not eq to input index i.r. root index then swap + call heapify
		if(largest != index) {
			int t = arr[largest];
			arr[largest] = arr[index];
			arr[index] = t;
			
			heapify(arr, len, largest);
		}
	}
}
