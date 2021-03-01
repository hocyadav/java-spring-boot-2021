package july17th;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring_with_all_uniqueChar {
	public static void main(String[] args) {
		String inp = "abaacdef";
		int subStrlen = longestSubstringAllUniqueChar(inp);
		System.out.println(subStrlen);
	}

	private static int longestSubstringAllUniqueChar(String inp) {
		int result = 0;
		
		int l = 0;
		Set<Character> set = new HashSet();
		
		for(int r = 0; r < inp.length(); r++) {
			char c = inp.charAt(r);
			
			while(set.contains(c)) {
				char c2 = inp.charAt(l);
				set.remove(c2);
				l++;
			}
			
			set.add(c);//add in set , only add uniquue so check before is present or not if present then remove from left so that window become unique
			result = Math.max(result,  r - l + 1);
		}
		
		
		return result;
	}
}
//5