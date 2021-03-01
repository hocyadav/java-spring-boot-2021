package july28th;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Encoding_Decode {
	
	public String encode_(String msg, String key) {
		StringBuilder result = new StringBuilder();
		int l1 = msg.length();
		int l2 = key.length();
		int i = 0;
		while( (l1 > 0) && (l2 > 0) ) {
			int k = key.charAt(i) - '0';//get int , get string
			String s = String.valueOf(msg.charAt(i));
			
			String join = nCopiesStr(k, s);//actual logic
			result.append(join);
			
			l1--; l2--; i++;//update pointers
		}
		if(l1 > 0) {
			result.append(msg.substring(i));
		}
		return result.toString();
	}

	private String nCopiesStr(int k, String s) {
		List<String> nCopies = Collections.nCopies(k, s);
		String join = String.join("", nCopies);
		return join;
	}
	
	
	public String decode_(String msg, String key) {
		StringBuilder result = new StringBuilder();
		int l1 = msg.length();
		int l2 = key.length();
		int p1 = 0, p2 = 0;//moving pointer - traverse pointer boundry check

		while( (p1 < l1) && (p2 < l2) ) {//moving pointer boundry check
			int k = key.charAt(p2) - '0';//get int , get string , get substring
			
			if(p1 + k > l1) return "-1";//checking boundry for p1+k
			String substring = msg.substring(p1, p1 + k);//check boundry for p1+k 
			
			String collect = trimSubString(substring);
			
			if(collect.length() > 1) return "-1";
			else result.append(collect);
			
			//update the pointers
			p2++;//number pointer only 1 step
			p1 = p1 + k;//message pointer move to 
		}
		if(p1 < l1) {
			result.append(msg.substring(p1));
		}
		return result.toString();
	}

	private String trimSubString(String substring) {
		String[] split = substring.split("");
		String collect = Arrays.stream(split)
								.distinct()//get only distinct
								.collect(Collectors.joining());//join above distinct as single string
		return collect;
	}
}
