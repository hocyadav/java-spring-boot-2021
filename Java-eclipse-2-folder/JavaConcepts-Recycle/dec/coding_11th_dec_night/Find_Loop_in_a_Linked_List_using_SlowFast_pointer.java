package coding_11th_dec_night;

/**
 * 
 * @author Hariom Yadav | 11-Dec-2019
 *
 */

class LL{
	Node2 head;
	
	public void add_First(int v) {
		Node2 nn = new Node2(v);
		if(head == null)
			head = nn;
		else {
			nn.next = head;
			head = nn;
		}
	}
	
	public boolean findLoopInLL_usingSlowFast() {
		Node2 slow = head;
		Node2 fast = head;
		
		while(slow != null && fast != null && fast.next != null) {
			slow = slow.next;//jump 1 step
			fast = fast.next.next; //jump 2 step
			if(slow == fast)//if loop then it will never exit so checking for equal condition
				return true;
		}
		return false;
	}
}


public class Find_Loop_in_a_Linked_List_using_SlowFast_pointer {
	public static void main(String[] args) {
		LL obj = new LL();
		obj.add_First(20);
		obj.add_First(40);
		obj.add_First(15);
		obj.add_First(10);
		boolean b = obj.findLoopInLL_usingSlowFast();
		System.out.println(b);
		//create loop for testing
		obj.head.next.next.next.next = obj.head.next;
		boolean b2 = obj.findLoopInLL_usingSlowFast();
		System.out.println(b2);
	}
}
/**
false
true
*/