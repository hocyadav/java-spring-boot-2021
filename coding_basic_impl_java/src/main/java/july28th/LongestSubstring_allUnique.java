package july28th;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring_allUnique {
	public static void main(String[] args) {
		longestSub("abcadef");//6 length substring bcadef
	}

	private static void longestSub(String str) {
		//traverse
		//add in set -> before that remove all till unique -> calculate longest length
		int result = Integer.MIN_VALUE;
		Set<Character> set = new HashSet()
;		int l = 0;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			while(set.contains(c)) {
				set.remove(str.charAt(l));
				l++;
			}
			
			set.add(c);
			result = Math.max(result, i - l + 1);
		}
		System.out.println(result);
	}
}
