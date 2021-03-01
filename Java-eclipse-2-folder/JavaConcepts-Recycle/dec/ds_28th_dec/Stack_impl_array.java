package ds_28th_dec;
/**
 * 
 * @author Hariom Yadav | 28-Dec-2019
 *
 */
class Stack_class{
	int top;
	int size;
	int[] stack;
	
	public Stack_class(int size) {
		super();
		this.size = size;
		this.stack = new int[this.size];
		this.top = -1;
	}
	
	public void push(int k) {
		if(top == size-1) {
			System.out.println("Overflow");
			return;
		}
		stack[++top] = k;
	}
	
	public void pop() {
		if(top == -1) {
			System.out.println("Underflow");
			return;
		}
		top--;
	}
	
	public boolean isEmpty() {
		return (top == -1) ? true: false;
	}
	
	public void print() {
		System.out.print("Stack : ");
		for(int i = 0; i<=top; i++)
			System.out.print(stack[i]+" ");
		System.out.println();
	}
	
	
}

class Stack2{//stack with only main case not edge case

	int top;
	int size;
	int[] stack;
	
	public void push(int k) {
		//edge case
		stack[++top] = k;
	}
	
	public void pop() {
		//edge case
		top--;
	}
	
	public boolean isEmpty() {
		return (top == -1) ? true: false;
	}
	
}


public class Stack_impl_array {

	public static void main(String[] args) {
		Stack_class obj = new Stack_class(4);
		obj.pop();
		obj.print();
		obj.push(12);obj.print();
		obj.push(34); obj.print();
		obj.pop(); obj.print();
		obj.pop(); obj.print();
		obj.pop(); obj.print();
	}

}
/**
 * 
Underflow
Stack : 
Stack : 12 
Stack : 12 34 
Stack : 12 
Stack : 
Underflow
Stack : 
 * 
 */
