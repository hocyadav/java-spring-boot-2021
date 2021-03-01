package feb_12th_office;

class LL extends LinkedList{
	Node head;
	
	public void reverseInGroup(int k) {//solve by 2 pointers

		Node traverse = head;
		int count = 0;
		Node headTemp = null;
		Node tailTemp = null;
		
		while(traverse != null) {
			count++;
			traverse = traverse.next;
			
			if(count == 3) {
				//1. set tail, tail next = null, [ (setting head not required since intial it is set after reverse set head)]
				Node temp = traverse;
				tailTemp = traverse;
				tailTemp.next = null;
				
				//2. call reverse
				Node rev = fun(headTemp);
				
				tailTemp = headTemp;
				headTemp = rev;
				
				//3. update head and count =0
				headTemp = traverse.next;
				count = 0;
			}
			if(tailTemp == null) {
				tailTemp = head;//1st time
			}
			else {
			}
			if(count == 3) {
				
				Node headNode = fun(headTemp);
				
				
				
				//make tail null + call reverse
				if(headTemp == null) {
					headTemp = head;
				}
				tailTemp = traverse;
				tailTemp.next = null;
				
				if(tailTemp != null) {
					tailTemp.next = headNode;
				}
				
				
				//make link with old tail
				
			}
			
		}
		
	}

	private Node fun(Node head) {//2 pointers
		Node t2 = head;
		Node t1 = null;
		Node temp = null;
		
		while(t2 != null) {
			temp = t2.next;
			
			t2.next = t1;
			t1 	= t2;
			
			t2 = temp;
			
		}
		return t1;
	}
	
}


public class LinkedList_revese_in_group_sizeK {
	public static void main(String[] args) {
		LL obj = new LL();
		obj.add1stPlace(1); obj.print();
		obj.add1stPlace(2); obj.print();
		obj.add1stPlace(3); obj.print();
		obj.reverse();obj.print();
	}
}
/**
LL : 1 
LL : 2 1 
LL : 3 2 1 
LL : 1 2 3 

*/