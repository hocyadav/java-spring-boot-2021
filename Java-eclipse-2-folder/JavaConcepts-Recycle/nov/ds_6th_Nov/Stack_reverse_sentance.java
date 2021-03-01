package ds_6th_Nov;
/**
 * 
 * @author Hariom Yadav - Nov 6, 2019
 *
 */
//use of ds
class MyStack{
	//know something
	int top;
	int size;
	String[] stack;
	
	//does some
	public MyStack(int s) {
		top =-1;
		size = s;
		stack = new String[size];
	}
	
	void push(String s) {//top++ & add
		if(top == size) {
			System.out.println("Overflow");
			return;
		}
		stack[++top] = s;
	}
	
	void pop() {//top--
		if(top == -1) {
			System.out.println("Underflow");
			return;
		}
		top--;
	}
	
	void print() {
		System.out.print("Stack : ");
		for(int i=0; i<=top; i++) {
			System.out.print(stack[i]+" ");
		}
		System.out.println();
	}
	boolean isEmpty() {
		return (top == -1)?true:false;
	}

	public String top() {
		if(top == -1) return "";
		return stack[top];
	}
	
	
	
}


public class Stack_reverse_sentance {
	public static void main(String[] args) {
		MyStack obj = new MyStack(10);
		/*obj.push("hariom");
		obj.push("yadav");
		obj.pop();
		obj.print();*/
		
		
		//StringBuilder str = new StringBuilder("hariom Yadav");
		String str = "hariom yadav";
		String[] arr = str.split(" ");
		for(int i=0; i<arr.length; i++) {
			obj.push(arr[i]);
		}
		StringBuilder st = new StringBuilder();
		while(!obj.isEmpty()) {
			System.out.print(obj.top()+" ");
			obj.pop();
		}
		
	}
}
