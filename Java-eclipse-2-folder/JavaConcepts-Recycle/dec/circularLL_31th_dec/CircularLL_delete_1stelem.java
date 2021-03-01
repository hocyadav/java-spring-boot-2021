package circularLL_31th_dec;
/**
 * 
 * @author Hariom Yadav | 01-Jan-2020
 *
 */
class CLL_delete extends CLL_add_print{
	//if any constructor with argument then use here so that it will take parameter from here 
	NodeC last;//store the last node address i.e. before head element
	
	public void helper_last() {
		NodeC t = head;
		if(head != null) {
			do {
				t = t.next;
			}while(t != head);
		}
		last = t;
	}
	
	public void delete1st() {
		if(head == null || head.next == null) {
			head = last = null;
			return;
		}
		//last = head.next;
		head = head.next;//1. move head pointer forward
		last.next = head;	//2. new connection last --> head
	}
}

public class CircularLL_delete_1stelem {
	public static void main(String[] args) {
		CLL_delete obj = new CLL_delete();
		obj.add(12); obj.print();
		obj.add(13); obj.print();
		//System.out.println(obj.head.data+" "+obj.last.data);
		//obj.delete1st(); obj.print();
	}
}
/**
CLL : 12 
CLL : 13 12 
*/