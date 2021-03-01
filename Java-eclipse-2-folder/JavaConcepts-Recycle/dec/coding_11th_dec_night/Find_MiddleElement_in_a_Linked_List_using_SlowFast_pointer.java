package coding_11th_dec_night;

/**
 * 
 * @author Hariom Yadav | 11-Dec-2019
 *
 */
class Linked_List{
	Node2 head;
	
	public void pushAddFirst(int v) {//add at first place
		Node2 nn = new Node2(v);
		if(head == null)
			head = nn;
		else {
			nn.next = head;
			head = nn;
		}
	}
	
	public Node2 findMidElementInLL() {
		Node2 slow = head;
		Node2 fast = head;
		
		while(fast != null && fast.next != null) {
			slow = slow.next;//move 1 step
			fast = fast.next.next;//move 2 step
		}
		return slow;
	}
	
}

public class Find_MiddleElement_in_a_Linked_List_using_SlowFast_pointer {
	public static void main(String[] args) {
		Linked_List obj = new Linked_List();
		obj.pushAddFirst(12);
		obj.pushAddFirst(14);
		obj.pushAddFirst(15);
		obj.pushAddFirst(1);
		obj.pushAddFirst(16);
		
		Node2 n = obj.findMidElementInLL();
		System.out.println("mid element : "+n.data);
	}
}
/**
mid element : 15

*/