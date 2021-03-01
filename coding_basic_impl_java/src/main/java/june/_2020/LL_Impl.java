package june._2020;

/**
 * DS
 */
class Node{
	int data;
	Node next;
	public Node(int data) {
		this.data = data;
		this.next = null;//non primitive : null assign by compiler
	}
}

/**
 * Opeartion on DS
 */
class SinglyLL {
	Node head;

	/**
	 * 1.new node obj -> 2.check for 1st element OR element already present -> (move head to 1st place)
	 */
	public void insertNodeAtFirst(int data) {
		Node newNode = new Node(data);
		if(isListEmpty()) {
			head = newNode; 
			return;
		}
		newNode.next = head;
		head = newNode;
	}

	/**
	 * 1. new node obj -> check for 1st element / not -> iterate and place in last
	 */
	public void insertNodeAtLast(int data) {
		Node node = new Node(data);
		if(isListEmpty()) {
			head = node; 
			return;
		}
		
		Node it = head;
		while(it.next != null) {
			it = it.next;
		}
		it.next = node;
	}
	
	public void deleteFirst() {
		if(head == null) return;
		
		Node temp = head;
		head = temp.next;
	}
	
	public void deleteLast() {
		if(head == null) return;
		
		Node it = head;
		Node pre = null;
		while(it.next != null) {
			pre = it;
			it = it.next;
		}
		pre.next = null;
	}
	
	
	public void printLL() {
		Node it = head;
		System.out.print("Singly LL : ");
		while(it != null) {
			System.out.print(it.data + " ");
			it = it.next;
		}
		System.out.println();
	}
	
	private boolean isListEmpty() {
		return head == null ? true : false;
	}
}

/**
 *Testing DS
 */
public class LL_Impl {
	public static void main(String[] args) {
		SinglyLL singlyLL = new SinglyLL();
		singlyLL.insertNodeAtFirst(10);
		singlyLL.printLL();
		singlyLL.insertNodeAtFirst(20);
		singlyLL.printLL();
		
		singlyLL.insertNodeAtLast(40);
		singlyLL.printLL();
		
		singlyLL.deleteFirst();
		singlyLL.printLL();
		
		singlyLL.deleteLast();
		singlyLL.printLL();
		
	}
}
