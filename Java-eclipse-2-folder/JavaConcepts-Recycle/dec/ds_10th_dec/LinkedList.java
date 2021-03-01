package ds_10th_dec;
/**
 * 
 * @author Hariom Yadav | 10-Dec-2019
 *
 */

//DS
class Node{
	int data;
	Node next;
	
	public Node(int v){
		data = v;
	}
}

//use of DS
class LinkedL{
	Node head;
	
	public void insertAtLast(int v) {//
		//1. 1st element
		Node nn = new Node(v);
		if(head == null) {
			head = nn;
		}else {//2. already elem present
			Node t = head;
			while(t.next != null)
				t = t.next;
			t.next = nn;
		}
	}
	
	public void insertAtFirst(int v) {
		//1. 1st elem
		Node nn = new Node(v);
		if(head == null)
			head = nn;
		else {
			nn.next = head;
			head = nn;
		}
	}
	
	public void deleteLast() {
		//no element
		if(head == null || head.next == null) {
			head = null;
			return;
			
		}
		else {
			Node t = head;
			Node x = t;
			while(t.next != null) {
				x = t;
				t = t.next;
			}
			x.next = null;
		}
	}
	
	public void deleteFirst() {
		//no element
		if(head == null)
			return;
		else {
			Node t = head;
			head = t.next;
		}
	}
	
	public void print() {
		Node t = head;
		System.out.print("LL : ");
		while(t!=null) {
			System.out.print(t.data+" ");
			t = t.next;
		}
		System.out.println();
	}
	
	public void deleteNode(Node del) {
		Node t = del;
		del.data = t.data;
		del.next = t.next;
	}
	
	
}


public class LinkedList {
	public static void main(String[] args) {
		LinkedL obj = new LinkedL();
		obj.insertAtFirst(12); obj.print();
		obj.insertAtFirst(13); obj.print();
		
		obj.insertAtLast(14); obj.print();
		obj.insertAtLast(16); obj.print();
		obj.insertAtLast(18); obj.print();
		obj.insertAtLast(20); obj.print();
		//obj.deleteNode(del);
		
		obj.deleteFirst(); obj.print();
		obj.deleteLast(); obj.print();
		obj.deleteLast(); obj.print();
		obj.deleteLast(); obj.print();
		obj.deleteFirst(); obj.print();
		obj.deleteFirst(); obj.print();
		
	}
}

/**
LL : 12 
LL : 13 12 
LL : 13 12 14 
LL : 13 12 14 16 
LL : 13 12 14 16 18 
LL : 13 12 14 16 18 20 
LL : 12 14 16 18 20 
LL : 12 14 16 18 
LL : 12 14 16 
LL : 12 14 
LL : 14 
LL : 
*/