package ds_stack_reverse_bst_bfs_dfs_print_delete_zig_zag_22nd_nov;

import java.util.Stack;
/**
 * 
 * @author Hariom Yadav - Nov 22, 2019
 *
 */

public class StackReverseUsingRecursion {

	static Stack<Integer> st = new Stack<>();
	public static void main(String[] args) {
		st.push(1); st.push(2); st.push(3); st.push(4);
		System.out.println("Input 	: "+st);
		
		reverseStackUsingRecursion(st);
		System.out.println("Output 	: "+st);
	}
	
	private static void reverseStackUsingRecursion(Stack<Integer> st) {
		if(st.isEmpty()) {//2. FCS makes stack empty - no operation here - operation is done by 3
			return;
		}else {
			int x = st.pop();//0. store in FCS
			reverseStackUsingRecursion(st);//1. FCS
			pushAtBottomOfStack(x);//3. after FCS - we reach here after step 2 - that mean stack is empty - so do our operation i.e. add at bottom
		}
	}
	
	private static void pushAtBottomOfStack(int x) {
		if(st.isEmpty()) {//2. FCS makes stack empty - that's what we need - now do our operation
			st.push(x);
			return;
		}else {
			int y = st.pop();//0. store in FCS
			pushAtBottomOfStack(x);//1. FCS
			st.push(y);//3. after FCS - top
		}
	}

}
