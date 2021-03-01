package linked_list_10th_dec_night;
/**
 * 
 * @author Hariom Yadav | 10-Dec-2019
 *
 */

//ds
class CircularLL{
	
	public static class Node{
		int data;
		Node next;
		
		public Node(int v) {
			data = v;
		}
	}
	
	Node last;
	
	public void addEmpty(int v) {
		last = rec_addEmpty(last, v);
	}

	private Node rec_addEmpty(Node last, int v) {
		if(last != null)
			return last;//coz this method is only for add into empty list
		
		Node nn = new Node(v);//1. create new node
		//2. make connection
		last = nn;
		last.next = last;
		//3. return updated head last node
		return last;
	}
	
	public void addFirst(int v) {
		last = rec_addFirst(last, v);
	}

	private Node rec_addFirst(Node last, int v) {
		if(last == null)
			return rec_addEmpty(last, v);
		
		Node nn = new Node(v);//1. new node
		//2. connection
		nn.next = last.next;
		last.next = nn;
		//3. return updated head last node
		return last;
	}
	
	public void addLast(int v) {
		last = rec_addLast(last, v);
	}
	/**
	 * same as addFirst + move last to newly added node
	 * @param last
	 * @param v
	 * @return
	 */
	private Node rec_addLast(Node last, int v) {
		if(last == null)
			return rec_addEmpty(last, v);
		
		Node nn = new Node(v);//1. new node
		//2. make connection
		nn.next = last.next;
		last.next = nn;
		//move last node to newly added node
		last = nn;
		//3. return updated head last node
		return last;
	}
	
	public void addAfter(int p, int v) {
		last = rec_addAfter(last, p, v);
	}
	/**
	 * Traverse through all node
	 * @param last
	 * @param p
	 * @param v
	 * @return
	 */
	private Node rec_addAfter(Node last, int p, int v) {
		if(last == null)//not a CLL
			return null;
		
		Node pnode = last.next;
		
		do {
			
			if(pnode.data == p) {//found : add + return
				Node nn = new Node(v);//1. new node
				//2. make connection
				nn.next = pnode.next;
				pnode.next = nn;
				//3. if last node is key then move last node to newly added node
				if(pnode == last)
					last = pnode;
				//4. return updated node
				return last;
				
			}else {
				pnode = pnode.next;
			}
			
		}while(pnode != last.next);//comparing starting point , starting point is p = last.next
		
		return last;
	}

	public void print() {
		System.out.print("CLL : ");
		
		if(last == null) {
			System.out.println();
			return;
		}
		
		Node t = last.next;
		while(t != last) {
			System.out.print(t.data+" ");
			t = t.next;
		}
		System.out.print(t.data);
		System.out.println();
	}
	
	public void traversePrint() {
		Node t = last.next;
		System.out.print("CLL : ");
		do {
			System.out.print(t.data+" ");
			t = t.next;
		}while(t != last.next);
		
	}
	
	
}


public class CircularLinkedList {
	public static void main(String[] args) {
		CircularLL obj = new CircularLL();
		obj.print();
		obj.addEmpty(9); obj.print();
		obj.addFirst(1);obj.print();
		obj.addFirst(2);obj.print();
		obj.addLast(4);obj.print();
		obj.addAfter(1, 34); obj.print();
		obj.traversePrint();
		
	}
}
/**
CLL : 
CLL : 9
CLL : 1 9
CLL : 2 1 9
CLL : 2 1 9 4
CLL : 2 1 34 9 4
CLL : 2 1 34 9 4 

*/