package feb_12th;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author Hariom Yadav | 12-Feb-2020
 *
 */
public class LongestSubstring_with_uniqueChar {
	public static void main(String[] args) {
		String s = "pwwkew";
		System.out.println(longestSubstring(s));
		
		String s2 = "abcabcccc";
		System.out.println(longestSubstring(s2));
	}

	private static int longestSubstring(String s) {
		int longest = 0;
	
		if(s == null || s.length() == 0) {	//error check
			return longest;
		}
		
		int l = 0;//2 pointer question
		Set<Character> set = new HashSet<>();
		
		for(int r = 0; r < s.length(); r++) {
			while(set.contains(s.charAt(r))) {
				set.remove(s.charAt(l));
				l++;
			}
			set.add(s.charAt(r));
			longest = Math.max(longest, r - l + 1);
		}
		
		System.out.println(set);
		return longest;
	}
}
/**
[a, b, c]
3
[c]
3

*/