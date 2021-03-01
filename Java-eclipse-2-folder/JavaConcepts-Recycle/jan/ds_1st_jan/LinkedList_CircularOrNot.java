package ds_1st_jan;
/**
 * 
 * @author Hariom Yadav | 01-Jan-2020
 *
 */
class LinkedList{
	Node head;
	
	public void isCLL() {
		if(head == null) {
			System.out.println("Circular..");
			return;
		}
		Node t = head.next;
		while(t != head && t != null) {
			t = t.next;
		}
		if(t == head)
			System.out.println("Circular..");
		else
			System.out.println("Not Circular..");
	}
}

public class LinkedList_CircularOrNot {
	public static void main(String[] args) {
		LinkedList obj 		= new LinkedList();
		obj.head 			= new Node(10);
		obj.head.next 		= new Node(20);
		obj.head.next.next 	= new Node(30);
		obj.isCLL();
		
		obj.head.next.next.next = obj.head;
		obj.isCLL();
	}
}
/**
Not Circular..
Circular..
*/
