package feb_6th_night;

import java.util.HashMap;
import java.util.Map;
/**
 * Find largest substring that contain 2 distinct character : Same problem as collect fruits only 2 basket 
 * @author Hariom Yadav | 07-Feb-2020
 *
 */
public class SlidingWindowAdvance_Working {
	public static void main(String[] args) {
		String str = "aaccd";
		int k = 3;
		int t = slidingWindow(str, k);
		System.out.println(t);
		
		int t2 = slidingWindow2(str, k);
		System.out.println(t2);
		
	}

	//using hashmap
	private static int slidingWindow2(String str, int k) {
		if(str == null || str.length() == 0) {
			return 0;
		}
		
		Map<Character, Integer> map = new HashMap<>();
		int distinct = 0;
		int l = 0;
		int maxWindowSize = 0;
		//2 pointer
		for(int r = 0; r < str.length(); r++) {
			if(map.containsKey(str.charAt(r)) == false) {
				distinct++;
			}
			map.put(str.charAt(r), map.getOrDefault(str.charAt(r), 0) + 1);//get value and add +1 or get default value 0 and + 1
			
			if(distinct > k) {
				//each time get one value and decrement by 1
				int val = map.get(str.charAt(l));
				if(val > 0) {
					map.put(str.charAt(l), map.get(str.charAt(l)) - 1);//decrement by 1
				}else if(val == 0) {
					distinct--;
				}
				
				l++;
				
			}
			maxWindowSize = Math.max(maxWindowSize, r - l + 1);
		}
		return maxWindowSize;
	}

	//using array as hashmap
	private static int slidingWindow(String str, int k) {
		//null check
		if(str == null || str.length() == 0) {
			return 0;
		}
		int[] map = new int[256];//hashmap for character
		int l = 0; // left pointer
		int distinct = 0;
		int maxWindowSize = 0;
		//2 pointer concept : left and right
		for(int r = 0; r < str.length(); r++) {
			if(map[str.charAt(r)] == 0) distinct++;
			map[str.charAt(r)]++;
			
			while(distinct > k) {//always check if distict element is greater than k or not coz we want k distict length
				//take one value and decrement 1 each time
				map[str.charAt(l)]--;//subtrating count as 1 from map
				if(map[str.charAt(l)] == 0) distinct--;//if count for a given char is 0 then distinct cont also reduce
				
				l++;
			}
			maxWindowSize = Math.max(maxWindowSize, r - l + 1);
		}
		return maxWindowSize;
	}
}
/*
5
5
 */
