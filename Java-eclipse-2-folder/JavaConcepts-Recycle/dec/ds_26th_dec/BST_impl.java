package ds_26th_dec;

/**
 * 
 * @author Hariom Yadav | 26-Dec-2019
 *
 */

class Node{
	int data;
	Node left, right;
	
	public Node(int d) {
		super();
		data = d;
	}
	
}


class BinaryTree{
	Node root;
	
	public void search(int k) {
		Node t = rec_search(root, k);
	}

	private Node rec_search(Node root, int k) {
		if(root == null || root.data == k)
			return root;
		
		if(root.data < k)
			return rec_search(root.left, k);
		else
			return rec_search(root.right, k);
		
	}
	
	public void insert(int k) {
		root = rec_insert(root, k);
	}

	private Node rec_insert(Node root, int k) {
		if(root == null) {
			Node nn = new Node(k);
			root = nn;
			return root;
		}
		if(root.data < k)
			root.left = rec_insert(root.left, k);
		else if(root.data >= k)
			root.right = rec_insert(root.right, k);
		return root;
		
	}
	
	public void delete(int k) {
		root = rec_delete(root, k);
	}

	private Node rec_delete(Node root, int k) {
		if(root == null)
			return null;
		if(root.data < k)//delete left side + update root.left address
			root.left = rec_delete(root.left, k);
		else if(root.data > k)//delete right side  + update right address
			root.right = rec_delete(root.right, k);
		else {//found at root + update left , right address
			//case1 : 0 child
			//case 2: 1 child
			//case 3 : 2 child
			
			if(root.left == null && root.right == null)
				return null;
			
			if(root.left == null)
				return root.right;
			if(root.right == null)
				return root.left;
			
			Node t = minRightSide(root.right);
			
			root.data = t.data;
			root.right = rec_delete(root.right, t.data);
			
		}
		
		return root;//after update left and right address return root
		
	}

	private Node minRightSide(Node root) {
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


public class BST_impl {
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		
		tree.inorder();
		tree.delete(1);
		tree.insert(12); tree.inorder();
		tree.insert(10); tree.inorder();
		tree.insert(21); tree.inorder();
		tree.insert(1); tree.inorder();
		tree.insert(100); tree.inorder();
		tree.delete(100); tree.inorder();
		tree.delete(21); tree.inorder();
		tree.delete(12); tree.inorder();
		tree.delete(10); tree.inorder();
		tree.delete(1); tree.inorder();
		
		
	}
}
/**
Inorder : 
Inorder : 12 
Inorder : 12 10 
Inorder : 21 12 10 
Inorder : 21 12 10 1 
Inorder : 100 21 12 10 1 
Inorder : 21 12 10 1 
Inorder : 12 10 1 
Inorder : 10 1 
Inorder : 1 
Inorder : 



*/