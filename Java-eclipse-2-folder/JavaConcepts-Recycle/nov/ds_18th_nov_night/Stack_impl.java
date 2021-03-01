package ds_18th_nov_night;
/**
 * 
 * @author Hariom Yadav - Nov 18, 2019
 *
 */

//ds : array

class Stack{
	//know something
	int top;
	int size;
	int[] stack;
	
	//does something
	public Stack(int s) {
		size = s;
		top = -1;
		stack = new int[size];
	}
	
	public void push(int v) {//top++ & add
		if(top == size-1) {
			System.out.println("Overflow stack");
			return;
		}
			stack[++top] = v;
	}
	
	public void pop() {
		if(top == -1) {
			System.out.println("Underflow stack");
			return;
		}
		--top;
	}
	
	public int top() {
		if(top == -1) return -1;
		return stack[top];
	}
	
	public void print() {
		System.out.print("Stack : ");
		for(int i=0; i<=top; i++) {
			System.out.print(stack[i]+" ");
		}
		System.out.println();
	}
}

public class Stack_impl {

	public static void main(String[] args) {
		Stack obj = new Stack(4);
		obj.print();
		obj.pop();
		obj.push(1); obj.print();
		obj.push(1); obj.print();
		obj.push(1); obj.print();
		obj.push(1); obj.print();
		obj.push(1); obj.print();
	}

}
