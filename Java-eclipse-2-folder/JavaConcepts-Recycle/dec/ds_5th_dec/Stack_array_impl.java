package ds_5th_dec;

/**
 * 
 * @author Hariom Yadav | 05-Dec-2019
 *
 */

//DS - array

//use of DS
class Stack{
	int top;
	int size;
	int[] stack;
	
	public Stack(int s) {
		size = s;
		stack = new int[size];
		top = -1;
	}
	
	public void push(int v) {//++top , add
		if(top == size-1) {
			System.out.println("Overflow");
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
	
	public int top() {
		if(top == -1) return -1;
		return stack[top];
	}
	/**
	 * Check stack is empty or not
	 * @return true if stack is empty else return false
	 * 
	 */
	public boolean isEmpty() {
		return (top == -1) ? true : false;
	}
	
	/**
	 * Print stack
	 */
	public void print() {
		System.out.print("Stack : ");
		for(int i=0; i<=top; i++) {
			System.out.print(stack[i]+" ");
		}
		System.out.println();
	}
	
	
}


public class Stack_array_impl {
	public static void main(String[] args) {
		Stack obj = new Stack(4);
		
		System.out.println(obj.isEmpty());
		
		obj.pop();
		obj.push(2); obj.print();
		obj.push(3); obj.print();
		obj.push(5); obj.print();
		obj.push(7); obj.print();
		obj.push(1); obj.print();
		System.out.println(obj.isEmpty());
		
		obj.pop(); obj.print();
		obj.pop(); obj.print();
		obj.pop(); obj.print();
		obj.pop(); obj.print();
		obj.pop(); obj.print();
		System.out.println(obj.isEmpty());
		
	}
}

/**
 * 
true
Underflow
Stack : 2 
Stack : 2 3 
Stack : 2 3 5 
Stack : 2 3 5 7 
Overflow
Stack : 2 3 5 7 
false
Stack : 2 3 5 
Stack : 2 3 
Stack : 2 
Stack : 
Underflow
Stack : 
true

*/
