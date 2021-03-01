package ds_algo_28th_nov;

import java.util.Stack;
/**
 * 
 * @author Hariom Yadav | 28-Nov-2019
 *Impl is same as reverse stack using recursion - only 1 extra if condition required
 *
 */
public class Stack_sort_using_recursion {
	public static void main(String[] args) {
		Stack<Integer> st = new Stack<>();
		st.add(12); st.add(1); st.add(0); st.add(-2);st.add(100);
		System.out.println("Initial stack 	: "+st);
		
		sortStack(st);
		System.out.println("Sorted stack 	: "+st);
	}
	
	private static void sortStack(Stack<Integer> st) {
		if(st.isEmpty()) return;
		else {
			int temp = st.pop();
			sortStack(st);//Func Call stack
			
			stackInsertEleInSortedOrder(st, temp);
		}
	}

	/**
	 * Insert element in stack in sorted order
	 * @param st
	 * @param temp
	 * 
	 * only extra " temp > st.peek() " added in condition check else code is same as insert at bottom
	 * 
	 */
	private static void stackInsertEleInSortedOrder(Stack<Integer> st, int temp) {
		if(st.isEmpty() || temp > st.peek()) {
			st.push(temp);
		}else {
			int x = st.pop();
			stackInsertEleInSortedOrder(st, temp);//FCS
			
			st.push(x);
		}
	}
	/**
	 * Insert element at bottom of stack
	 * @param st
	 * @param temp
	 */
	@SuppressWarnings("unused")
	private static void stackInsertAtBottom(Stack<Integer> st, int temp) {
		if(st.isEmpty()) {
			st.push(temp);
		}else {
			int x = st.pop();
			stackInsertEleInSortedOrder(st, temp);//FCS
			
			st.push(x);
		}
	}
	
}

/**
 * 
 * 
Initial stack 	: [12, 1, 0, -2, 100]
Sorted stack 	: [-2, 0, 1, 12, 100]
 * 
 * 
 */

