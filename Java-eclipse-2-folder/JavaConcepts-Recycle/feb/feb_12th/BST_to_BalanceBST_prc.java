package feb_12th;

import java.util.Vector;
/**
 * 
 * @author Hariom Yadav | 12-Feb-2020
 *
 */
class Node {
	int data;
	Node left, right;
	
	public Node(int d) {
		data = d;
	}
}

class BinarySearchTree {
	Node root;
	
	public void inorder() {
		System.out.print("Inorder : ");
		rec_inorder(root);
		System.out.println();
	}

	private void rec_inorder(Node root) {
		if(root == null) return;
		
		rec_inorder(root.left);
		System.out.print(root.data+" ");
		rec_inorder(root.right);
	}
	
	public void bstToBalance() {
		Vector<Node> vec = new Vector<>();
		storeInorder(vec, root);
		root = balanceBst(vec, 0, vec.size() - 1);
 	}

	private Node balanceBst(Vector<Node> vec, int start, int end) {
		//base case
		if(start > end) 
			return null;
		int mid = (start + end)/2;
		Node midNode = vec.get(mid);
		midNode.left = balanceBst(vec, start, mid-1);//mid - 1 : because mid is taken as root element
		midNode.right = balanceBst(vec, mid+1, end);
		return midNode;
	}

	private void storeInorder(Vector<Node> vec, Node root) {
		if(root == null) return;
		storeInorder(vec, root.left);
		vec.add(root);
		storeInorder(vec, root.right);
	}
	
}

public class BST_to_BalanceBST_prc {
	public static void main(String[] args) {
		BinarySearchTree obj = new BinarySearchTree();
		obj.inorder();
		obj.root = new Node(12);
		obj.root.left = new Node(6);
		obj.root.left.left = new Node(2);
		obj.root.left.left.left = new Node(1);
		obj.inorder();
		obj.bstToBalance();
		obj.inorder();
	}
}
/**
Inorder : 
Inorder : 1 2 6 12 
Inorder : 1 2 6 12 


*/