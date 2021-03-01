package linked_list_que_23rd_nov;
/**
 * 
 * @author Hariom Yadav - Nov 24, 2019
 *
 */

//Data structure
class Node{
	int data;
	Node next;
	public Node(int v) {
		data = v;
	}
}

//Use of Data Structure
class Linked_List{
	Node head;
	
	/**
	 * Insert at last position
	 * @param v
	 */
	public void insertLast(int v) {
		Node nn = new Node(v);
		if(head == null) {
			head = nn;
		}else {
			Node t = head;
			while(t.next != null)
				t = t.next;
			t.next = nn;
		}
	}
	
	/**
	 * Print LL
	 */
	public void print() {
		Node t = head;
		System.out.print("LL : ");
		while(t != null) {
			System.out.print(t.data+" ");
			t = t.next;
		}
		System.out.println();
	}
	
	/**
	 * Delete node from LL
	 * @param node
	 */
	public void delete_a_Node_no_head_ponter_given(Node node) {//1,2,3,4,5 (delete node 3)
		Node temp = node.next;//temp contain 4
		
		node.next = temp.next;//new connection : ---2->4--- (old connection : ---2->3->4---)
		node.data = temp.data;//copy data  : 3rd node data into ==> 2nd node data
	}
	
}
//Test Data Structure
public class LinkedList_DeleteNode_NoHeadPointerGiven {
	public static void main(String[] args) {
		Linked_List obj = new Linked_List();
		obj.insertLast(1); obj.print();
		obj.insertLast(2); obj.print();
		obj.insertLast(3); obj.print();
		obj.insertLast(4); obj.print();
		obj.insertLast(5); obj.print();
		Node del = obj.head.next;
		System.out.println("delete this node : "+del.data);
		obj.delete_a_Node_no_head_ponter_given(del); obj.print();
	}
}
