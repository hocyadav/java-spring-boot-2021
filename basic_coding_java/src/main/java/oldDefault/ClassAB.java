package oldDefault;

/**fractual inter interview
 * impl own marker interface and class
 * **/
class Node1{
	int data;
	Node1 next;
	public Node1() {}
	
	public Node1(int d){
		this.data = d;
	}
}

class SLL {
	Node1 head = null; 
	
	public Node1 reverse(){
		Node1 it = head;
		Node1 p = null;
		Node1 t = null;
		while(it != null) {//1->2->3->4->5->null
			t = it.next;
			
			it.next = p;
			p = it;
			
			it = t;
		}
		return p;
	}
	
	public Node1 listCopy(Node1 head) {
		Node1 head2 = null;
		
		Node1 it = head;
		while(it != null) {
			Node1 nn = new Node1();
			nn.data = it.data;
			nn.next = it.next;
			//
			it = it.next;
		}
		return head2;
	}
}

public class ClassAB {
	
	public static void main(String[] args) {
		
		int[] arr = {0, 0, 0, 2, 3, 0, 5, 6};
		
		for(int v : arr) {
			System.out.print(v+" - ");
		}
		System.out.println();
		int p = arr.length - 1;
		int q = arr.length - 1;
		
		while(q > 0) {
			if(arr[p] != 0) 
				p--;
			else if(arr[p] == 0){
				for(int i = p; i > 0; i--) {
					if(arr[i] != 0) {//non zero
						q = i;
						break;
					}
				}
				int t = arr[p];
				arr[p] = arr[q];
				arr[q] = t;
				//p--;
			}
			q--;
		}
		for(int v : arr) {
			System.out.print(v+" ");
		}
		
	}
}
