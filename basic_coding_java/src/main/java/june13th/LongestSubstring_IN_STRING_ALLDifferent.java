package june13th;

import java.util.HashSet;
import java.util.Set;

//logic : 1. set DS + 2 pointer --> 2. traverse (check in set + add in set + update max longest)
public class LongestSubstring_IN_STRING_ALLDifferent {
	public static void main(String[] args) {
		String str = "abcaaabcde";
		System.out.println(longestSubstringAllDiff(str));
		
		
	}

	private static int longestSubstringAllDiff(String str) {
		int result = 0;
		if(str.length() == 0) {//null check
			return result;
		}
		//1. set + 2 pointers
		Set<Character> set = new HashSet<>();
		int l = 0;
		int r = 0;
		
		//2. check in set + add in set + update max longest length
		for (r = 0; r < str.length(); r++) {
			char cr = str.charAt(r);
			while(set.contains(cr)) {//replace if with while, cut all the left until it contain r
				//https://photos.app.goo.gl/MyPnMuKikedTA37J6
				set.remove(cr);
				l++;
			}
			set.add(cr);
			result = Math.max(result, r - l + 1);
		}
		return result;
	}
}
