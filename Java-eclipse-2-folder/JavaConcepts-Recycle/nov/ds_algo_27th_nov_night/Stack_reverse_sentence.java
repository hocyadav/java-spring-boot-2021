package ds_algo_27th_nov_night;

import java.util.Stack;
/**
 * 
 * @author Hariom Yadav | 27-Nov-2019
 *
 */
public class Stack_reverse_sentence {
	public static void main(String[] args) {
		String str = "Hari om yadav ca tech bangalore";
		System.out.println("INPUT : "+str);
		Stack<String> st = new Stack<>();
		StringBuffer sb = new StringBuffer();
		
		
		String[] star = str.split(" ");
		for(String s: star)
			System.out.println(s);
		
		for(String s : star) {
			st.push(s);
		}
		
		System.out.println("stack data : "+st);
		System.out.println("stack top : "+st.peek());//check top element
		
		while(!st.isEmpty()) {
			sb.append(st.pop()).append(" ");
		}
		
		System.out.println("OUTPUT : "+sb);
		
	}
}
/**
INPUT : Hari om yadav ca tech bangalore
Hari
om
yadav
ca
tech
bangalore
stack data : [Hari, om, yadav, ca, tech, bangalore]
stack top : bangalore
OUTPUT : bangalore tech ca yadav om Hari 
**/
