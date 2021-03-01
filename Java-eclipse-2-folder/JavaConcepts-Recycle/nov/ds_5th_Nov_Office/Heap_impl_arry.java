package ds_5th_Nov_Office;
/**
 * 
 * @author Hariom Yadav - Nov 5, 2019
 *
 */
public class Heap_impl_arry
{
	public static void main (String[] args)
	{
		int[] ary = {20,1,2,5,4,30,100,8};
		int len = ary.length;
		for(int i=0;i<len; i++)
			System.out.print(ary[i]+" ");
		System.out.println(len);
		buildHeap(ary, len);
		for(int i=0;i<len; i++)
			System.out.print(ary[i]+" ");
	
	}
	static void buildHeap(int[] ary, int len){
		//find non leaf node : half - 1
		int nn_index = (len/2);
		//traverse till 0th index
		for(int i = nn_index; i>=0; i--){
			heapify(ary, len, i);
		}
		
	}
	
	static void heapify(int[] ary, int len, int index){
		//1.find all left and right index -- 2*i+1, 2*i+2
		int largest = index;
		int left = 2*index + 1;
		int right = 2*index + 2;
		
		
		//2.find max value index 
		if(left < len && ary[left] > ary[largest]){//IMP Note: left side of && if we right side then we wll get errors
			largest = left;
		}
		if(right < len && ary[right] > ary[largest]){//Note : left side condition come 1st else sometime we will get error index out of range 
			largest = right;
		}
		
		//3. if max index value is different from root_index then max value is children 
		if(largest != index){//swap b/w latest max index and root index
			int temp = ary[index];
			ary[index] = ary[largest];
			ary[largest] = temp;
			
			heapify(ary, len, largest);
		}
	}
}