package ds_n_LRU_impl_21st_nov;

import java.util.Stack;
/**
 * 
 * @author Hariom Yadav - Nov 21, 2019
 *
 */
public class Stack_reverse_number {

	public static void main(String[] args) {
		int i = 1234;
		System.out.println("Input 	: "+i);
		int t = reverseIntUsingStack(i);
		System.out.println("Output 	: "+t);
	}

	private static int reverseIntUsingStack(int i) {
		Stack<Integer> st = new Stack<>();
		
		while(i>0) {
			st.push(i % 10);//mod : last digit
			i = i/10; // divide : except last digit
		}
		
		//System.out.println(st);
		
		int reverse = 0;
		int x = 1;
		
		while(!st.isEmpty()) {//or st.empty()
			reverse = reverse + st.pop()*x;//adding number at 1st place - like link list reverse concept
			x = x * 10;
		}
		return reverse;
	}
}
