package feb_17th;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author Hariom Yadav | 17-Feb-2020
 *
 */
public class Substring_with_All_unique_char_try {
	public static void main(String[] args) {
		String s = "abcabcdfr";
		substringAlluniqueChar("abcabcdfr");
	}

	private static void substringAlluniqueChar(String s) {
		if(s == null || s.length() == 0) {
			System.out.println("");
			return;
		}
		//sliding window(2 pointer) + set
		int l = 0;
		Set<Character> set = new HashSet();
		int longest = 0;
		for(int r = 0; r < s.length(); r++) {
			if(set.contains(s.charAt(r))) {//if char present in set : that means not all are uniqe, remove all char from current window
				while(set.contains(s.charAt(r))) {
					set.remove(s.charAt(l));
					l++;
				}
			}
			set.add(s.charAt(r));
			longest = Math.max(longest, r - l + 1);
		}
		System.out.println(longest);
		
	}
}
//6
