package feb_2nd_ds;
/**
 * 
 * @author Hariom Yadav | 02-Feb-2020
 *
 */

class Node {
	int data;
	Node next;
	public Node(int data) {
		super();
		this.data = data;
	}
}

class CLL {
	Node last;
	
	public void insertAtFirst(int k) {
		Node nn = new Node(k);
		if(last == null) {
			last = nn;
			last.next = nn;
			return;
		}
		nn.next = last.next;
		last.next = nn;
	}
	
	public void insertAtLast(int k) {
		Node nn = new Node(k);
		if(last == null) {
			last = nn;
			last.next = nn;
			return;
		}
		nn.next = last.next;
		last.next = nn;
		last = nn;
	}
	
	public void print() {
		Node t = last.next;
		do {
			System.out.print(t.data+" ");
			t = t.next;
		} while(t != last.next);//same as initial t value but not equal
		
		System.out.println();
	}
}


public class CirculareLL {
	public static void main(String[] args) {
		CLL cll = new CLL();
		cll.insertAtFirst(10); cll.print();
		cll.insertAtFirst(12); cll.print();
		cll.insertAtFirst(15); cll.print();
		cll.insertAtLast(9); cll.print();
		cll.insertAtLast(90); cll.print();
		cll.insertAtFirst(119); cll.print();
	}

}
/**
10 
12 10 
15 12 10 
15 12 10 9 
15 12 10 9 90 
119 15 12 10 9 90 

*/