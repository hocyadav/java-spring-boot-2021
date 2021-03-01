package feb_7th_office;

/**
 * Given "aaabbcde" k=2, find largest substring that contain only 2 distinct char
 * @author Hariom Yadav | 07-Feb-2020
 *
 */
public class SlidingWindow_adv_try {
	public static void main(String[] args) {
		String str = "ababce";
		int k = 2;
		int t = slidingWindow(str, k);
	}

	private static int slidingWindow(String str, int k) {
		//null n error check
		if(str == null || str.length() == 0)
			return 0;
		
		//2 pointer
		int[] map = new int[256];
		for(int r = 0; r < str.length(); r++) {
			//if(map[])
		}
		return 0;
	}
}
