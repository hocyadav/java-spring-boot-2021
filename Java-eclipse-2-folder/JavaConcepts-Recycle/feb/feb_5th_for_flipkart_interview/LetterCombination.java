package feb_5th_for_flipkart_interview;

import java.util.ArrayList;
import java.util.List;

public class LetterCombination {

	public static void main(String[] args) {
		String digit = "23";
		
		List<String> t = letterCombination(digit);
		System.out.println(t);
	}

	private static List<String> letterCombination(String digit) {
		List<String> list = new ArrayList<>();
		//1. error check
		if(digit == null || digit.length() == 0) {
			return list;
		}
		
		String[] mapping = {//hash map
				"0",
				"1",
				"abc",
				"def",
				"ghi",
				"jkl",
				"mnop",
				"qrs",
				"tuv",
				"wxyz"
		};
		
		letterCombination_rec(list, digit, "", 0, mapping);
		
		return list;
	}

	private static void letterCombination_rec(List<String> list, String digit, String current, int index, String[] mapping) {
		if(index == digit.length() ) {
			list.add(current);
			return;
		}
		
		int in = digit.charAt(index) - '0';//"24"
		String letter = mapping[in];
		
		for(int i = 0; i < letter.length(); i++) {
			letterCombination_rec(list, digit, current + letter.charAt(i), index+1, mapping);
		}
	}

}
/*
[ad, ae, af, bd, be, bf, cd, ce, cf]
 */
