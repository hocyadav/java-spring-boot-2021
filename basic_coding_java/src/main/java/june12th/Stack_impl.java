package june12th;

class Stack {
	int[] arr;
	int top;
	int size;
	public Stack(int size) {
		super();
		this.top = -1;
		this.size = size;
		arr = new int[size];
	}
	
	public void push(int data) {//++top , add
		if(top == size-1) {//1st element
			System.out.println("Overflow");
			return;
		}
		arr[++top] = data;
	}
	
	public void pop() {
		if(top == -1) {
			System.out.println("Underflow");
			return;
		}
		top--;
	}
	
	public void print() {
		System.out.print("Stack : ");
		for (int i =0; i <= top; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	
}



public class Stack_impl {
	public static void main(String[] args) {
		Stack stack = new Stack(3);
		stack.pop();
		
		stack.print();
		stack.push(10);
		stack.print();
		stack.push(11);
		stack.print();
		stack.push(11);
		stack.print();
		stack.push(11);
		stack.print();
		stack.push(11);
		stack.print();
		stack.pop();
		stack.print();
	}
}
