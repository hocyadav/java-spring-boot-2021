package problem_solving_skill_29th_nov;
/**
 * 
 * @author Hariom Yadav - Nov 29, 2019
 *
 */
public class EvenLengthPalindrom {

	public static void main(String[] args) {
		int nth = 123;//1 length palindrom
		palindromEvenLength(nth);
	}

	private static void palindromEvenLength(int nth) {
		
		String nthStr = String.valueOf(nth);
		System.out.println(nthStr);
		StringBuffer sb = new StringBuffer(nthStr);
		//for 1 -> 11, 2 -> 22, 3 -> 33, ... 10 -> 1001, 12 -> 1221 ... for given input output is append as mirror image
		int j = nthStr.length()-1;
		
		while(j >= 0) {
			sb.append(nthStr.charAt(j));
			j--;
		}
		
		System.out.println(sb);
	}
}
/**
123
123321
 */
