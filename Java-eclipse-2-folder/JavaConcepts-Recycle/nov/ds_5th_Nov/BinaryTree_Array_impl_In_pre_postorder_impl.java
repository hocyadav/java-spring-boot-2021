package ds_5th_Nov;
/**
 * 
 * @author Hariom Yadav - Nov 5, 2019
 *
 */
//data str
class Node {
	//know some
	int data;
	Node left, right;
	
	//does some
	Node(int v){//not a mtrhod
		data = v;
		left = right = null;
	}
	
}
//use of DS
class Binary{
	//know some
	Node root;
	
	void inorder() {
		System.out.print("Inorder LNR: ");
		inrec(root);
		System.out.println("");
	}
	private void inrec(Node r) {
		if(r == null) {
			return;
		}else {
			inrec(r.left);
			System.out.print(r.data+" ");
			inrec(r.right);
		}
	}
	
	void preorder() {
		System.out.print("Preorder NLR: ");
		pre(root);
		System.out.println("");
	}
	private void pre(Node preRoot) {
		if(preRoot == null) {
			return;
		}else {
			System.out.print(preRoot.data+" ");
			pre(preRoot.left);
			pre(preRoot.right);
		}
	}
	void postorder() {
		System.out.print("Postorder LRN: ");
		post(root);
		System.out.println("");
	}
	private void post(Node r2) {
		if(r2 == null) {
			return;
		}else {
			post(r2.left);
			post(r2.right);
			System.out.print(r2.data+" ");
		}
	}
}


public class BinaryTree_Array_impl_In_pre_postorder_impl {
	public static void main(String[] args) {
		Binary obj = new Binary();
		obj.root = new Node(1);
		
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
