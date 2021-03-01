package feb_25th;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author Hariom Yadav | 25-Feb-2020
 *
 */
public class LargestSubstringAllUnique_kUnique_Char {
	public static void main(String[] args) {
		String s = "abcab";
		largestSubstringAllUnique(s);
		int k = 2;
		largestSubstringKUnique("eceba", k);
	}

	private static void largestSubstringKUnique(String s, int k) {
		if(s == null || s.length() == 0 || k == 0) {
			System.out.println("0");
			return;
		}
		
		//sw + map
		Map<Character, Integer> map = new HashMap();
		int l = 0;
		int longest = 0;
		
		for(int r = 0; r < s.length(); r++) {
			int count = map.getOrDefault(s.charAt(r) , 0);
			map.put(s.charAt(r), count  + 1);
			
			while(map.size() > k) {
				//int count1 = map.get(s.charAt(l));
				map.put(s.charAt(l), map.get(s.charAt(l)) - 1);
				if(map.get(s.charAt(l)) == 0) {
					map.remove(s.charAt(l));
				}
				l++;
			}
			longest = Math.max(longest, r - l + 1);
			
		}
		System.out.println("largestSubstringKUnique "+longest);
		
	}

	private static void largestSubstringAllUnique(String s) {
		if(s == null || s.length() == 0) { //empty check
			System.out.println("0");
			return;
		}
		
		//sw + set
		Set<Character> set = new HashSet();
		int longest = 0;
		int l = 0;
		for(int r = 0; r < s.length(); r++) {
			while(set.contains(s.charAt(r))) {
				set.remove(s.charAt(l));
				l++;
			}
			set.add(s.charAt(r));
			longest = Math.max(longest, r - l + 1);
		}
		
		System.out.println("largestSubstringAllUnique "+longest);
		
	}
}
/**
largestSubstringAllUnique 3
largestSubstringKUnique 3
 */
