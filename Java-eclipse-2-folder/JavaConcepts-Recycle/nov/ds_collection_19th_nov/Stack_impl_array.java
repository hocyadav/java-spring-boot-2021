package ds_collection_19th_nov;
/**
 * 
 * @author Hariom Yadav - Nov 19, 2019
 *
 */

class Stack{
	//know something
	int top;
	int size;
	int[] stack;
	
	//does something
	public Stack(int s){
		size = s;
		stack = new int[size];
	}
	
	void push(int i) {
		if(top == size -1) {
			System.out.println("Stack overflow");
			return;
		}else {
			stack[++top] = i;
		}
	}
	
	
	
	
	
}

public class Stack_impl_array {

}
