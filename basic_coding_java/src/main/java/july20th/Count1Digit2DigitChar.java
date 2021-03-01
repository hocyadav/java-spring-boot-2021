package july20th;

/** https://www.geeksforgeeks.org/count-of-1-bit-and-2-bit-characters-in-the-given-binary-string/ **/
public class Count1Digit2DigitChar {
	public static void main(String[] args) {
		String str = "1010110";
		//count two digit and one digit char/values in above
		count(str);
		count("111100");
		count("000");
	}

	//traverse and jump one or two step based on input -> also incerement count
	private static void count(String str) {
		int i = 0;
		int result = 0; 
		while(i < str.length()) {
			char c = str.charAt(i);
			
			if(c == '1') i += 2;
			else i += 1;
			
			result++;
		}
		System.out.println(result);
	}
}
/**
4
4
3
*/