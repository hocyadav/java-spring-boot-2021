package june13th;

class Queue {
	int front;
	int rear;
	int size;
	int[] arr;
	public Queue(int size) {
		super();
		this.front = 0;
		this.rear = 0;
		this.size = size;
		this.arr = new int[this.size];
	}
	
	public void enQueue(int data) {//inserting so check for size
		if(rear == size) {
			System.out.println("Overflow");
			return;
		}
		this.arr[rear++] = data;
	}
	
	public void printQueue() {
		System.out.print("Queue :");
		for (int i = 0; i < rear; i++) {
			System.out.print(this.arr[i]+" ");
		}
		System.out.println();
	}
	
	public void deQueue() {//deleting so check for 0 element
		if(this.front == this.rear) {
			System.out.println("Underflow");
			return;
		}
		rear--;
	}
	
	
}


public class Queue_Impl {
	public static void main(String[] args) {
		Queue queue = new Queue(3);
		queue.printQueue();
		queue.enQueue(10);
		queue.printQueue();
		queue.enQueue(11);
		queue.printQueue();
		queue.enQueue(11);
		queue.printQueue();
		queue.enQueue(11);
		queue.printQueue();
		queue.deQueue();
		queue.printQueue();
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		queue.printQueue();
	}
}
