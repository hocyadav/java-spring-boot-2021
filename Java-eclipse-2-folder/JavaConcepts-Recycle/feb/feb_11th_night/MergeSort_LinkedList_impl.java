package feb_11th_night;
/**
 * 
 * @author Hariom Yadav | 11-Feb-2020
 *
 */

class Node {
	int data;
	Node next;
	public Node(int d) {
		data = d;
	}
}

class LinkedList1 {
	Node head;
	
	public void insert(int k) {
		Node nn = new Node(k);
		if(head == null) {
			head = nn;
			return;
		}
		Node t = head;
		while(t.next != null) {
			t = t.next;
		}
		t.next = nn;
	}
	
	public void print() {
		Node t = head;
		System.out.print("List : ");
		while(t != null) {
			System.out.print(t.data + " ");
			t = t.next;
		}
		System.out.println();
	}
	
	
}

public class MergeSort_LinkedList_impl {
	public static void main(String[] args) {
		LinkedList1 obj = new LinkedList1();
		obj.print();
		obj.insert(12); obj.print();
		obj.insert(11); obj.print();
		obj.insert(10); obj.print();
		obj.insert(1); obj.print();
		obj.insert(100); obj.print();
		obj.insert(-10); obj.print();
		
		obj.head = mergeSort(obj.head);
		obj.print();
		
	}

	private static Node mergeSort(Node head) {
		if(head == null || head.next == null) {//sorting start from 2 element
			return head;
		}
		
		Node mid 		= middleNode(head);
		Node firstHalf 	= head;
		Node secondHalf = mid.next;
		mid.next 		= null;
		
		Node leftList = mergeSort(firstHalf);
		Node rightList = mergeSort(secondHalf);
		
		Node sorted  	= sort2LinkedList(leftList, rightList);
		
		return sorted;
	}

	private static Node sort2LinkedList(Node leftList, Node rightList) {
		Node result = null;
		
		if(leftList == null) {
			return rightList;
		}
		if(rightList == null) {
			return leftList;
		}
		
		if(leftList.data < rightList.data) {
			result = leftList;
			result.next = sort2LinkedList(leftList.next, rightList);
		}else {
			result = rightList;
			result.next = sort2LinkedList(leftList, rightList.next);
		}
		
		return result;
	}

	private static Node middleNode(Node head) {
		if(head == null) {
			return null;
		}
		Node slow = head, fast = head;
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
}
