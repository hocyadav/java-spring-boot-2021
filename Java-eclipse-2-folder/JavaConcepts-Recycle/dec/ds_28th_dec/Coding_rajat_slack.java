package ds_28th_dec;

import java.util.Stack;

/**
 * 
 * 
 * @author Hariom Yadav | 28-Dec-2019
 *i/p : aaabbccc, x=3 -> o/p : bb
 */

class Node{
	char cdata;
	int count;
	
	public Node(char c) {
		this.cdata = c;
		count = 1;
		
	}
}

public class Coding_rajat_slack {

	public static void main(String[] args) {
		String str = "aaabbccc";
		int x = 3;
		Stack<Node> stack = new Stack<>();
		
		for(int i=0; i<str.length();i++) {
			char current = str.charAt(i);
			Node nn = new Node(current);
			
			if(stack.isEmpty()) {//1st time insertion
				stack.push(nn);
			}else {
				if(current == stack.peek().cdata) {//update count only
					stack.peek().count++;
				}else {
					int tcount = stack.peek().count;
					if(tcount >= x) {
						stack.pop();
						stack.push(nn);
					}else {
						stack.push(nn);
					}
				}
			}
			
			
		}
		while(!stack.isEmpty())
			System.out.println(stack.pop().cdata+" ");
	}

}
