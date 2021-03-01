package ds_algo_1st_dec;

/**
 * 
 * @author Hariom Yadav | 01-Dec-2019
 *
 */

//data structure - array

//use of DS
class Stack{
	//know something
	int top;
	int size;
	int[] stack;
	
	//does something
	public Stack(int size) {
		top = -1;
		this.size = size;
		stack = new int[this.size];
	}
	
	
	public void push(int v) {//top++ and add
		if(top == size-1) {
			System.out.println("overflow");
			return;
		}
		stack[++top] = v;
	}
	
	public void pop() {//top--
		if(top == -1) {
			System.out.println("Underflow");
			return;
		}
		top--;
	}
	
	public int top() {//top ele
		if(top == -1) return -1;
		return stack[top];
	}
	
	public void print() {
		System.out.print("Stack : ");
		for(int i=0; i<=top; i++)
			System.out.print(stack[i]+" ");
		System.out.println();
	}
	
}

public class Stack_impl_array {
	public static void main(String[] args) {
		Stack obj = new Stack(4);
		obj.pop();
		obj.print();
		obj.push(10); obj.print();
		obj.push(20); obj.print();
		obj.push(30); obj.print();
		obj.push(40); obj.print();
		obj.push(50); obj.print();
	}
}
/** 
Underflow
Stack : 
Stack : 10 
Stack : 10 20 
Stack : 10 20 30 
Stack : 10 20 30 40 
overflow
Stack : 10 20 30 40 
*/
