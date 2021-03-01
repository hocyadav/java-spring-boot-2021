package circularLL_31th_dec;
/**
 * 
 * @author Hariom Yadav | 31-Dec-2019
 *
 */
class QueueA{
	int qsize;
	int[] queue;
	int front, rear;
	
	public QueueA(int qsize) {
		super();
		this.qsize = qsize;
		queue = new int[this.qsize];
		front = rear = 0;
	}
	
	public void enQ(int k) {//rear++ , add
		if(rear == this.qsize - 1) {
			System.out.println("Overflow..");
			return;
		}
		queue[rear++] = k;
	}
	
	public void deQ() {//shift 1 left side
		if(front == rear) {
			System.out.println("Underflow..");
			return;
		}
		for(int i=front; i<=rear-1; i++)
			queue[i] = queue[i+1];
		//queue[rear] = 0;//optional step
		rear--;
	}
	
	public boolean isEmpty() {
		return (front == rear) ? true: false;
	}
	
	public void print() {
		System.out.print("Queue : ");
		for(int i=0; i<rear; i++)
			System.out.print(queue[i]+" ");
		System.out.println();
	}
	
}


public class Queue_array_impl {
	public static void main(String[] args) {
		QueueA obj = new QueueA(4);
		obj.print();
		obj.deQ();
		obj.enQ(1); obj.print();
		obj.enQ(12); obj.print();
		obj.enQ(13); obj.print();
		obj.enQ(14); obj.print();
		obj.enQ(15); obj.print();
		obj.deQ(); obj.print();
		obj.deQ(); obj.print();
		obj.deQ(); obj.print();
	}
}
/**

Queue : 
Queue : 1 
Queue : 1 12 
Queue : 1 12 13 
Overflow..
Queue : 1 12 13 
Overflow..
Queue : 1 12 13 
Queue : 12 13 
Queue : 13 
Queue : 


*/