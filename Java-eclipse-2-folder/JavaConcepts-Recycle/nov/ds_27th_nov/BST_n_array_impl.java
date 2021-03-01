package ds_27th_nov;

import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav
 *
 */
class Node1{
	int data;
	LinkedList<Node1> leftRight = new LinkedList<>();
	
	public Node1(int v) {
		data = v;
	}
}

class BstNArray{
	
	Node1 root;
	
	public void preorder() {
		System.out.print("preorder : ");
		rec_preorder(root);
		System.out.println();
	}

	private void rec_preorder(Node1 root) {
		if(root == null) return;
		else {
			System.out.print(root.data+" ");
			for(int i=0; i<root.leftRight.size(); i++) {
				rec_preorder(root.leftRight.get(i));
			}
		}
	}
	
	
}


public class BST_n_array_impl {
	public static void main(String[] args) {
		BstNArray obj = new BstNArray();
		
		obj.root = new Node1(20);
		
		obj.preorder();
		
		obj.root.leftRight.add(new Node1(10));
		
		obj.preorder();
		
		obj.root.leftRight.add(new Node1(2));
		
		obj.preorder();
		
		obj.root.leftRight.getFirst().leftRight.add(new Node1(34));
		
		obj.preorder();
		
		
	}
}
