package july28th;

public class Rotated {
	
	public static void main(String[] args) {
		int arr1[] = {5,6,7,8,9,1,2,3,4}; 
	
//		int bs = bs(arr1, 5, arr1.length - 1, 0);
//		System.out.println(bs);
		
		int search = search(arr1, 2);
		System.out.println(search);
		
	}
	public static int search(int[] nums, int target) {
		int index = -1;
		for(int i = 0; i < nums.length - 1; i++) {
			if(nums[i] < nums[i+1]) {
				continue;
			} else {
				index = i + 1;
				break;
			}
		}
		System.out.println("index "+index);

		if(index == -1) 
			return bs(nums, 0, nums.length - 1, target);
		if(nums[index] == target) 
			return index;

		if(nums[0] <= target) {//7 <= 1
			System.out.println("if");
				return bs(nums, 0, index - 1, target);
		} else {
			System.out.println("else");
			return bs(nums, index + 1, nums.length - 1, target);
		}
	}

	public static int bs(int[] arr, int start, int end, int key) {
		if(start > end) return -1;

		int mid = (start + end)/2;//start + jump
		if(arr[mid] == key) 
			return mid;

		//recursion
		if(key <= arr[mid] ) 
			return bs(arr, start, mid -1, key);
		else 
			return bs(arr, mid + 1, end, key);
	}
}
