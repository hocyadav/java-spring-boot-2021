package coding_11th_dec_night;

import java.util.Stack;
/**
 * 
 * @author Hariom Yadav | 11-Dec-2019
 * https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 */

class LinkedList_P{
	Node2 head;
	Stack<Integer> stack = new Stack<Integer>();
	
	public void addFirstPlace(int v) {
		Node2 nn = new Node2(v);
		if(head == null)
			head = nn;
		else {
			nn.next = head;
			head = nn;
		}
	}
	
	public void checkPalindromeLL() {
		
		storeInStack();
		
		Node2 t = head;
		
		while(t != null) {//TODO : not working -> done change stack to integer from Node type
			int x = stack.pop();
			if(t.data == x) {
				System.out.println("palindrom "+t.data);
				t = t.next;
			}else {
				System.out.println("Not a palindrome");
				return;
			}
		}
	}

	private void storeInStack() {
		Node2 t = head;
		
		while(t != null) {
			stack.push(t.data);
			t = t.next;
		}
		//System.out.println(stack);
	}
	
	public void printLL() {
		Node2 t = head;
		System.out.print("List : ");
		while(t != null) {
			System.out.print(t.data+" ");
			t = t.next;
		}
		System.out.println();
	}
	
}

public class Linked_List_is_Palindrome_orNot_usingStack {
	public static void main(String[] args) {
		LinkedList_P obj = new LinkedList_P();
		obj.addFirstPlace(1);
		obj.addFirstPlace(2);
		obj.addFirstPlace(2);
		obj.addFirstPlace(1);obj.printLL();
		obj.checkPalindromeLL();
		
	}
}
/*
palindrom
palindrom
palindrom
palindrom

*/