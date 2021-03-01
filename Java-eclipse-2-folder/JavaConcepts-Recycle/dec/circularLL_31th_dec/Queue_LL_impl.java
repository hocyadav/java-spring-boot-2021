package circularLL_31th_dec;
/**
 * 
 * @author Hariom Yadav | 31-Dec-2019
 *
 */
class NodeQ{
	int data;
	NodeQ next;//this is fear
	NodeQ rear;
	
	public NodeQ(int d) {
		data = d;
	}
}

class QueueL{
	NodeQ head;
	
	public void enQ(int k) {
		insert_in_LL_Last(k);
	}

	private void insert_in_LL_Last(int k) {
		NodeQ nn = new NodeQ(k);
		if(head == null) {
			head = nn;
			return;
		}
		NodeQ t = head;
		while(t.next != null)
			t = t.next;
		t.next = nn;
	}
	
	
	public void deQ() {
		delete_LL_first();
	}

	private void delete_LL_first() {
		if(head == null) {
			System.out.println("Underflow..");
			return;
		}
		head = head.next;
	}
	
	public boolean isEmpty() {
		return (head == null) ? true: false;
	}
	
	public void print() {
		NodeQ t = head;
		System.out.print("Queue : ");
		while(t!= null) {
			System.out.print(t.data +" ");
			t = t.next;
		}
		System.out.println();
	}
	
	
}


public class Queue_LL_impl {
	public static void main(String[] args) {
		QueueL obj = new QueueL();
		obj.print();
		obj.deQ();
		obj.enQ(1); obj.print();
		obj.enQ(12); obj.print();
		obj.enQ(13); obj.print();
		//obj.enQ(14); obj.print();
		//obj.enQ(15); obj.print();
		obj.deQ(); obj.print();
		obj.deQ(); obj.print();
		obj.deQ(); obj.print();
	}
}
/**

Queue : 
Underflow..
Queue : 1 
Queue : 1 12 
Queue : 1 12 13 
Queue : 12 13 
Queue : 13 
Queue : 

 */
