package ds_4th_Oct;
/**
 * 
 * @author Hariom Yadav - Nov 4, 2019
 *
 */
//DS
class Node{
	//know
	int data;
	Node next;
	
	//does
	Node(int v){
		data = v;
	}
}

//use of DS
class LinkedList{
	//know
	Node head;
	
	//does
	void insert_at_end(int v){//
		//new node
		Node nn = new Node(v);
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
	void delete_at_end() {
		if(head == null) {
			System.out.println("LL empty : ");
			return;
		}else {
			Node temp = head;
			if(temp.next == null) {
				head = null;
				return;
			}
			while(temp.next.next != null) {
				temp = temp.next;
			}
			temp.next = null;
		}
	}
	
	void delete_at_start() {
		if(head == null) {
			System.out.println("empty");
			return;
		}else {
			head = head.next;
		}
	}
	
	void print() {
		Node temp = head;
		System.out.print("LInked list : ");
		while(temp != null) {
			System.out.print(temp.data+" ");
			temp = temp.next;
		}
		System.out.println("");
	}
}

public class LinkedList_impl {
	public static void main(String[] arg) {
		LinkedList obj = new LinkedList();
		obj.insert_at_end(12);obj.print();
		obj.insert_at_end(13);obj.print();
		obj.insert_at_end(14);obj.print();
		obj.delete_at_end();obj.print();
		obj.delete_at_end();obj.print();
		obj.delete_at_end();obj.print();
		obj.delete_at_end();obj.print();
		obj.delete_at_end();obj.print();
		
		obj.insert_at_end(12);obj.print();
		obj.insert_at_end(13);obj.print();
		obj.insert_at_end(14);obj.print();
		obj.delete_at_start();obj.print();
		obj.delete_at_start();obj.print();
		obj.delete_at_start();obj.print();
		obj.delete_at_start();obj.print();
		
	}
}
