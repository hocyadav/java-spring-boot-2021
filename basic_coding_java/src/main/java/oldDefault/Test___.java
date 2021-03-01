package oldDefault;

/** impatus 2nd round **/
public class Test___ {
	public static void main(String[] args) {
		int[] arr1 = {1,2,3,4,5,10,20,50,100};
		int[] arr2 = {13, 40, 57, 80, 90};

		for(int i : arr1) 
			System.out.print(i+" ");
		System.out.println();

		for(int i : arr2) 
			System.out.print(i+" ");
		System.out.println();

		int[] temp = sort2sortedArray(arr1, arr2);

		for(int i : temp) 
			System.out.print(i+" ");

	}

	public static int[] sort2sortedArray(int[] arr1, int[] arr2){//TC : O(n), SC: O(n)
		int[] temp = new int[arr1.length + arr2.length];

		int arr1Pointer = 0;
		int arr2Pointer = 0;
		int tempPointer = 0;

		while(arr1Pointer < arr1.length && arr2Pointer < arr2.length) {
			if(arr1[arr1Pointer] < arr2[arr2Pointer])
				temp[tempPointer++] = arr1[arr1Pointer++];
			else
				temp[tempPointer++] = arr2[arr2Pointer++];
		}
		//temp = 1,2,3,4,5
		while(arr1Pointer < arr1.length){
			temp[tempPointer++] = arr1[arr1Pointer++];
		}

		while(arr2Pointer < arr2.length){
			temp[tempPointer++] = arr2[arr2Pointer++];
		}
		return temp;//1,2,3,4,5,6,7,8
	}
}
