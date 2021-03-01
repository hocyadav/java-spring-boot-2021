package ds_algo_1st_dec;

//ds - use queue instead array

//use of ds
class Stack2Q{
	int top;
	int size;
	Queue qq1;
	Queue qq2;
	
	//does something
	//initialize queue
	public Stack2Q(int s) {
		size = s;
		//top = -1;
		qq1 = new Queue(size);
		qq2 = new Queue(size);
	}
	
	public void push(int v) {//top++(no top here) simply add
		if(qq1.isEmpty() && qq2.isEmpty()) {//both empty - add in any one
			qq1.enQ(v);
			//top++;
		}else {
			if(qq1.isEmpty()) {//q1 is empty add in q2
				qq2.enQ(v);
				//top++;
			}else {//q2 is empty add in q1
				qq1.enQ(v);
				//top++;
			}
		}
	}
	
	public void pop() {//top -- 
		if(qq1.isEmpty()) {
			int i = 0;
			
			while(!qq2.isEmpty() && i<) {
				qq1.enQ(qq2.peek());
				qq2.deQ();
			}
		}else {
			while(!qq1.isEmpty()) {
				qq2.enQ(qq1.peek());
				qq1.deQ();
				
			}
		}
	}
	
	
}

public class Stack_impl_using_2_Queue {
	public static void main(String[] args) {
		
	}
}
