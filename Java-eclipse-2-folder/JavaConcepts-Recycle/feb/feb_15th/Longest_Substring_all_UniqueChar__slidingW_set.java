package feb_15th;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author Hariom Yadav | 15-Feb-2020
 *
 */
public class Longest_Substring_all_UniqueChar__slidingW_set {
	public static void main(String[] args) {
		String s = "abcabcbb";
		longestSubstringAllUnique(s);
		longestSubstringAllUniqueTRY(s);
	}

	/**
	 * Logic : sliding window + set
	 * sliding window --> uses 2 pointer
	 * set --> coz we want unique char i.e. is count reaches more than 1 for that we are using set
	 * map : also we can use , but we want [ count > 1 ~same as~ set(contains(input char)) ] 
	 * @param s
	 */
	private static void longestSubstringAllUnique(String s) {
		if(s == null || s.length() == 0) {
			System.out.println("");
			return;
		}
		//sliding window : 2 pointers (one is use for traverse)
		int l = 0;
		Set<Character> set = new HashSet<>();//keep track of current window char
		int longest = 0;
		
		for(int r = 0; r < s.length(); r++) {
//			if(set.contains(s.charAt(r))) {
//				while(set.contains(s.charAt(r))) {
//					set.remove(s.charAt(l));
//					l++;
//				}
//			}
			
			while(set.contains(s.charAt(r))) {//set == itself give distinct key: our requirement is all distinct 
				set.remove(s.charAt(l));
				l++;
			}
			
			set.add(s.charAt(r));
			int currentWindowSize = r - l + 1;
			longest = Math.max(longest, currentWindowSize);
		}
		System.out.println(longest);
	}
	
	private static void longestSubstringAllUniqueTRY(String s) {
		if(s == null || s.length() == 0) {
			System.out.println("");
			return;
		}
		//sliding window : 2 pointers (one is use for traverse)
		int l = 0;
		Set<Character> set = new HashSet<>();//keep track of current window char
		int longest = 0;
		
		for(int r = 0; r < s.length(); r++) {
			if(set.contains(s.charAt(r))) {//during traversal if value already present in set : remove this : 
				//but we dont know where it is present so traverse again with while condition : set.contains(last char i.e. traversal of r)
				while(set.contains(s.charAt(r))) {//set == itself give distinct key: our requirement is all distinct 
					set.remove(s.charAt(l));
					l++;
				}
			}

			set.add(s.charAt(r));
			
			int currentWindowSize = r - l + 1;
			longest = Math.max(longest, currentWindowSize);
		}
		System.out.println(longest);
	}
}
/**
3
3

*/