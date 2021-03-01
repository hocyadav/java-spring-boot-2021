package feb_1st_ds_n_coding;
/**
 * 
 * @author Hariom Yadav | 01-Feb-2020
 *
 */

//ds 
class Node{
	int data;
	Node left, right;
	
	public Node(int d) {
		data = d;
	}
}

//use of ds
class BST{
	Node root;
	
	public void insert(int k) {
		root = rec_insert(root, k);
	}
	
	private Node rec_insert(Node root, int k) {
		if(root == null) {
			Node nn = new Node(k);
			root = nn;
			return root;
		}
		if(k < root.data) {
			root.left = rec_insert(root.left, k);
		}else {
			root.right = rec_insert(root.right, k);
		}
		return root;
	}
	
	public void delete(int k) {
		root = rec_delete(root, k);
	}
	
	
	private Node rec_delete(Node root, int k) {
		if(root == null) {
			return root;
		}else if(k < root.data) {
			root.left = rec_delete(root.left, k);
		}else if(k > root.data) {
			root.right = rec_delete(root.right, k);
		}else { // update right child in 3rd case
			//case 1 : no child 
			if(root.left == null && root.right == null)
				return null;
			
			//case 2 : one child
			if(root.left != null)
				return root.left;
			if(root.right != null)
				return root.right;
			
			//case 3 : 2 child 
			Node t = min(root);
			root.data = t.data;
			root.right = rec_delete(root.right, t.data);
		}
		return root;
	}

	private Node min(Node root) {
		if(root == null)
			return root;
		Node t = root;
		while(t.left != null) {
			t = t.left;
		}
		return t;
	}

	public void inorder() {
		System.out.print("Inorder : ");
		rec_inorder(root);
		System.out.println();
	}

	private void rec_inorder(Node root) {
		if(root == null)
			return;
		rec_inorder(root.left);
		System.out.print(root.data+" ");
		rec_inorder(root.right);
	}
	
}

//test ds
public class BinarySearchTree {
	public static void main(String[] args) {
		BST tree = new BST();
		tree.insert(12);tree.inorder();
		tree.insert(8);tree.inorder();
		tree.insert(18);tree.inorder();
		tree.delete(8);tree.inorder();
		tree.delete(12);tree.inorder();
		tree.delete(18);tree.inorder();
		tree.delete(11); tree.inorder();
		tree.insert(12);tree.inorder();
		tree.insert(8);tree.inorder();
		tree.insert(18);tree.inorder();
	}
}
