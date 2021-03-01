package feb_13th;
/**
 * 
 * @author Hariom Yadav | 13-Feb-2020
 *
 */
class Node {
	int data;
	Node next;
	public Node(int d) {
		data = d;
	}
}

class LinkedL {
	Node head;
	
	public void addLast(int d) {
		Node nn = new Node(d);
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
			System.out.print(t.data+" ");
			t = t.next;
		}
		System.out.println();
	}

	public void reverseLL() {//2 pointer
		Node p2 = head;//for traverse
		Node temp = null;//for helping traversal
		Node p1 = null;
		while(p2 != null) {
			temp = p2.next;//helping traversal , next time p2 will jump to this place only
			
			p2.next = p1;
			p1 = p2;
			
			p2 = temp;
		}
		head = p1;
	}

	public void reverseLL_K_Group(int k) {
		head = _reverseLLGroup(head, k);
	}
	
	public void reverseLL_K_Group2(int k) {
		head = reverseKGroup(head, k);
	}

	private Node _reverseLLGroup(Node head, int k) {//2 pointer : same as sinle ll reverse
		Node p2 = head;//for traversal
		Node p1 = null;
		Node temp = null;
		int count = 0;
		
		while(p2 != null && count < k) {
			temp = p2.next;
			
			p2.next = p1;//add p2 before p1
			p1 = p2;
			
			p2= temp;
			count++;
		}
		if(p2 != null) {
			head.next = _reverseLLGroup(p2, k);
		}
		return p1;
	}
	
	
	public Node reverseKGroup(Node head, int k) {//2 pointer + recursion
		Node p2 = head;//pointer 1 for traversal
		Node p1 = null;
		Node helper = null;
        int count = 0;
        
        while(p2 != null && count < k ) {//traversal
            helper = p2.next;
            
            p2.next = p1;//add p2 before p1
            p1 = p2;
            
            p2 = helper; //i++
            count++;
        }
        
        if(p2 != null) {//p2 is start of next list, so checking for not null
            //head.next is last node of before list, so update it
            head.next = reverseKGroup(p2, k);
            
        }
        
        return p1;
    }
}

public class LL_reverse_try {
	public static void main(String[] args) {
		LinkedL obj = new LinkedL();
		obj.print();
		obj.addLast(12); obj.print();
		obj.addLast(13); obj.print();
		obj.addLast(14); obj.print();
		obj.addLast(15); obj.print();
		obj.addLast(16); obj.print();
		//System.out.println("reverse ");
		//obj.reverseLL(); obj.print();
		System.out.println("reverse in k group");
		//obj.reverseLL_K_Group(3); obj.print();
		obj.reverseLL_K_Group2(2); obj.print();
		
		
	}
}
/**
 List : 
List : 12 
List : 12 13 
List : 12 13 14 
List : 12 13 14 15 
reverse 
List : 15 14 13 12 
reverse in k group
List : 14 15 12 13 
 
 
 */
