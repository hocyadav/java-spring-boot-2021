package circularLL_31th_dec;
/**
 * 
 * @author Hariom Yadav | 31-Dec-2019
 *
 */

class NodeC{
	int data;
	NodeC next;
	
	public NodeC(int d) {
		data = d;
	}
	
}


class CLL_add_print{
	NodeC head;
	
	public void add(int k) {
		NodeC nn = new NodeC(k);
		nn.next = head;//1st link : adding at 1st place in CLL
		
		if(head == null) {
			head = nn;
			nn.next = nn;//or nn.next = head // circular connection
		}else {
			NodeC t = head;
			
			while(t.next !=  head)// (t.next != null) for LL
				t = t.next;
			
			t.next = nn;//2nd link
			head = nn;//3rd link
		}
		
	}
	
	public void print() {
		NodeC t = head;
		
		System.out.print("CLL : ");
		
		if(head != null) {
			do {
				System.out.print(t.data+" ");
				t = t.next;
			}while(t != head);
		}
		System.out.println();
	}
	
}

public class CircularLL_add_print {
	public static void main(String[] args) {
		CLL_add_print obj = new CLL_add_print();
		obj.print();
		obj.add(12); obj.print();
		obj.add(13); obj.print();
		obj.add(14); obj.print();
	}
}
/**

CLL : 
CLL : 12 
CLL : 13 12 
CLL : 14 13 12 

 */
