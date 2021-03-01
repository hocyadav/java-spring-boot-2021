package jan_27th;
/**
 * 
 * @author Hariom Yadav | 27-Jan-2020
 *
 */
public class Coding_valid_Anagram {
	public static void main(String[] args) {
		String s1 = "anagram";
		String s2 = "gramana";
		boolean b = validAnagram(s1, s2);
		System.out.println(b);
		
		String s11 = "hariom";
		String s21 = "omhari";
		boolean b1 = validAnagram(s11, s21);
		System.out.println(b1);
		
		String s12 = "omprakash";
		String s22 = "omprakashyadav";
		boolean b2 = validAnagram(s12, s22);
		System.out.println(b2);
		
	}

	private static boolean validAnagram(String s1, String s2) {
		int[] count = new int[26];//initial all values 0 as default
		//hash map - key : char index, value : char total count
		for(char c : s1.toCharArray())
			count[c - 'a']++;
		
		//2nd string - make char count as 0
		for(char c : s2.toCharArray())
			count[c - 'a']--;
		
		//check for count : if 0 then fine else not anagram
//		for(int i : count) {
//			if(i == 0)
//				continue;
//			else
//				return false;
//		}
		
		//for loop continue not required
		for(int i : count) {
			if(i != 0)
				return false;
		}
		
		return true;
	}
}
/**
true
true
false
*/