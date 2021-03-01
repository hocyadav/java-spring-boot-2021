package feb_17th;
/**
 * 
 * @author Hariom Yadav | 17-Feb-2020
 *
 */
public class LargestPalindrom_Subsequence_recursion {
	public static void main(String[] args) {
		String s = "agbdba";
		int t = largestPalindromSubRecursion(s.toCharArray(), 0, s.length() - 1);
		System.out.println(t);
	}

	private static int largestPalindromSubRecursion(char[] arr, int startIndex, int endIndex) {
		if(arr == null || startIndex > endIndex) return 0;
		if(startIndex == endIndex) return 1;
		
		char startValue = arr[startIndex];
		char endValue 	= arr[endIndex];
		
		if(startValue == endValue) {
			return 2 + 
					largestPalindromSubRecursion(arr, startIndex + 1, endIndex - 1);
		}else {
			return Math.max(
					largestPalindromSubRecursion(arr, startIndex, endIndex - 1), 
					largestPalindromSubRecursion(arr, startIndex + 1, endIndex));
		}
	}
}
//5