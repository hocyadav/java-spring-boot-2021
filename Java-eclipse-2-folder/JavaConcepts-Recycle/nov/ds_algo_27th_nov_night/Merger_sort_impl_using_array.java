package ds_algo_27th_nov_night;
/**
 * 
 * @author Hariom Yadav | 27-Nov-2019
 *
 */
public class Merger_sort_impl_using_array {
	public static void main(String[] args) {
		int[] arr = {12,4,1,45,6,3,0,100,11};
		
		System.out.print("INPUT : ");
		for(int i: arr)
			System.out.print(i+" ");
		System.out.println();
		
		int start = 0;
		int end = arr.length - 1;
		mergeSort(arr,start, end);
		
		System.out.print("OUTPUT: ");
		for(int i: arr)
			System.out.print(i+" ");
		
	}

	private static void mergeSort(int[] arr, int start, int end) {
		//divide - mid 
		//call both haf recursion mergeSort
		if(start < end) {
			int mid = (start + end) / 2;
			
			mergeSort(arr, start, mid);//merging 1st half
			mergeSort(arr, mid+1, end);//merging 2nd half
			
			merge(arr, start, mid, end);
		}
	}

	private static void merge(int[] arr, int start, int mid, int end) {
		//find length
		int l_len = (mid - start) + 1;//end - start + 1
		int r_len = (end - mid);//end - start + 1
		
		//declare arry
		int[] l_arr = new int[l_len];
		int[] r_arr = new int[r_len];
		
		//copy data into array
		for(int i = 0; i<l_len; i++)
			l_arr[i] = arr[start + i];// jumping index concept, intial is start and then we are jumping by i value
		
		for(int j = 0; j<r_len; j++)
			r_arr[j] = arr[mid+1 + j];//jump by step : start + step ==> start is mid+1 and step is j
		
		int i=0, j=0, k=start;
		
		while(i<l_len && j<r_len) {
			if(l_arr[i] < r_arr[j]) {
//				arr[k] = l_arr[i];
//				k++; i++;
				arr[k++] = l_arr[i++];//above 2 line is same as this single line
			}else {
				arr[k] = r_arr[j];
				k++; j++;
				//arr[k++] = r_arr[j++]; // same as above 2 line
			}
		}
		
		//copy remaining
		while(i < l_len) {
			arr[k] = l_arr[i];
			k++; i++;
		}
		
		while(j < r_len) {
			arr[k] = r_arr[j];
			k++; j++;
		}
		
	}
}
/**
INPUT : 12 4 1 45 6 3 0 100 11 
OUTPUT: 0 1 3 4 6 11 12 45 100 
**/
