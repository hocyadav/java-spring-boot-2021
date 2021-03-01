package feb_18th;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstring_allCharDiff_n_Kdiff_char_try {

	public static void main(String[] args) {
		String s = "bbbbbcccc";
		System.out.println(longestSubstringAllDiff(s));//output : bc
		System.out.println(longestSubstring2Diff("bbbbbcccc", 2));//output : bbbbbcccc
		
	}

	private static int longestSubstringAllDiff(String s) {
		int longest = 0;
		if(s == null || s.length() == 0) {
			return longest;
		}
		//sw + set
		int l = 0;
		Set<Character> set = new HashSet();
		
		for(int r = 0; r < s.length(); r++) {
			while(set.contains(s.charAt(r))) {
				set.remove(s.charAt(l));
				l++;
			}
			set.add(s.charAt(r));
			longest = Math.max(longest, r - l + 1);
		}
		
		return longest;
	}
	
	private static int longestSubstring2Diff(String s, int k) {
		int longest = 0;
		if(s == null || s.length() == 0 || k == 0) {
			return longest;
		}
		
		//sw + map
		int l = 0;
		Map<Character, Integer> map = new HashMap();
		for(int r = 0; r < s.length(); r++) {
			
			int count = map.getOrDefault(map.get(s.charAt(r)), 0);
			map.put(s.charAt(r), count + 1);
			
			if(map.size() > 2) {
				while(l < s.length() && map.size() > 2) {
					int c = map.get(s.charAt(l));
					map.put(s.charAt(l), c - 1);//update map with - 1 value
					
					if(map.get(s.charAt(l)) == 0) {
						map.remove(s.charAt(l));
					}
					l++;
				}
			}
			
			longest = Math.max(longest, r - l + 1);
			
		}
		return longest;
	}

}
