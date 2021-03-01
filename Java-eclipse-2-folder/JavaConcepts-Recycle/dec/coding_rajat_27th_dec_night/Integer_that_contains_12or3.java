package coding_rajat_27th_dec_night;

import java.util.Collections;
import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav | 27-Dec-2019
 *
 */
public class Integer_that_contains_12or3 {

	public static void main(String[] args) {
		int[] arr = {123,1212,4545,231,565665,12541};
		LinkedList<Integer> list = new LinkedList<>();
		
		for(int i: arr) {
			String s = String.valueOf(i);
			if(s.contains("1") && s.contains("2") && s.contains("3")) {
				list.add(i);
			}
		}
		
		Collections.sort(list);
		
		if(list.size() > 0)
			System.out.println(list);
		else
			System.out.println(-1);
		
	}
}
/**
[123, 231]
*/