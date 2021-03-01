package ds_10th_dec;


//DS
class Node2{
	int data;
	Node2 next;
	
	public Node2(int v){
		data = v;
	}
}

//use of DS
class LinkedL2{
	Node2 head;
	
	public void insertAtLast(int v) {//
		//1. 1st element
		Node2 nn = new Node2(v);
		if(head == null) {
			head = nn;
		}else {//2. already elem present
			Node2 t = head;
			while(t.next != null)
				t = t.next;
			t.next = nn;
		}
	}
	
	public void insertAtFirst(int v) {
		//1. 1st elem
		Node2 nn = new Node2(v);
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
			Node2 t = head;
			Node2 x = t;
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
			Node2 t = head;
			head = t.next;
		}
	}
	
	public void print() {
		Node2 t = head;
		System.out.print("LL : ");
		while(t!=null) {
			System.out.print(t.data+" ");
			t = t.next;
		}
		System.out.println();
	}
	
	
	public void reverseLL() {// 2 pointer question
		Node2 t2 = head;
		Node2 t1 = null, next;
		
		while(t2 != null) {
			next = t2.next;
			t2.next = t1;
			t1 = t2;
			t2 = next;
		}
		head = t1;
	}
}


public class LinkedListReverseImpl{
	public static void main(String[] args) {
		LinkedL2 obj = new LinkedL2();
		obj.insertAtFirst(12); obj.print();
		obj.insertAtFirst(11); obj.print();
		obj.insertAtFirst(10); obj.print();
		obj.insertAtFirst(1); obj.print();
		
		obj.reverseLL();obj.print();
		
	}	
}
/**
 * 
LL : 12 
LL : 11 12 
LL : 10 11 12 
LL : 1 10 11 12 
LL : 12 11 10 1 
 */
