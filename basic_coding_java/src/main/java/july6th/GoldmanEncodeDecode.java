package july6th;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
/** https://www.evernote.com/l/AGDRqq0HjBxCWJnVE6_C_7lP4hWQ1Fj8lSo/ **/
public class GoldmanEncodeDecode {
	public static void main(String[] args) {
		System.out.println(encode("hariom", "12345"));
		System.out.println(decode("haarrriiiiooooom","12345"));
	}
	
	public static String decode(String str, String key) {
		//get l1 , l2 , sb
		//traverse while with anoter pointer + while condition add boundry only
		//while body :
			//get num key -> key.get(j) - '0'
			//edge check we can reach in str or not : i + num > l1 return -1
			//now we have number and string : substring frm str str.substring(i, i + k)
			//now we have all same string - extract 1st string 
				//m1 : get 0th index as char -> and convert to string
				//m2 : Arrays.stream(sub.split).distince().collect(Collectiors.joining);
				//check that distinct is > 1 then return -1
			//sb .append (sub)
		//last check if l1 is left 
		//if i < l1 sb.append(sbtr.subr(i));
		
		StringBuilder sb = new StringBuilder();
		int l1 = str.length();
		int l2 = key.length();
		int p = 0;
		int q = 0;
		while((p < l1) && (q < l2)) {
			int k = key.charAt(q) - '0';
			if(p + k > l1) return "-1";
			String sub = str.substring(p, p + k);
			String collect = Arrays.stream(sub.split("")).distinct().collect(Collectors.joining());
			if(collect.length() > 1) return "-1";
			sb.append(collect);
			p = p + k;
			q++;
		}
		
		if(p < l1) {
			String sub = str.substring(p);
			sb.append(sub);
		}
		return sb.toString();
	}
	
	public static String encode(String str, String key) {
		int l1 = str.length();
		int l2 = key.length();
		int i = 0;
		StringBuilder sb = new StringBuilder();
		//get single char as string + single number 
		//then create new string 
		// add to resule
		while((l1 > 0) && (l2 > 0)) {
			char c = str.charAt(i);
			String s = String.valueOf(c);
			int k = key.charAt(i) - '0';
			
			List<String> nCopies = Collections.nCopies(k, s);//get lits of same string k time
			String join = String.join("", nCopies);//join all k times string sa sinle
			sb.append(join);
			
			l1--;
			l2--;
			i++;
		}
		if(l1 > 0) {//l1 count is not finished
			String sub = str.substring(i);
			sb.append(sub);
		}
		return sb.toString();
	}
}
