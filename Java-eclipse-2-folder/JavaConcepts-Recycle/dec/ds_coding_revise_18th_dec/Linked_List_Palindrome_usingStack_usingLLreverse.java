package ds_coding_revise_18th_dec;

import java.util.Stack;

class List{
	Node head;
	
	public void add(int v) {
		Node nn = new Node(v);
		
		if(head == null)
			head = nn;
		else {
			Node t = head;
			while(t.next != null) {
				t = t.next;
			}
			t.next = nn;
		}
	}
	
	public void print() {
		Node t = head;
		System.out.print("List : ");
		while(t != null) {
			System.out.print(t.data +" ");
			t = t.next;
		}
		System.out.println();
	}
	
	public void palindromeUsingStack() {
		Stack<Integer> ss = new Stack<>();
		Node t = head;
		
		while(t!=null) {
			ss.push(t.data);
			t = t.next;
		}
		
		Node t2 = head;
		while(t2 != null) {
			if(t2.data == ss.pop()) {//pop from stack and compare
				t2 = t2.next;
				continue;
			}else {
				System.out.println("different ");
				break;
			}
		}
		
		System.out.println("Same ");
		
	}
	//same as above ??: while loop change , if condition is on reverse of above
	public boolean isPalindrome() {
		Node head = this.head;
		
		
        Node current=head;
        Stack <Integer> stack = new Stack <Integer>();
        while (current!=null){
            stack.push(current.data);
            current=current.next;
        }
        current=head;
        while (current!=null){
            if (current.data!=stack.pop())
                return false;
            current=current.next;
        }
        return true;
    }

	public void palindromeUsingLLreverse() {
		Node t = head;
		Node mid = mid(t);
		Node t2 = mid.next;
		mid.next = null;
		
		//reverse 2nd list
		t2 = reverseLL(t2);
		
		
		
	}

	private Node reverseLL(Node head) {
		Node t1 = null;//prev
		Node t2 = head;//current
		Node next;
		
		while(t2 != null) {
			next = t2.next;
			t2.next = t1;
			t1 = t2;
			t2 = next;
		}
		
		return t1;
	}

	private Node mid(Node t) {
		Node slow = t;
		Node fast = t;
		
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	
}

public class Linked_List_Palindrome_usingStack_usingLLreverse {
	public static void main(String[] args) {
		List obj  = new List();
		obj.add(1);
		obj.add(2);obj.print();
		obj.add(1);obj.print();
		obj.palindromeUsingStack();
		obj.palindromeUsingLLreverse();
		//System.out.println(obj.isPalindrome());//testing
		
	}
}
