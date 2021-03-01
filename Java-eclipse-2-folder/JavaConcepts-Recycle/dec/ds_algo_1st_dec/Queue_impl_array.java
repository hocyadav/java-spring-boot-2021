package ds_algo_1st_dec;

/**
 * 
 * @author Hariom Yadav | 01-Dec-2019
 *
 */

//ds - array

//use of ds
class Queue{
	int size;
	int[] queue;
	int frontOUT, rearIN;
	
	public Queue(int size) {
		this.size 	= size;
		queue 		= new int[this.size];
		frontOUT 	= 0;
		rearIN 		= 0;
	}
	
	public boolean isEmpty() {
		return (frontOUT == rearIN)?true:false;
	}
	
	public void enQ(int v) {//IN : rear++
		if(rearIN == size) {
			System.out.println("Overflow");
			return;
		}else {
			queue[rearIN++] = v;
		}
	}
	
	public void deQ() {//OUT : front no change in array, rear move 1 left for loop
		if(frontOUT == rearIN) {
			System.out.println("Underflow - no data");
			return;
		}else {
			for(int i=0; i < rearIN-1; i++)
				queue[i] = queue[i+1];
			
			rearIN--;
		}
	}
	
	public int peek() {
		if(frontOUT == rearIN) {
			System.out.println("No element");
			return -1;
		}
		return queue[frontOUT];
	}
	
	public void print() {
		System.out.print("Queue : ");
		for(int i=0; i<rearIN; i++)
			System.out.print(queue[i]+" ");
		System.out.println();
	}
	
}
//test DS
public class Queue_impl_array {
	public static void main(String[] args) {
		Queue obj = new Queue(4);
		obj.print();
		obj.enQ(10); obj.print();
		
		System.out.println("peek front : "+obj.peek());
		
		obj.enQ(20); obj.print();
		obj.enQ(30); obj.print();
		System.out.println("peek front : "+obj.peek());
		
		obj.enQ(40); obj.print();
		obj.enQ(50); obj.print();
		obj.deQ(); obj.print();
		
		obj.deQ(); obj.print();
		System.out.println("peek front : "+obj.peek());
		
		obj.deQ(); obj.print();
		System.out.println("peek front : "+obj.peek());
		
		obj.deQ(); obj.print();
		System.out.println("peek front : "+obj.peek());
		
		obj.deQ(); obj.print();
	}
}
/**

Queue : 
Queue : 10 
peek front : 10
Queue : 10 20 
Queue : 10 20 30 
peek front : 10
Queue : 10 20 30 40 
Overflow
Queue : 10 20 30 40 
Queue : 20 30 40 
Queue : 30 40 
peek front : 30
Queue : 40 
peek front : 40
Queue : 
No element
peek front : -1
Underflow - no data
Queue : 

*/
