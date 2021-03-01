package ds_5th_Nov_Office;
/**
 * 
 * @author Hariom Yadav - Nov 5, 2019
 *
 */
public class Heap_impl {
	public static void main(String[] args) {
		int[] ary = {2,1,11,23,4,111,2323,99,8};
		int len = ary.length;
		for(int i=0;i<len; i++)
			System.out.print(ary[i]+" ");
		System.out.println(len);
		heapFun(ary, len);
		for(int i=0;i<len; i++)
			System.out.print(ary[i]+" ");
	}

	private static void heapFun(int[] ary, int len) {

		//find non leaf node : half - 1
				int nn_index = (len/2) - 1;
				//traverse till 0th index
				for(int i = nn_index; i>=0; i--){
					heapify(ary, len, i);
				}
	}

	private static void heapify(int[] ary, int len, int root_index) {

		//1.find all left and right index -- 2*i+1, 2*i+2
		int max_index = root_index;
		int left = 2*root_index + 1;
		int right = 2*root_index + 2;
		
		
		//2.find max value index 
		if(left < len && ary[left] > ary[max_index]){
			max_index = left;
		}
		if(right < len && ary[right] > ary[max_index]){
			max_index = right;
		}
		
		//3. if max index value is different from root_index then max value is children 
		if(max_index != root_index){//swap b/w latest max index and root index
			int temp = ary[root_index];
			ary[root_index] = ary[max_index];
			ary[max_index] = temp;
			heapify(ary, len, max_index);
		}
	
	}
}
