package july5th.bst;

import java.util.LinkedList;
import java.util.Queue;

/**Binary tree data structure**/
class Node{
	int val;
	Node left;
	Node right;
	public Node(int val) {
		super();
		this.val = val;
	}
}

/**Use of Binary tree data structure**/
class BST{
	Node root;
	
	/**DFS traversal - inorder, postorder, preorder**/
	public void inorder() {
		System.out.print("Inorder : ");
		rec_inorder(root);
		System.out.println();
	}

	private void rec_inorder(Node root) {
		if(root == null) return;
		rec_inorder(root.left);
		System.out.print(root.val+" ");
		rec_inorder(root.right);
	}
	
	public void preorder() {
		System.out.print("Preorder : ");
		rec_pre(root);
		System.out.println();
	}

	private void rec_pre(Node root) {
		if(root == null) return;
		System.out.print(root.val+" ");
		rec_pre(root.left);
		rec_pre(root.right);
	}
	
	public void postorder() {
		System.out.print("Postorder : ");
		rec_post(root);
		System.out.println();
	}
	
	private void rec_post(Node root) {
		if(root == null) return;
		rec_post(root.left);
		rec_post(root.right);
		System.out.print(root.val+" ");
	}
	
	/**BFS traversal - level order**/
	public void levelorder() {
		System.out.print("Levelorder BFS : ");
		Queue<Node> qq = new LinkedList();
		qq.add(root);
		
		while(!qq.isEmpty()) {
			Node t = qq.poll();
			System.out.print(t.val+" ");
			
			if (t.left != null) qq.add(t.left);
			if (t.right != null) qq.add(t.right);
		}
		System.out.println();
	}
	
	public void insert(int key) {
		root = rec_insert(root, key);
	}

	private Node rec_insert(Node root, int key) {
		if (root == null) 
			return new Node(key);
		
		if(key < root.val) {
			root.left =  rec_insert(root.left, key);
		}else {
			root.right = rec_insert(root.right, key);
		}
		return root;
	}
	
	public void delete(int key) {
		root = rec_del(root, key);
	}

	private Node rec_del(Node root, int key) {
		if(root == null) return null;
		
		if(key < root.val) root.left = rec_del(root.left, key);
		else if(key > root.val) root.right = rec_del(root.right, key);
		else {//current root node is target
			//case 1 : 0 children
			if(root.left == null && root.right == null) return null;
			
			//case 2 : 1 children
			if(root.left == null) return root.right;
			if(root.right == null) return root.left;
			
			//case 3 : 2 children -> find min from right -> replace with root -> delete that min ele
			Node t = minFromRight(root.right);
			root.val = t.val;
			root.right = rec_del(root.right, t.val);
		}
		return root;
	}

	private Node minFromRight(Node root) {
		Node t = root;
		while(t.left != null) t = t.left;
		return t;
	}
	
	public void search_(int key) {
		if(rec_search(root, key)) {
			System.out.println("FOUND Value "+key);
			return;
		}
		System.err.println("NOT FOUND Value "+key);
		
	}

	private boolean rec_search(Node root, int key) {
		if(root == null) return false;
		if(root.val == key) return true;
		if(key < root.val) return rec_search(root.left, key);
		else return rec_search(root.right, key);
	}
}

/**Test Binary data structure**/
public class BST_DFS_BFS_Delete_Insert {
	public static void main(String[] args) {
		BST obj = new BST();
		obj.inorder();
		obj.preorder();
		obj.postorder();

		obj.root = new Node(10);
		obj.root.left = new Node(1);
		obj.root.right = new Node(20);
		obj.inorder();
		obj.preorder();
		obj.postorder();
		obj.levelorder();
		obj.insert(14);obj.inorder();
		obj.delete(14);obj.inorder();
		obj.delete(20);obj.inorder();
		obj.search_(10);obj.search_(20);obj.search_(1);
		obj.delete(1);obj.inorder();
		obj.delete(10);obj.inorder();
	}
}
