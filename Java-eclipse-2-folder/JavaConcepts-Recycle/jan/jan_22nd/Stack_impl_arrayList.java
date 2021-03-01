package jan_22nd;

import java.util.ArrayList;
/**
 * IMPL stack using List / ArrayList 
 * @author Hariom Yadav | 22-Jan-2020
 *
 */
class Stack{
	ArrayList<Integer> stack;
	int size;
	int top;
	
	public Stack(int s) {
		size = s;
		top = -1;
		stack = new ArrayList<>();
	}
	
	public void push(int k) {//top ++ , add
		if(top == size - 1) {
			System.out.println("Overflow..");
			return;
		}
		stack.add(k);
		top++;
	}
	
	public void pop() { //top -- 
		if(top == -1) {
			System.out.println("Underflow..");
			return;
		}
		stack.remove(top);
		top--;
	}
	
	public int top() {
		if(top == -1) {
			System.out.println("Underflow..");
			return -1;
		}
		return stack.get(top);
	}
	
	public boolean isEmpty() {
		return top == -1 ? true : false;
	}
	
	public void print() {
		for(int i=0; i <= top; i++) {
			System.out.print(stack.get(i)+" ");
		}
		System.out.println();
	}
	
}



public class Stack_impl_arrayList {
	public static void main(String[] args) {
		Stack obj = new Stack(4);
		obj.pop();
		obj.push(11);obj.print();
		obj.pop();obj.print();
		obj.push(11);obj.print();
		obj.push(12);obj.print();
		obj.push(13);obj.print();
		obj.push(14);obj.print();
		obj.push(15);obj.print();
	}
	
}
/**

Underflow..
11 

11 
11 12 
11 12 13 
11 12 13 14 
Overflow..
11 12 13 14 
 */
