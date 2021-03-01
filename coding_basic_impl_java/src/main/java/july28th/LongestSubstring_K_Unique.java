package july28th;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring_K_Unique {
	public static void main(String[] args) {
		longestSubKUnique("abac");//6 length substring bcadef
		
		longestSubKUnique("abcabcabcaaaaaa");//6 length substring bcadef
	}

	private static void longestSubKUnique(String str) {
		System.out.println(str);
		//traverse
		//add in set -> before that remove all till unique -> calculate longest length
		int result = Integer.MIN_VALUE;
		Map<Character, Integer> map = new HashMap<>();
		int l = 0;
		for(int r = 0; r < str.length(); r++) {//add in map -> then check size
			char c = str.charAt(r);
			map.putIfAbsent(c, 0);//create key if not prpesent
			map.put(c, map.get(c) + 1);
			
			while(l < str.length() && map.size() > 2) {//window is unstable
				char cc = str.charAt(l);
				map.put(cc, map.get(cc) - 1);
				if(map.get(cc) == 0) map.remove(cc);
				l++;
			}
			
			
			result = Math.max(result, r - l + 1);
		}
		//System.out.println(map);//this final map has no relation with final resulet 
		System.out.println(result);
	}

}
/**
abac
3
abcabcabcaaaaaa
7


*/