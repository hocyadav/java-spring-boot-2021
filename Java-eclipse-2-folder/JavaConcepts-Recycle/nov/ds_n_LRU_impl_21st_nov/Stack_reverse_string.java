package ds_n_LRU_impl_21st_nov;

import java.util.Stack;
/**
 * 
 * @author Hariom Yadav - Nov 21, 2019
 *
 */
public class Stack_reverse_string {

	public static void main(String[] args) {
		String str = "abcde";
		System.out.println("Input 	: "+str);
		String t = reverseStrUsingStack(str);
		System.out.println("Outpit 	: "+t);
		
	}
	
	private static String reverseStrUsingStack(String str) {
		Stack<Character> st = new Stack<>();
		
		for(int i=0; i<str.length(); i++) {
			st.push(str.charAt(i));
		}
		
		//System.out.println(st);
		StringBuilder sb = new StringBuilder();
		while(!st.isEmpty()){
			sb.append(st.pop());
		}
		//System.out.println(sb);
		
		return sb.toString();
	}

}
