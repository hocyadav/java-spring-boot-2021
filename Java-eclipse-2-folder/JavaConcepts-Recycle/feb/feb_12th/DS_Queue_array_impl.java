package feb_12th;
/**
 * 
 * @author Hariom Yadav | 12-Feb-2020
 *
 */

class Queue {
	int[] qq;
	int front, rear;
	int size;
	
	public Queue(int s) {
		size = s;
		front = 0;
		rear = 0;
		qq = new int[size];
	}
	
	public void enQ(int k) {
		if(rear == size - 1) {
			System.out.println("Overflow Queue..");
			return;
		}
		qq[rear++] = k;
	}
	
	public void deQ() {
		if(front == rear) {
			System.out.println("Empty Queue..");
			return;
		}
		front++;
	}
	
	public void print() {
		System.out.print("Queue : ");
		if(front == rear) {
			System.out.println("");
			return;
		}
		for(int i = front; i < rear; i++) {
			System.out.print(qq[i]+" ");
		}
		System.out.println();
	}
}

public class DS_Queue_array_impl {
	public static void main(String[] args) {
		Queue obj = new Queue(4);
		obj.print();
		obj.deQ();
		obj.enQ(12);obj.print();
		obj.enQ(1);obj.print();
		obj.enQ(2);obj.print();
		obj.deQ();obj.print();
		obj.deQ();obj.print();
		obj.deQ();obj.print();
		obj.deQ();obj.print();
		
	}
}
/**
Queue : 
Empty Queue..
Queue : 12 
Queue : 12 1 
Queue : 12 1 2 
Queue : 1 2 
Queue : 2 
Queue : 
Empty Queue..
Queue : 
*/


