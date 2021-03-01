package ds_12th_dec;
/**
 * 
 * @author Hariom Yadav | 12-Dec-2019
 *
 */
class Queue{
	int queue[];
	int qsize;
	int front, rear;
	public Queue(int qsize) {
		super();
		this.qsize = qsize;
		front = 0;
		rear = 0;
		queue = new int[this.qsize];
	}
	
	
	//enqueue
	public void enQ(int v) {//front - out, rear - in
		if(rear == qsize) {//1st data insert
			System.out.println("Overflow");
			return;
		}else
			queue[rear++] = v;
		
	}
	
	public void deQ() {//out from front : move 1 left
		if(front == rear) {
			System.out.println("Underflow - empty Q");
			return;
		}else {
			for(int i=front; i<rear-1; i++) {
				queue[i] = queue[i+1];
			}
			rear--;
		}
	}
	
	public void print() {
		System.out.print("Queue : ");
		for(int i=front; i<rear; i++) {
			System.out.print(queue[i]+" ");
		}
		System.out.println();
	}
	
}


public class Queue_impl_Array {
	public static void main(String[] args) {
		Queue obj = new Queue(4);
		obj.deQ(); obj.print();
		
		obj.enQ(12); obj.print();
		obj.enQ(13); obj.print();
		obj.enQ(1); obj.print();
		obj.enQ(3); obj.print();
		obj.enQ(13); obj.print();
		
		obj.deQ(); obj.print();
		obj.deQ(); obj.print();
		obj.deQ(); obj.print();
		obj.deQ(); obj.print();
		obj.deQ(); obj.print();
	}
}

/**
Queue : 12 13 1 3 
Overflow
Queue : 12 13 1 3 
Queue : 13 1 3 
Queue : 1 3 
Queue : 3 
Queue : 
Underflow - empty Q
Queue : 


*/