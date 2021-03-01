package coding_16th_dec_night;

import java.util.Arrays;
import java.util.HashMap;

public class Max_occurance_of_char_in_a_String {
	public static void main(String[] args) {
		String str = "test////";
		int cc = maxOccuranceInaString(str);
		System.out.println(cc);
		
		int cd = maxOccuranceInaStringHashing(str);
		System.out.println(cd);
		
	}
	
	//better approach - able to handle all type of char : total ascii char is 256
	private static int maxOccuranceInaStringHashing(String str) {
		HashMap<Character, Integer> hmap = new HashMap<>();
		int max = 0;
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(hmap.get(c) == null) {//new insertion
				hmap.put(c, 1);
			}else {
				int t = hmap.get(c)+1;
				hmap.put(c, t);
				if(t > max)
					max = t;
			}
		}
		return max;
	}

	//order of n^2 : total ascii char is 256
	private static int maxOccuranceInaString(String str) {		
		//String --> toCharArray() --> Arrays.sort() --> for loop
		char[] arr = str.toCharArray();
		Arrays.sort(arr);
		int temp = 0;
		int max = 0;
		char chr=arr[0];
		for(int i=0; i<arr.length-1; i++) {
			char c = arr[i];
			char c2 = arr[i+1];
			if(c == c2) {
				++temp;
				if(temp > max) {
					chr = c;
					max = temp;
				}
			}
			else
				temp = 1;
		}
		System.out.print(chr+" ");
		return max;
		
	}
}
/**

/ 3
4

 */
