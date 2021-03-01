package ds_6th_Nov;
/**
 * 
 * @author Hariom Yadav - Nov 6, 2019
 *
 */
public class Heap_impl_delete_from_MaxHeap {
	private static void buildHeap(int[] arr, int len) {
		//find non leaf node  : half - 1
		int nn = len/2 - 1;
		for(int i=nn ; i>=0; i--) {
			heapify(arr, len, i);
		}
		
	}
	private static void heapify(int[] arr, int len, int index) {
		//1. find all 3 index
		int larg = index;
		int left = 2*index +1;
		int right = 2*index +2;
		
		//2. find largest value index
		if(left < len && arr[left] > arr[larg]) {
			larg = left;
		}if(right < len && arr[right] > arr[larg]) {
			larg = right;
		}
		
		//3. if input root index is not same as largest updated index - then swap && call heapify
		if(larg != index) {
			int temp = arr[larg];
			arr[larg] = arr[index];
			arr[index] = temp;
			
			heapify(arr, len, larg);
			
		}
		
	}
	
	//avbove method from Build heap implemetation
	
	public static void main(String[] args) {
		int[] arr = { 20, 300, 40, 100 };
		int len = arr.length;
		
		for(int i=0;i<arr.length; i++)
			System.out.print(arr[i]+" ");
		System.out.println();
		buildHeap(arr, len);
		
		for(int i=0;i<arr.length; i++)
			System.out.print(arr[i]+" ");
		System.out.println();
		
		
		// 1. build heap - max heap from heap_impl code
		// 2. heapify code - from --""---
		// 3. delete code
		
		int t = deleteHeap(arr, len);//root index
		for(int i=0;i<t; i++)
			System.out.print(arr[i]+" ");
		System.out.println();
		int t2 = deleteHeap(arr, t);//root index - snend new length
		for(int i=0;i<t2; i++)
			System.out.print(arr[i]+" ");

	}
	
	
	private static int deleteHeap(int[] arr, int len) {
		//deleting root -> swap from last element
		arr[0] = arr[len-1];
		//dec heap size
		len = len-1;
		
		heapify(arr, len, 0);//callinf root index heapaify -: ths method will swap and call heapify for all change index
		return len;
	}
	
	
	
	
}
