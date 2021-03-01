package feb_15th;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Hariom Yadav | 15-Feb-2020
 *
 */
public class Longest_Substring_K_UniqueChar__slidingW_map {
	public static void main(String[] args) {
		String s = "eceba";
		longestSubstringKUnique(s);
	}
	/**
	 * sliding window + map
	 * hash map of character => integer to keep track of the count of each character in the current window.
	 * like in set : it keep track of char in current window
	 * @param s
	 */
	private static void longestSubstringKUnique(String s) {
		if(s == null || s.length() == 0) {
			System.out.println("");
			return;
		}
		//sliding window : 2 pointers (one pointer is for traversal)
		int l = 0;
		Map<Character, Integer> map = new HashMap<>();
		int longest = 0;
		
		for(int r = 0; r < s.length(); r++) {
			int count = map.getOrDefault(s.charAt(r), 0);//get old value or get 0 as default
			map.put(s.charAt(r), count + 1);
			
			while(map.size() > 2) {//map.size() == gives total distinct key, our requirement is 2 distinct
				map.put(s.charAt(l), map.get(s.charAt(l)) - 1);//update count
				if(map.get(s.charAt(l)) == 0) {//check count for 0
					map.remove(s.charAt(l));
				}
				l++;
			}
			
			int currentWindowSize = r - l + 1;
			longest = Math.max(longest, currentWindowSize);
		}
		//System.err.println("redcolor");
		System.out.println(longest);
		
		
		
	}
}
//3
