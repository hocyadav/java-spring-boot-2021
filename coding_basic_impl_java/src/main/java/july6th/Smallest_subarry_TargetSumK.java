package july6th;
//length : smallest + largest subarray
//start and index of : subarray that have sum == target
public class Smallest_subarry_TargetSumK {
	public static void main(String[] args) {
		int[] arr = {10, 5, 2, 7, 1, 9 };
		int target = 15;
		System.out.println("smallest sub array LENGTH "+smallestSubset(arr, target));//only for +ve
		System.out.println("smallest sub array LENGTH "+smallestSubset2(arr, target));//only for +ve
		System.out.println("largest sub array LENGTH "+largetSubset(arr, target));//only for +ve
		
		int[] arr2 = {2,3,1,2,4,3};
		int target2 = 7;
		System.out.println("smallest sub array LENGTH "+smallestSubset(arr2, target2));//only for +ve
		System.out.println("largest sub array LENGTH "+largetSubset(arr2, target2));//only for +ve
	}

	//traverse -> local sum -> if locasum >= target then update result : note : change if to while
	private static int smallestSubset(int[] arr, int target) {
		int i = 0;
		int j = 0;
		int result = Integer.MAX_VALUE;
		int localSum = 0;
		while(j < arr.length) {
			localSum += arr[j];
			j++;
			while(localSum >= target) {//update result -> //NOTE : last change if to while
				result = Math.min(result, j - i);
				localSum -= arr[i];
				i++;
			}
		}
		return result == Integer.MAX_VALUE ? 0 : result;
	}
	
	private static int smallestSubset2(int[] arr, int target) {
		int i = 0;
		int j = 0;
		int result = Integer.MAX_VALUE;
		int localSum = 0;
		while(j < arr.length) {
			localSum += arr[j];
			j++;
			while(localSum >= target) {//update result -> //NOTE : last change if to while
				//System.out.println(i+" "+j);
				result = Math.min(result, j - i);
				localSum -= arr[i];
				i++;
			}
		}
		return result == Integer.MAX_VALUE ? 0 : result;
	}
	
	
	private static int largetSubset(int[] arr, int target) {
		int i = 0;
		int j = 0;
		int result = Integer.MIN_VALUE;
		int localSum = 0;
		
		while(j < arr.length) {
			localSum += arr[j];//get local sum
			j++;//incremtn pointer
			while(localSum >= target) {
				if(localSum == target) {
					System.out.println(i+" "+j);//print index or store to get the subarry start and end point
					result = Math.max(result, j - i);
				}
				localSum -= arr[i];
				i++;
			}
		}
		return result == Integer.MIN_VALUE ? 0 : result;
		
		
	}
}
