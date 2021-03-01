package ds_27th_nov;
/**
 * 
 * @author Hariom Yadav
 *
 */
class Node{
	int data;
	Node left, right;
	
	public Node(int v){
		data = v;
	}
}


class Bst{
	Node root;
	
	public boolean search(int k) {
		return (rec_search(root, k) != null)? true:false;
	}

	private Node rec_search(Node r, int k) {
		if(r == null) return r;
		else {
			if(k < r.data)
				return rec_search(r.left, k);
			else
				return rec_search(r.right, k);
		}
	}
	
	public void insert(int k) {
		root = rec_insrt(root, k);
	}

	private Node rec_insrt(Node r, int k) {
		if(r == null) {
			r = new Node(k);
			return r;
		}else {//update left and right value
			if(k < r.data)
				r.left = rec_insrt(r.left, k);
			else
				r.right = rec_insrt(r.right, k);
		}
		return r;
	}
	
	public void delete(int key) {
		System.out.println("Delete : "+key + " ");
		root = rec_del(root, key);
	}

	private Node rec_del(Node root, int key) {
		if(root == null) return null;
		else if(key < root.data)
			root.left = rec_del(root.left, key);
		else if(key > root.data)
			root.right = rec_del(root.right, key);
		else {
			//case 1: 0 child
			if(root.left == null && root.right == null)
				return null;
			
			//case 2: 1 child
			if(root.left == null)
				return root.right;
			
			if(root.right == null)
				return root.left;
			
			//case 3: 2 child
			Node t = min(root.left);
			root.data = t.data;
			root.right = rec_del(root.right, t.data);
			
		}
		return root;
	}

	private Node min(Node root) {//go left 
		Node t = root;
		while(t.left != null) {
			t = t.left;
		}
		return t;
	}
	
	public void inorder() {
		System.out.print("INorder : ");
		rec_inorder(root);
		System.out.println();
	}

	private void rec_inorder(Node root) {
		if(root == null) return;
		else {
			rec_inorder(root.left);
			System.out.print(root.data + " ");
			rec_inorder(root.right);
		}
	}
	
}


public class BST_search_insert_delete_DFS_BFS {
	public static void main(String[] args) {
		Bst obj = new Bst();
		
		obj.insert(12); obj.inorder();
		obj.insert(10); obj.inorder();
		obj.insert(21); obj.inorder();
		obj.insert(1); obj.inorder();
		obj.delete(1); obj.inorder();
		
		obj.delete(10); obj.inorder();
		
		obj.delete(12); obj.inorder();
		
		obj.delete(21); obj.inorder();
		
	}
}
