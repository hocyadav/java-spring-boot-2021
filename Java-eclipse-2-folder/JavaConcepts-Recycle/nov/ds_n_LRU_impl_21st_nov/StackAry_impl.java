package ds_n_LRU_impl_21st_nov;
/**
 * 
 * @author Hariom Yadav - Nov 21, 2019
 *
 */
//ds
class StackA{
	//know somethng
	int top;
	int size;
	int[] stack;
	
	//does something
	public StackA(int s){
		top = -1;
		size = s;
		stack = new int[size];
	}
	/**
	 * Push
	 * @param v
	 */
	public void push(int v) {
		if(top == size-1) {
			System.out.println("Overflow - Stack");
			return;
		}else {
			stack[++top] = v;
		}
	}
	
	public void pop() {
		if(top == -1) {
			System.out.println("Underflow - Stack");
		}else {
			top--;
		}
	}
	
	public int top() {
		return (top == -1)? -1 : stack[top];
	}
	
	public void print() {
		System.out.print("STACK : ");
		for(int i=0; i<=top; i++) {
			System.out.print(stack[i]+" ");
		}
		System.out.println();
	}
	
}


public class StackAry_impl {

	public static void main(String[] args) {
		StackA obj = new StackA(4);
		obj.pop();
		obj.push(11); obj.print();
		obj.push(12); obj.print();
		obj.push(12); obj.print();
		obj.push(12); obj.print();
		obj.push(12); obj.print();
		obj.pop();
	}

}
