package ds_1st_jan;
/**
 * 
 * @author Hariom Yadav | 01-Jan-2020
 *
 */
class Node{
	int data;
	Node next;
	public Node(int data) {
		super();
		this.data = data;
	}
}

class QueueCLL{
	Node front, rear;
	
	public void enQ(int k) {
		Node nn = new Node(k);
		if(front == null && rear == null) {
			front = rear = nn;
			rear.next = front;
			return;
		}
		nn.next = front;//1st connection
		rear.next = nn;
		rear = nn;	//2nd connection
	}
	
	public void deQ() {
		if(front == rear) {
			front = rear = null;
			return;
		}
		front = front.next;//1st connection
		rear.next = front;//2nd connection
	}
	
	public void print() {
		System.out.print("Queue CLL : ");
		if(front == null) {
			System.out.println();
			return;
		}
		Node t = front;
		do {
			System.out.print(t.data+" ");
			t = t.next;
		}while(t != front);
		System.out.println();
	}
}

public class Queue_CircularLL_impl {
	public static void main(String[] args) {
		QueueCLL obj = new QueueCLL();
		obj.print();
		obj.enQ(12); obj.print();
		obj.enQ(13); obj.print();
		obj.enQ(14); obj.print();
		obj.deQ(); obj.print();
		obj.deQ(); obj.print();
		obj.deQ(); obj.print();
	}
}
/**

Queue CLL : 
Queue CLL : 12 
Queue CLL : 12 13 
Queue CLL : 12 13 14 
Queue CLL : 13 14 
Queue CLL : 14 
Queue CLL : 

*/