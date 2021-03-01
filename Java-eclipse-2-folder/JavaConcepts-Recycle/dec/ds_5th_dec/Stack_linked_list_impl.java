package ds_5th_dec;

/**
 * 
 * @author Hariom Yadav | 05-Dec-2019
 *
 */

//ds
class Node{
	int data;
	Node next;
	
	public Node(int d){
		data =d;
	}
}

//use of DS
class StackLL{
	Node head;
	
	public void push(int v) {//insert at first node LL
		Node nn = new Node(v);
		if(head == null) {
			head = nn;
		}else {
			nn.next = head;
			head = nn;
		}
	}
	
	public void pop() {//head -> head.next
		if(head == null) {
			System.out.println("Underflow");
			return;
		}
		head = head.next;
	}
	
	public boolean isEmpty() {
		return (head == null) ? true : false;
	}
	
	public void print() {
		Node t = head;
		System.out.print("Stack : ");
		while(t != null) {
			System.out.print(t.data+" ");
			t = t.next;
		}
		System.out.println();
	}
	
}

public class Stack_linked_list_impl {
	public static void main(String[] args) {
		StackLL obj = new StackLL();
		
		System.out.println(obj.isEmpty());
		obj.pop(); obj.print();
		
		obj.push(12); obj.print();
		obj.push(13); obj.print();
		obj.push(16); obj.print();
		obj.push(1); obj.print();
		
		obj.pop(); obj.print();
		obj.pop(); obj.print();
		obj.pop(); obj.print();
		obj.pop(); obj.print();
		obj.pop(); obj.print();
	}
}
/**
 * 
true
Underflow
Stack : 
Stack : 12 
Stack : 13 12 
Stack : 16 13 12 
Stack : 1 16 13 12 
Stack : 16 13 12 
Stack : 13 12 
Stack : 12 
Stack : 
Underflow
Stack : 

 */
