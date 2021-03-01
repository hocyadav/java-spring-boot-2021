package june12th;

class Stack3<T> {
	T[] arr;
	int top;
	int size;
	
	@SuppressWarnings("unchecked")
	public Stack3(int size) {
		this.top = -1;
		this.size = size;
		arr =(T[])new Object[size];//generic array declaration
	}
	
	public void push(T data) {
		if(top == this.size - 1) {
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
		for(int i =0; i <= top; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
}


public class Stack_Generic {
	public static void main(String[] args) {
		Stack3<Integer> stack3 = new Stack3<Integer>(3);
		stack3.pop();
		stack3.push(10);
		stack3.print();
		stack3.push(10);
		stack3.print();
		stack3.push(10);
		stack3.print();
		stack3.push(10);
		stack3.print();
		
		Stack3<String> stack4 = new Stack3<String>(3);
		stack4.pop();
		stack4.push("hariom");
		stack4.print();
		stack4.push("yadav");
		stack4.print();
		stack4.push("piyushSir");
		stack4.print();
		stack4.push("anirudh");
		stack4.print();
	}
}
