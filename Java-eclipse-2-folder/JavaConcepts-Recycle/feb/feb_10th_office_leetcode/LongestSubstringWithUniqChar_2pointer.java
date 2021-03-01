package feb_10th_office_leetcode;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author Hariom Yadav | 10-Feb-2020
 *[LeetCode] 3. Longest Substring without Repeating Characters
 *
 *Given a string, find the length of the longest substring without repeating characters.
Examples:
Given "abcabcbb", the answer is "abc", which the length is 3.
Given "bbbbb", the answer is "b", with the length of 1.
Given "pwwkew", the answer is "wke", with the length of 3.
 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

Thought process:
Two pointers + sliding window:

Pointer i points to the left end of current window.
Pointer j points to the right end of current window.
Use a hash set to keep track of the characters in current window. 
Iterate through the string using j. If s[j] is a repeating character, 
increment i and remove s[i] from set until no repeating character is there.
 */
public class LongestSubstringWithUniqChar_2pointer {

	public static void main(String[] args) {
		String s = "abcabcbbdefghi";
		System.out.println(fun(s));
		System.out.println(fun2(s));
	}

	private static int fun(String s) {
		int longest = 0;
		//error check
		if(s == null || s.length() == 0) {
			return longest;
		}
		//traverse + strore in set
		int l = 0;
		Set<Character> set = new HashSet<>();
		for(int r = 0; r < s.length(); r++) {
			//check 
			while(set.contains(s.charAt(r))) {
				set.remove(s.charAt(l)); 
				l++;
			}
			
			//add + largest length : before that check
			set.add(s.charAt(r));
			longest = Math.max(longest, r - l + 1);
		}
		System.out.println(set);
		//return set.size();
		return longest;
		
	}
	
	private static int fun2(String s) {
		int longest = 0;
		//error check
		if(s == null || s.length() == 0) {
			return longest;
		}
		//traverse + strore in set
		int l = 0;
		Set<Character> set = new HashSet<>();
		for(int r = 0; r < s.length(); r++) {
			//check 
			if(set.contains(s.charAt(r))) {
				for(int k = l; k < r; k++) {
					set.remove(s.charAt(k));
					l++;
				}
			}
			
			//add + largest length : before that check
			set.add(s.charAt(r));
			longest = Math.max(longest, r - l + 1);
		}
		System.out.println(set);
		//return set.size();
		return longest;
	}

}

/**


[b, d, e, f, g, h, i]
7

*/