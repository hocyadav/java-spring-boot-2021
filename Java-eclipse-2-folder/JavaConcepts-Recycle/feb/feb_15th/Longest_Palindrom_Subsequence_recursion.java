package feb_15th;
/**
 * Compare only last char
 * @author Hariom Yadav | 15-Feb-2020
 *
 */
public class Longest_Palindrom_Subsequence_recursion {
	public static void main(String[] args) {
		String s = "agbdba";
		
		int t = fun_Recursion(s.toCharArray(), 0, s.length() - 1);//DONE
		System.out.println(t);
		
		int t2 = fun_Dynamic(s.toCharArray());//TODO
	}
	
	private static int fun_Dynamic(char[] charArray) {
		int[][] dp = new int[charArray.length][charArray.length];
		
		for(int i = 0; i < charArray.length; i++) {//filling diagonally
			dp[i][i] = 1;
		}
		
		for(int l = 2; l < charArray.length; l++) {
			
		}
		
		return 0;
	}


	private static int fun_Recursion(char[] arr, int startIndex, int endIndex) {
		if(arr == null || startIndex > endIndex) {//
			return 0;
		}
		if(startIndex == endIndex) {
			return 1;
		}
		
		if(arr[startIndex] == arr[endIndex]) {
			return 2 + fun_Recursion(arr, startIndex + 1, endIndex - 1);//last 2 char are same thats why adding 2
		} else 
			return Math.max(fun_Recursion(arr, startIndex, endIndex - 1), fun_Recursion(arr, startIndex + 1, endIndex));
	}
}
//5
