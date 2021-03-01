package ds_8th_Nov;
/**
 * 
 * @author Hariom Yadav - Nov 8, 2019
 *
 */
//ds - arry


public class Heap_min {
	public static void main(String[] args) {
		int arr[] = { 100, 3, 5, 6,0,12, 1 };
		int len = arr.length;
		buildHeap(arr, len);
		for(int i=0; i< len; i++) {
			System.out.print(arr[i] + " ");
		}
		
	}
	

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
		if(left < len && arr[left] < arr[larg]) {
			larg = left;
		}if(right < len && arr[right] < arr[larg]) {
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
}

