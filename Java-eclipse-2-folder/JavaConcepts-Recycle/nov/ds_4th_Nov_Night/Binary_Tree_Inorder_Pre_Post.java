package ds_4th_Nov_Night;
/**
 * 
 * @author Hariom Yadav - Nov 4, 2019
 *
 */
//data struture
class Node{
	int data;
	Node left, right;
	
	//does something
	Node(int v){
		data = v;
	}
}

//use of DS
class BinaryT{
	//know something
	Node root;
	
	//does something
	BinaryT(int v){
		root = new Node(v);
	}
	
	void inorder() {//recurxion - FCS
		inorder_fcs(root);
		System.out.println("");
	}

	private void inorder_fcs(Node root2) {
		if(root2 == null) {
			return;
		}else {
			inorder_fcs(root2.left);
			System.out.print(root2.data+" ");
			inorder_fcs(root2.right);
		}
		
	}
	
	void preorder(){
		pre_rec(root);
		System.out.println("");
	}

	private void pre_rec(Node root2) {
		if(root2 == null) {
			return;
		}else {
			System.out.print(root2.data+" ");
			pre_rec(root2.left);
			pre_rec(root2.right);
		}
	}
	
	void postorder() {
		post_rec(root);
	}

	private void post_rec(Node root2) {
		if(root2 == null) {
			return;
		}else {
			post_rec(root2.left);
			post_rec(root2.right);
			System.out.print(root2.data+" ");
		}
	}

}

public class Binary_Tree_Inorder_Pre_Post {
	public static void main(String[] args) {
		BinaryT obj = new BinaryT(1);
		obj.root.left = new Node(2);
		obj.root.right = new Node(3);
		
		obj.root.left.left = new Node(4);
		obj.root.left.right = new Node(5);
		
		obj.root.right.left = new Node(6);
		obj.root.right.right = new Node(7);
		
		
		obj.inorder();
		obj.preorder();
		obj.postorder();
		
	}
}
