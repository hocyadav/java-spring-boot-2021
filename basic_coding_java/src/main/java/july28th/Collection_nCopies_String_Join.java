package july28th;

import java.util.Collections;
import java.util.List;

public class Collection_nCopies_String_Join {
	public static void main(String[] args) {
		List<String> listStr = Collections.nCopies(2, "hari");
		System.out.println(listStr);
		
		List<Character> listChr = Collections.nCopies(3, 'c');
		System.out.println(listChr);
		
		String join = String.join("->", "hari", "hariom");
		System.out.println(join);
		
		String join2 = String.join("-", listStr);
		System.out.println(join2);
		
		
	}
}
