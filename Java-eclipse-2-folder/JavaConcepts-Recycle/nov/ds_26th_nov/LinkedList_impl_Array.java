package ds_26th_nov;

/**
 * 
 * @author Hariom Yadav
 *
 */

class Node{
	int data;
	Node next;
	
	public Node(int v) {
		data = v;
	}
}

class LLimpl{
	Node head;
	
	public void insertAtFirstPlace(int v) {
		Node nn = new Node(v);
		if(head == null) {
			head = nn;
		}else {
			nn.next = head;
			head = nn;
		}
	}
	
	public void insertAtLastPlace(int v) {
		Node nn = new Node(v);
		if(head == null) {
			head = nn;
		}else {
			Node t = head;
			while(t.next != null) {
				t = t.next;
			}
			t.next = nn;
		}
	}
	
	public void print() {
		Node t = head;
		System.out.print("LL : ");
		while(t != null) {
			System.out.print(t.data+" ");
			t = t.next;
		}
		System.out.println();
		
	}
	
	public void deleteLast() {
		if(head == null) {
			System.out.println("LL empty.");
			return;
		}else {
			Node t = head;
			while(t.next.next != null) {
				t = t.next;
			}
			t.next = null;
		}
		print();
	}
	
	public void deleteFirst() {
		if(head == null) {
			System.out.println("LL empty");
			return;
		}else {
			head = head.next;
		}
	}
	
}

public class LinkedList_impl_Array {
	public static void main(String[] args) {
		LLimpl obj = new LLimpl();
		obj.insertAtFirstPlace(1); obj.print();
		obj.insertAtFirstPlace(2); obj.print();
		obj.insertAtFirstPlace(3); obj.print();
		obj.insertAtLastPlace(4); obj.print();
		obj.insertAtLastPlace(5); obj.print();
		obj.insertAtLastPlace(6); obj.print();
		
		obj.deleteFirst(); obj.print();
		obj.deleteLast(); 
		obj.deleteLast(); 
		
		
	}
}
