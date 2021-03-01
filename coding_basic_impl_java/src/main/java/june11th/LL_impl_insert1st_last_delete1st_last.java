package june11th;

class Node{
	int d;
	Node next;
	public Node(int d) {
		super();
		this.d = d;
	}
}

class LinkedList {
	Node h;
	
	public void insert1st(int data) {
		Node nn = new Node(data);
		if(h == null) {
			h = nn;
			return;
		}
		nn.next = h;
		h = nn;
	}
	
	public void printLL() {
		Node t = h;
		System.out.print("LL : ");
		while(t != null) {
			System.out.print(t.d+" ");
			t = t.next;
		}
		System.out.println();
	}

	public void insertLast(int i) {
		Node nn = new Node(i);
		if(h == null) {
			h = nn;
			return;
		}
		Node t = h;
		while(t.next != null) {
			t = t.next;
		}
		t.next = nn;
	}

	public void delete1st() {
		if(h == null || h.next == null) {
			h = null;
			return;
		}
		h = h.next;
	}

	public void deleteLast() {
		if(h == null || h.next == null) {
			h = null;
			return;
		}
		Node t = h;
		while(t.next != null) {
			System.out.println(t.d);
			t = t.next;
		}
		t = null;
	}
	
	public void reverse() {
		h = reverseLL();
	}
	
	private Node reverseLL() {
		
		Node it = h;//4 //iterator/traversal pointer
		Node pre = null; //result pointer
		Node temp = null; //helper pointer
		
		while(it != null) {
			temp = it.next;
			
			it.next = pre;//1
			pre	= it;//2
			
			it = temp;//3
		}
		return pre;
	}

	public void reverseKgroup_(int k) {
		h = reverseK(h, k);
	}
	//old logic --> check traversal pointer
	private Node reverseK(Node head, int k) {
		//null check or base case
		if(head == null || k == 0) {
			return head;
		}
		
		Node it = head;
		Node pre = null;
		Node temp = null;
		int i = 0;
		while(i < k && it != null) {
			temp = it.next;
			
			it.next = pre;
			pre = it;
			
			it = temp;
			i++;
		}
		if(it != null) {
			head.next = reverseK(it, k);//check for recursion input argument head not original head
		}
		return pre;
	}
	
}

public class LL_impl_insert1st_last_delete1st_last {
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.printLL();
		list.insert1st(1);
		list.printLL();
		list.insert1st(2);
		list.insert1st(3);
		list.insert1st(4);
		list.printLL();
		list.insertLast(5);
		list.insertLast(6);
		list.printLL();
		
		list.delete1st();
		list.delete1st();
		list.printLL();
		
		list.deleteLast();
		list.printLL();
		
		list.reverse();
		list.printLL();
		
		list.insertLast(10);
		list.insertLast(11);
		list.insertLast(12);
		list.printLL();
		
		//list.reverse();
		list.printLL();
		
		list.reverseKgroup_(2);
		//list.reverseKGroup(2);
		list.printLL();

	}
}
