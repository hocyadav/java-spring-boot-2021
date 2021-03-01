package datastructure_2nd_Oct;
/**
 * 
 * @author Hariom Yadav - Nov 2, 2019
 *
 */
//ds
class Node{
	//know something
	int data;
	Node next;
	//does something
	Node(int v){//memory allocate, 
		data = v;
	}
}

class LinkedList{
	//know something
	Node head;
	
	//does something
	void insert(LinkedList list, int v){
		//create new node
		
		Node newN = new Node(v);
		
		if(head == null) {
			list.head = newN;
		}else {
			Node temp = list.head;
			while(temp.next!=null) temp = temp.next;
			temp.next = newN;
		}
	}
	
	void printLinkList(LinkedList list) {
		Node temp = list.head;
		System.out.print("List : ");
		while(temp!=null) {//traverse all
			System.out.print(temp.data+" ");
			temp = temp.next;
		}
		System.out.println("");
	}
}



public class LinkedList_2ndoct {
	public static void main(String[] args) {
		LinkedList obj = new LinkedList();
		obj.printLinkList(obj);
		obj.insert(obj, 12);obj.printLinkList(obj);
		obj.insert(obj, 12);obj.printLinkList(obj);
	}
}
