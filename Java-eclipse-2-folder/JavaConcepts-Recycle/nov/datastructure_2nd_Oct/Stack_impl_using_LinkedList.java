package datastructure_2nd_Oct;
/**
 * 
 * @author Hariom Yadav - Nov 2, 2019
 *
 */

class StackLL{
	//know something
	Node headTop;
	
	//does something
	public StackLL() {
		headTop = null;
	}
	
	void push(int v){//adding new element at first - else top operation not possible
		//1st node
		Node newNode = new Node(v);
		if(headTop == null) {
			headTop = newNode;
		}else {//add before headTop
			newNode.next = headTop;
			headTop = newNode;
		}
	}
	
	void pop() {//deleting 1st node
		if(headTop == null) {
			System.out.println("Underflow");
			return;
		}
		headTop = headTop.next;
	}
	
	int top() {
		if(headTop == null) {
			System.out.println("empty");
			return -1;
		}
		return headTop.data;
	}
	
	void print() {
		Node temp = headTop;
		System.out.print("Stack : ");
		while(temp != null) {
			System.out.print(temp.data+" ");
			temp = temp.next;
		}
		System.out.println("");
	}
	
}

public class Stack_impl_using_LinkedList {
	public static void main(String[] args) {
		StackLL obj = new StackLL();
		obj.push(1);obj.print();
		obj.push(2);obj.print();
		obj.push(21);obj.print();
		obj.pop();obj.print();
		obj.pop();obj.print();
		obj.pop();obj.print();
		obj.pop();obj.print();
		
		
	}
}
