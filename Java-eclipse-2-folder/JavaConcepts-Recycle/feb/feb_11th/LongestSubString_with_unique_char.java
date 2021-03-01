package feb_11th;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author Hariom Yadav | 11-Feb-2020
 *
 */
public class LongestSubString_with_unique_char {

	public static void main(String[] args) {
		String s = "abcabccccc";
		System.out.println(longestSubstaring(s));
		System.out.println(longestSubstaring2(s));
	}

	private static int longestSubstaring2(String s) {
		int longest = 0;
		//empty check 
		if(s == null || s.length() == 0) {
			return longest;
		}
		//2 pointer
		int l = 0;
		Set<Character> set = new HashSet<>();
		for(int r = 0; r < s.length(); r++) {
			if(set.contains(s.charAt(r))) {//if duplicate found then clear set : i.e. we are deleting all visited char from set, same as for loop
				set.clear();
			}
			set.add(s.charAt(r));
			longest = Math.max(longest, set.size());//not true , use 2 pointer approach
		}
		System.out.println("my set:"+ set);
		return longest ;
	}

	private static int longestSubstaring(String s) {
		int longest = 0;
		if(s == null || s.length() == 0) {
			return longest;
		}
		
		//2 pointer
		int l = 0;
		Set<Character> set = new HashSet<>();
		
		for(int r = 0;r < s.length(); r++) {
			while(set.contains(s.charAt(r))) {
				set.remove(s.charAt(l));
				l++;
			}
			set.add(s.charAt(r));
			longest = Math.max(longest, r - l + 1);
		}
		
		System.out.println("Set : "+set);
		return longest;//or we can return set size
	}

}
/**
Set : [a, b, c, d]
4
my set:[a, b, c, d]
4 
 */
