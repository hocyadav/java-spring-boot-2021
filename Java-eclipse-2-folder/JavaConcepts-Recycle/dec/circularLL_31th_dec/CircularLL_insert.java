package circularLL_31th_dec;
/**
 * 
 * @author Hariom Yadav | 01-Jan-2020
 *
 */
class Node{
	int data;
	Node next;
	
	public Node(int data) {
		this.data = data;
	}
}

class CLL_insert{
	Node last;
	
	public void add_NULL(int k) {
		if(last != null)//this method is only for when last is null
			return;
		Node nn = new Node(k);
		last = nn;
		last.next = last;//loop 
	}
	
	public void add_BEGIN(int k) {
		if(last == null) {
			add_NULL(k);
			return;
		}
		Node nn = new Node(k);
		//singly LL : 2 connection required
		nn.next = last.next;//1st connection
		last.next = nn;		//2nd connection
	}
	
	//same code as above + move last pointer last=nn 
	public void add_END(int k) {
		if(last == null) {
			add_NULL(k);
			return;
		}
		Node nn = new Node(k);
		
		nn.next = last.next;
		last.next = nn;
		
		last = nn;//move last pointer : before that same code as add_BEGIN
	}
	
	//template using for traversing 
	//	t = last.next; //1st ele in CLL 
	//	do {
	//		t = t.next;
	//	}while(t != last.next);
	
	public void add_AFTER(int item, int k) {
		if(last == null) {
			add_NULL(k);
			return;
		}
		Node nn = new Node(k);
		//find item + then add + then check if find position is last then move last
		Node t = last.next;//first element in CLL
		
		do {
			if(t.data == item) {//while traversing if we found ele then add + return
				nn.next = t.next;
				t.next = nn;
				
				if(t == last)//special case: if search item is last elem -> then after adding move last elem
					last = nn;
				
				return;
			}
			t = t.next;
			
		}while(t != last.next);
		
	}
	//simply for experiment
	public void add_AFTER2(int item, int k) {
		if(last == null) {
			add_NULL(k);
			return;
		}
		Node nn = new Node(k);
		Node t = last.next;
		
		if(t == last) {
			add_END(k);
			return;
		}
		do {
			if(t.data == item) {//while traversing if we found ele then add + return
				nn.next = t.next;
				t.next = nn;
				return;
			}
			t = t.next;
		}while(t != last.next);
	}
	
	//put traversal template in if(last != null) block
	public void print() {
		
		System.out.print("CLL : ");
		if(last == null) {
			System.out.println();
			return;
		}
		
		Node t = last.next;
		if(last != null) {
			
			do {
				System.out.print(t.data+" ");
				t = t.next;
			}while(t != last.next);
			
		}
		System.out.println();
	}
}

public class CircularLL_insert {
	public static void main(String[] args) {
		CLL_insert obj = new CLL_insert();
		obj.print();
		obj.add_NULL(12); obj.print();
		
		obj.add_BEGIN(13); obj.print();
		
		obj.add_END(15); obj.print();
		
		obj.add_BEGIN(1); obj.print();
		
		obj.add_AFTER(1, 2); obj.print();
		obj.add_AFTER(15, 17);obj.print();
		
		obj.add_AFTER2(17, 20); obj.print();//after2 method is wrong in case of if we are adding after last
	}
}
/**

CLL : 
CLL : 12 
CLL : 13 12 
CLL : 13 12 15 
CLL : 1 13 12 15 
CLL : 1 2 13 12 15 
CLL : 1 2 13 12 15 17 
CLL : 20 1 2 13 12 15 17 

*/