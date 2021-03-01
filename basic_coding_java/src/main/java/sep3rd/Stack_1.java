package sep3rd;

import java.util.Stack;

public class Stack_1 {
	public static void main(String[] args) {
		Stack<Character> stack = new Stack();
		
		String str = "()";
		char[] arr = str.toCharArray();
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == '(' ) {
				stack.push(arr[i]);//add
				System.out.println(stack);
			}else if(arr[i] == ')' && stack.peek() == '(') {
				stack.pop();//delete top
				System.out.println(stack);
			}
		}
		
		//System.out.println(stack);
		if(stack.empty() == true) {
			System.out.println("valid");
		}else {
			System.out.println("Not valide");
		}
	}

}
