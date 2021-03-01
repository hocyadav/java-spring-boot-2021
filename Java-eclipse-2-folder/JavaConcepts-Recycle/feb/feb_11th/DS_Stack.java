package feb_11th;

import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav | 11-Feb-2020
 *
 */
class Stack{
	int[] stack;
	int size;
	int top;
	
	public Stack(int s) {
		size = s;
		top = -1;
		stack = new int[size];
	}
	
	public void push(int k) {//top++, add
		if(top == size-1) {
			System.out.println("Overflow..");
			return;
		}
		stack[++top] = k;
	}
	
	public void pop() {//top--
		if(top == -1) {
			System.out.println("Underflow..empty..");
			return;
		}
		top--;
	}
	
	public int top() { //
		if(top == -1) {
			System.out.println("Empty..-1");
			return -1;
		}
		return stack[top];
	}
	
	public void print() {
		System.out.print("Stack : ");
		for(int i =0; i <= top; i++) {
			System.out.print(stack[i]+" ");
		}
		System.out.println();
	}
	
	
}

public class DS_Stack {
	public static void main(String[] args) {
		Stack obj = new Stack(4);
		obj.print();
		obj.pop();
		obj.top();
		obj.push(12);obj.print();
		obj.push(14);obj.print();
		obj.push(15);obj.print();
		obj.push(16);obj.print();
		obj.push(17);obj.print();
		
		obj.pop();obj.print();
		obj.pop();obj.print();
		obj.pop();obj.print();
		obj.pop();obj.print();
		obj.pop();obj.print();
		
		
		
	}
}

/**
Stack : 
Underflow..empty..
Empty..-1
Stack : 12 
Stack : 12 14 
Stack : 12 14 15 
Stack : 12 14 15 16 
Overflow..
Stack : 12 14 15 16 
Stack : 12 14 15 
Stack : 12 14 
Stack : 12 
Stack : 
Underflow..empty..
Stack : 



*/