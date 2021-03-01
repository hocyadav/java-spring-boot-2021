package feb_12th_office;
/**
 * 
 * @author Hariom Yadav | 12-Feb-2020
 * reverse LL : 2 pointer question
 */
class Node {
	int data;
	Node next;
	public Node(int d) {
		data = d;
	}
}

class LinkedList {
	Node head;
	
	public void add1stPlace(int k) {
		Node nn = new Node(k);
		if(head  == null) {
			head = nn;
			return;
		}
		nn.next = head;
		head = nn;
	}
	
	public void print() {
		Node t = head;
		System.out.print("LL : ");
		while(t != null) {
			System.out.print(t.data +" ");
			t = t.next;
		}
		System.out.println();
	}
	
	public void print2(Node head) {
		Node t = head;
		System.out.print("LL : ");
		while(t != null) {
			System.out.print(t.data +" ");
			t = t.next;
		}
		System.out.println();
	}
	
	public void reverse() {//2 pointer solution
		Node t2 = head;
		Node t1 = null;
		
		Node temp = null;
		
		while(t2 != null) {// traverse 2nd pointer , i.e. right pointer
			temp = t2.next;
			
			t2.next = t1;//this is main
			t1 = t2;// this is main
			
			t2 = temp;
			
		}
		
		head = t1;
	}
	
	public void reverseGroup(int k) {
		head = rec_reverse(head, k);
	}

	private Node rec_reverse(Node head, int k) {
		//print2(head);
		Node t2 = head;
		Node t1 = null;
		Node temp = null;
		int count = 0;
		System.out.print("before while : ");
		print2(head);
		while(count < k && t2 != null) {
			temp = t2.next;
			
			t2.next = t1;
			t1 = t2;
			
			t2 = temp;
			
			count++;
		}
		System.out.print("after while : ");
		print2(head);
		//print();
		if(t2 != null) {
			System.out.println("t2 (next k 1st node): "+t2.data+" - head (previous k 1st node) : "+head.data);
			//print2(head);
			Node tt = rec_reverse(t2, k);
			System.out.print("--head.next ");
			print2(tt);
			head.next = tt;
		}
		System.out.print("t1 : ");
		print2(t1);
		return t1;
	}
}

public class ReverseLL_2p_que_GroupK {
	public static void main(String[] args) {
		LinkedList obj = new LinkedList();
		//obj.print();
		obj.add1stPlace(1);//obj.print();
		obj.add1stPlace(3);//obj.print();
		obj.add1stPlace(5);//obj.print();
		obj.add1stPlace(10);//obj.print();
		obj.add1stPlace(500);//obj.print();
		obj.add1stPlace(88);//obj.print();
		obj.add1stPlace(9);obj.print();
//		obj.reverse();obj.print();
		obj.reverseGroup(3); obj.print();
	}
}
/**
LL : 
LL : 1 
LL : 3 1 
LL : 5 3 1 
LL : 10 5 3 1 
LL : 500 10 5 3 1 
LL : 1 3 5 10 500  
 */
