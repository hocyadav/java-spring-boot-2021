package priority_queue_comparable_comparator_18th_nov;
/**
 * 
 * @author Hariom Yadav - Nov 18, 2019
 *
 */
//ds : array

//use of Data structure
class QueueDS{//default class - use inside package only
	//know something
	int front, rear;
	int size;
	int[] queue;
	
	//does something
	QueueDS(int x){
		size = x;
		queue = new int[size];
		front = rear = 0;
	}
	
	public void enQ(int v) {//rear ++ //not ++rear - first insert and then update
		if(rear == size) {
			System.out.println("Overflow Q");
			return;
		}else {
			queue[rear++] = v;
		}
	}
	
	public void deQ() {//r-- , before move 1 step left
		if(front == rear) {
			System.out.println("Underflow Q");
			return;
		}else {
			for(int i=0; i<rear-1; i++)
				queue[i] = queue[i+1];
			rear--;
		}	
	}
	
	public void print() {
		System.out.print("Queue : ");
		for(int i=0; i<rear; i++)
			System.out.print(queue[i]+" ");
		System.out.println();
	}
}

public class Queue_impl_Array{
	public static void main(String[] args) {
		QueueDS obj = new QueueDS(4);
		obj.deQ();
		obj.enQ(2); obj.print();
		obj.enQ(4); obj.print();
		obj.enQ(6); obj.print();
		obj.enQ(22); obj.print();
		obj.enQ(22); obj.print();
	}
}
