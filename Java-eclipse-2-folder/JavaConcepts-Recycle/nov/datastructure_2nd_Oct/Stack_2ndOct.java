package datastructure_2nd_Oct;
/**
 * 
 * @author Hariom Yadav - Nov 2, 2019
 *
 */
class Stack{
	//know something
	int top;
	int size;
	String[] stack;
	
	//does something
	Stack(){
		top = -1; size = 10;
		stack = new String[size];
	}
	
	void push(String s) {
		if(top == size-1) {
			System.out.println("overflow"); return;
		}
		stack[++top] = s;
	}
	
	void pop() {
		if(top == -1) {
			System.out.println("underflow"); return;
		}
		--top;
	}
	String top() {
		if(isEmpty()) {
			System.out.println("empty");
			return "-1";
		}else
			return stack[top];
	}
	boolean isEmpty() {
		return (top == -1)?true:false;
	}
	void traverse() {
		System.out.print("stack : ");
		for(int i=0; i<=top; i++) {
			System.out.print(stack[i]+" ");
		}
		System.out.println("");
	}
}


public class Stack_2ndOct {
	public static void main(String[] arg) {
		Stack obj = new Stack();
		System.out.println(obj.top);
		obj.push("hariom");obj.traverse();
		obj.push("yadav");obj.traverse();
		obj.push("omprakash");obj.traverse();
		obj.pop();obj.traverse();
		obj.pop();
		obj.pop();obj.traverse();
		obj.traverse();
		obj.pop();
		obj.push("omprakash");obj.traverse();
		obj.push("omprakash");obj.traverse();
		obj.push("omprakash");obj.traverse();
		obj.push("omprakash");obj.traverse();
		obj.push("omprakash");obj.traverse();
		obj.push("omprakash");obj.traverse();
		obj.push("omprakash");obj.traverse();
		obj.push("omprakash");obj.traverse();
		obj.push("omprakash");obj.traverse();
		obj.push("omprakash");obj.traverse();
		obj.push("omprakash");obj.traverse();
		
		
	}
	
}
