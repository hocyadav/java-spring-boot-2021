package ds_coding_revise_18th_dec;

class Node{
	int data;
	Node next;
	public Node(int data) {
		super();
		this.data = data;
	}
	
}

class StackLL{
	Node head;
	
	public void push(int v) {//at at first place
		Node nn = new Node(v);
		if(head == null)
			head = nn;
		else {
			nn.next = head;
			head = nn;
		}
	}
	
	public void pop() {
		if(head == null) {
			System.out.println("Underflow - empty");
			return;
		}
		head = head.next;
	}
	
	public int top() {
		if(head == null) {
			System.out.println("No data - empty");
			return -1;
		}
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

public class Stack_impl_Linked_List {
	public static void main(String[] args) {
		StackLL obj = new StackLL();
		obj.pop();
		obj.print();
		obj.push(12);obj.print();
		obj.push(13);obj.print();
		obj.push(14);obj.print();
		obj.push(16);obj.print();
	}
}
