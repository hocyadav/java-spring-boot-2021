package ds_3rd_Nov;
/**
 * 
 * @author Hariom Yadav - Nov 3, 2019
 *
 */
class Node{
	//know something
	String data;
	Node next;
	//does something
	Node(String s){
		data = s;
	}
}

class LinkedList{
	//know something
	Node head;
	//does something
	void insert(String str) {
		//new node
		Node nn = new Node(str);
		if(head == null) {
			head = nn;
		}else {
			Node temp = head;
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = nn;
		}
		
	}
	
	void print() {
		Node temp = head;
		System.out.print("linked list : ");
		while(temp!=null) {
			System.out.print(temp.data+" ");
			temp = temp.next;
		}
		System.out.println("");
	}
}



public class LinkedListMain {
	public static void main(String[] args) {
		LinkedList obj = new LinkedList();
		obj.insert("a");obj.print();
		obj.insert("b");obj.print();
	}
}
