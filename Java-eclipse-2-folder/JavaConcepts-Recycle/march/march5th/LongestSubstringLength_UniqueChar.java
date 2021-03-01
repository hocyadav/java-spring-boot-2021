package march5th;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author Hariom Yadav | 05-Mar-2020
 *
 */
public class LongestSubstringLength_UniqueChar {
	public static void main(String[] args) {
		String str = "abcabc";
		longestSubstringUniqChar(str);
		int k = 3;
		longestSubstringKUniqChar("ddeeffgm", k);
	}

	private static void longestSubstringKUniqChar(String str, int k) {
		if(str == null || str.length() == 0) {
			System.out.println("0");
			return;
		}
		//sw + map
		int l = 0;
		int longest = 0;
		Map<Character, Integer> map = new HashMap();
		for(int r = 0; r < str.length(); r++) {
			int count = map.getOrDefault(str.charAt(r), 0);
			map.put(str.charAt(r), count + 1);
			
			while(map.size() > k) {
				map.put(str.charAt(l), map.get(str.charAt(l)) - 1);
				if(map.get(str.charAt(l)) == 0) {
					map.remove(str.charAt(l));
				}
				l++;
			}
			longest = Math.max(longest, r - l + 1);
		}
		System.out.print("longestSubstringKUniqChar : "+str+" "+ longest);
	}

	private static void longestSubstringUniqChar(String str) {
		if(str == null || str.length() == 0) {
			System.out.println("0");
			return;
		}
		//sw + set
		int l = 0;
		Set<Character> set = new HashSet();
		int longest = 0;
		for(int  r = 0; r < str.length(); r++) {
			while(set.contains(str.charAt(r))) {
				set.remove(str.charAt(l));
				l++;
			}
			
			set.add(str.charAt(r));
			longest = Math.max(longest, r - l + 1);
		}
		System.out.print("longestSubstringUniqChar : "+str+" "+ longest);
		System.out.println();
	}
}
/**
longestSubstringUniqChar : abcabc 3
longestSubstringKUniqChar : ddeeffgm 6
 */
