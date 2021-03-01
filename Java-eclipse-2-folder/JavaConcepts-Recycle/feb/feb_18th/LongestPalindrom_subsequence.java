package feb_18th;

public class LongestPalindrom_subsequence {
	public static void main(String[] args) {
		System.out.println(longestPalindrome("abaab"));//baab
	}

	private static int longestPalindrome(String string) {
		return rec_palindrom(string.toCharArray(), 0, string.length() - 1);
	}

	private static int rec_palindrom(char[] charArray, int i, int j) {
		//base case
		if(charArray == null || charArray.length == 0 || i > j) {
			return 0;
		}
		if(i == j) {
			return 1;
		}
		
		//main recursion
		if(charArray[i] == charArray[j]) {
			return 2 + rec_palindrom(charArray, i + 1, j - 1);
		} else {
			return Math.max(
					
					rec_palindrom(charArray, i, j - 1), 
					rec_palindrom(charArray, i + 1, j) 
					
					);
		}
	}
}
