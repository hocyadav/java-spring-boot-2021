package feb_11th;
/**
 * 
 * @author Hariom Yadav | 11-Feb-2020
 * Mirror bst using recursion
 */
class BST_mirror extends BST{
	
	public void BST_mirror() {
		root = BST_mirror_rec(root);
	}

	private Node BST_mirror_rec(Node root) {
		if(root == null) {
			return null;
		}
		
		//1. call left and right mirror
		Node leftMirror = BST_mirror_rec(root.left);
		Node rightMirror = BST_mirror_rec(root.right);
		
		//2. swap
		Node temp = leftMirror;
		root.left = rightMirror;
		root.right = temp;
		
		return root;
		
	}
	
	
	
	
}

public class BST_mirror_recursion {
	public static void main(String[] args) {
		BST_mirror obj = new BST_mirror();
		obj.inorder();
		obj.root = new Node(12);
		obj.root.left = new Node(6);
		obj.root.left.left = new Node(2);
		obj.root.left.left.left = new Node(1);
		obj.inorder();
		obj.BST_mirror();
		obj.inorder();
	}
}
/**
 Inorder : 
Inorder : 1 2 6 12 
Inorder : 12 6 2 1 

 */
