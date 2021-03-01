package june11th;

//ds
class Node2<T> {
	T data;
	Node2 next;
	public Node2(T data) {
		super();
		this.data = data;
	}
}

class SinglyLL_Integer{
	Node2<Integer> head;

	public void insert1st(Integer data) {
		Node2<Integer> nn = new Node2(data);
		if(head == null) {
			head = nn;
			return;
		}
		nn.next = head;
		head = nn;
	}

	public void print() {
		Node2<Integer> it = head;
		System.out.print("LL : ");
		while(it != null) {
			System.out.print(it.data+" ");
			it = it.next;
		}
		System.out.println();
	}
}
class SinglyLL_String{
	Node2<String> head;

	public void insert1st(String data) {
		Node2<String> nn = new Node2(data);
		if(head == null) {
			head = nn;
			return;
		}
		nn.next = head;
		head = nn;
	}

	public void print() {
		Node2<String> it = head;
		System.out.print("LL : ");
		while(it != null) {
			System.out.print(it.data+" ");
			it = it.next;
		}
		System.out.println();
	}
}

public class LinkListGeneric {
	public static void main(String[] args) {
		SinglyLL_Integer singlyLL = new SinglyLL_Integer();
		singlyLL.print();
		singlyLL.insert1st(10);
		singlyLL.insert1st(20);
		singlyLL.print();

		SinglyLL_String singlyLL_str = new SinglyLL_String();
		singlyLL_str.print();
		singlyLL_str.insert1st("hariom");
		singlyLL_str.insert1st("yadav");
		singlyLL_str.print();
	}
}