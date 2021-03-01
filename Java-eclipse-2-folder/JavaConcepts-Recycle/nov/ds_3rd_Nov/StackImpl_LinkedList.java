package ds_3rd_Nov;
/**
 * 
 * @author Hariom Yadav - Nov 3, 2019
 *
 */
//ds - Node

//use of ds
class Stack{
	//know something
	Node top;
	//does something
	Stack(){
		top = null;
	}
	
	void push(String str) {
		Node nn = new Node(str);
		
		if(top == null) {
			top = nn;
		}else {//insert at begigning 
			nn.next = top;
			top = nn;
		}
	}
	void pop() {
		if(top == null) {
			System.out.println("underflow");
			return;
		}
		top = top.next;
	}
	
	boolean isEmpty() {
		return (top==null)?true:false;
	}
	
	String top() {
		if(top == null) {
			System.out.println("empty");
			return "null";
		}
		return top.data;
	}
	
	void print() {
		Node temp = top;
		System.out.print("Stack : ");
		while(temp!=null) {
			System.out.print(temp.data+" ");
			temp = temp.next;
		}
		System.out.println("");
	}
	
}



public class StackImpl_LinkedList {
	public static void main(String[] args) {
		Stack obj = new Stack();
		obj.pop();obj.print();
		obj.push("a");obj.print();
		obj.push("b");obj.print();
		obj.push("c");obj.print();
		obj.push("d");obj.print();
		obj.pop();obj.print();
		obj.pop();obj.print();
		obj.pop();obj.print();
		obj.pop();obj.print();
		obj.pop();obj.print();
	}
}
