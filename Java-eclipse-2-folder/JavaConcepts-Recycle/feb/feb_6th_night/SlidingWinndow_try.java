package feb_6th_night;

public class SlidingWinndow_try {

	public static void main(String[] args) {
		int[] arr = {1,2,4,3,1,2,3,6};
		int sum = 6;
		int t = slidingWindow(arr, sum);
		System.out.println(t);
		
	}

	//1. 2 pointer i , j
	//
	private static int slidingWindow(int[] arr, int sum) {
		int i = 0, j = 0;
		int windowSum = Integer.MAX_VALUE;
		int windowLength = Integer.MAX_VALUE;
		while(j < arr.length) {
			System.out.println(i+" "+j+" "+windowLength+" "+ windowSum+" ");
			if(windowSum == sum) {
				windowLength = Math.min(windowLength, j - i + 1);
			}
			if(windowSum < sum) {
				j++;
				windowSum += arr[j];
				continue;
			}
			if(windowSum > sum) {
				windowSum -= arr[i];
				i++;
				continue;
			}
		}
		
		return windowSum;
	}

}
