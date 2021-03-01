package feb_17th;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Hariom Yadav | 17-Feb-2020
 *
 */
public class Substring_k_uniueChar {
	public static void main(String[] args) {
		String s = "abbbbba";
		int k =2;
		substringKuniqueChar("abc", 3);
	}

	private static void substringKuniqueChar(String s, int k) {
		if(s == null || s.length() == 0) {
			System.out.println("");
			return;
		}
		//sliding window(2 point) + map , map coz we can get distinct key count
		int l = 0;
		Map<Character, Integer> map = new HashMap();
		int longest = 0;
		for(int r = 0; r < s.length(); r++) {
			int count = map.getOrDefault(map.get(s.charAt(r)), 0);
			map.put(s.charAt(r), count + 1);
			if(map.size() == 3) {
				System.out.println("3 count");
			}
			if(map.size() > k) {
				int c = map.get(s.charAt(l));
				map.put(s.charAt(l), c - 1);
				if(map.get(s.charAt(l)) == 0) {
					map.remove(s.charAt(l));
				}
				l++;
			}
			longest = Math.max(longest, r - l + 1);
		}
		System.out.println(longest);
		
	}
}
//7
