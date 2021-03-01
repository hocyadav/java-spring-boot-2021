package july17th;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring_with_Only_k_char {
	public static void main(String[] args) {
		String inp = "aaabbcc";
		int subStrlen = longestSubstringKChar(inp);
		System.out.println(subStrlen);
		
	}

	private static int longestSubstringKChar(String inp) {
		int result = 0;
		
		/* key-value : char & count */
		Map<Character, Integer> map = new HashMap();
		
		int l = 0;
		
		for(int r = 0; r < inp.length(); r++) {
			char c = inp.charAt(r);
			//working
//			int count = map.getOrDefault(c, 0);
//			map.put(c, count + 1);
			//working
			map.putIfAbsent(c,  0);
			map.put(c, map.get(c) + 1);
			
			//map.putIfAbsent(c, map.getOrDefault(c, 0) + 1);//not working
			
			while(map.size() > 2) {
				char lc_ = inp.charAt(l);
				map.put(lc_, map.get(lc_) - 1);
				if(map.get(lc_) == 0) map.remove(lc_);
				l++;
			}
			
			result = Math.max(result, r - l + 1);
		}
		
		return result;
	}

}
//5