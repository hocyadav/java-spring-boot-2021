package ds_n_LRU_impl_21st_nov;
/**
 * 
 * @author Hariom Yadav - Nov 21, 2019
 *
 */
//ds
class Node{
	int data;
	Node next;
	
	public Node(int v) {
		data = v;
	}
}

//use of DS
class StackLL{
	Node head;
	
	/**
	 * Stack - Push method
	 * @param v
	 */
	public void push(int v) {//add new node at starting of LL
		Node nn = new Node(v);
		if(head == null)
			head = nn;
		else {
			nn.next = head;//make connection
			head = nn;//update head
		}
	}
	
	public void pop() {
		if(head == null) {
			System.out.println("Underflow - Stack");
			return;
		}else {
			head = head.next;
		}
	}
	
	public int top() {
		if(head == null) return -1;
		return head.data;
	}
	
	public void print() {
		Node t = head;
		System.out.print("Stack : ");
		while(t != null) {
			System.out.print(t.data+" ");
			t = t.next;
		}
		System.out.println();
	}
}

public class StackLL_impl {

	public static void main(String[] args) {
		StackLL obj = new StackLL();
		obj.pop();
		obj.push(1); obj.print();
		obj.push(1); obj.print();
		obj.push(1); obj.print();
		obj.pop(); obj.print();
		obj.print();
	}

}
