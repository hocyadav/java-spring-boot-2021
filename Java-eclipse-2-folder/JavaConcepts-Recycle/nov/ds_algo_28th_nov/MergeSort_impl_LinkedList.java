package ds_algo_28th_nov;

/**
 * 
 * @author Hariom Yadav | 28-Nov-2019
 *
 */

//declare - data structure for LL
class Node{
	int data;
	Node next;
	
	public Node(int v) {
		data = v;
	}
}

//Use -  data structure 
class Linkedlist{
	Node head;
	
	public void insert(int key) {
		Node nn = new Node(key);
		if(head == null) {
			head = nn;
		}else {
			Node t = head;
			while(t.next != null)
				t = t.next;
			t.next = nn;
		}
	}
	
	public void print() {
		Node t = head;
		while(t != null) {
			System.out.print(t.data+" ");
			t = t.next;
		}
		System.out.println();
	}
}


public class MergeSort_impl_LinkedList {

	public static void main(String[] args) {
		Linkedlist obj = new Linkedlist();

		//add data into list
		obj.insert(12); obj.insert(23); obj.insert(1); obj.insert(5);
		obj.insert(-1); obj.insert(90); obj.insert(10); obj.insert(0);
		
		System.out.print("Input List  : ");
		obj.print();
		
		obj.head = mergeSort(obj.head);//sort and update head 
		
		System.out.print("Sorted List : ");
		obj.print();
	}

	private static Node mergeSort(Node head) {
		if(head == null || head.next == null)//base case : 0 node / 1 node 
			return head;
		else {//more than 1 node (Logically sorting start from more than 1 node)
			//1 mid
			Node mid = getMid_by_SlowFastPointer(head);
			Node firstHalf = head;
			Node secHalf = mid.next;
			mid.next = null;
			
			//2 recursion for 2 half
			Node left 	= mergeSort(firstHalf);
			Node right	= mergeSort(secHalf);
			
			//3 sort 2 half
			Node sorted = sortTwoSortedLL(left, right);
			
			//return sorted list
			return sorted;
		}
	}

	/**
	 * Get mid node of a linked list
	 * @param head
	 * @return
	 */
	private static Node getMid_by_SlowFastPointer(Node head) {//head has more than 1 data, i.e. 2,3,4..
		//if(head == null) return null;//not required since already check present in base case
		if(head == null) 
			return null;
		else {
			Node slow = head, fast = head;
			
			while(fast.next != null && fast.next.next != null) {
				slow = slow.next;
				fast = fast.next.next;
			}
			return slow;
		}
	}
	/**
	 * Sort 2 sorted LL : 
	 * input 2 sorted LL, output : merge them + sort
	 *
	 * @param firstHalf
	 * @param secHalf
	 * @return
	 */
	private static Node sortTwoSortedLL(Node firstHalf, Node secHalf) {
		Node result = null;
		
		//base case : any one of list is null
		if(firstHalf == null)
			return secHalf;
		if(secHalf == null)
			return firstHalf;
		//else condition - both list contain data then copy data in result list
		if(firstHalf.data < secHalf.data) {
			result = firstHalf;
			result.next = sortTwoSortedLL(firstHalf.next, secHalf);
		}else {
			result = secHalf;
			result.next = sortTwoSortedLL(firstHalf, secHalf.next);
		}
		return result;
	}
}
/**
 * 
Input List  : 12 23 1 5 -1 90 10 0 
Sorted List : -1 0 1 5 10 12 23 90 
 * 
 */
