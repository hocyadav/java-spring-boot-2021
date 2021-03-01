package june12th;

import java.util.LinkedList;
import java.util.List;

class StackLL{
	List<Integer> list;
	
	
	public StackLL() {
		super();
		this.list = new LinkedList<Integer>();//command shift o //auto import
	}

	public void push(int data) {
		list.add(data);
	}
	
	public void pop() {
		if(list.isEmpty()) {
			System.out.println("Underflow");
			return;
		}
		list.remove(list.size()-1);
	}
	public void print() {
		System.out.print("List : ");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
		System.out.println();
	}
}

public class Stack_LL_impl {
	public static void main(String[] args) {
		StackLL ll = new StackLL();
		ll.pop();
		ll.print();
		ll.push(12);
		ll.print();
		ll.push(13);
		ll.push(13);
		ll.push(13);
		ll.print();
		ll.pop();
		ll.print();

	}
}
