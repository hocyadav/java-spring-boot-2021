package feb_11th;

import java.util.Vector;
/**
 * 
 * @author Hariom Yadav | 11-Feb-2020
 *
 */
class Node {
	int data;
	Node left;
	Node right;
	
	public Node(int d) {
		data = d;
	}
}

class BST{
	Node root;
	
	public void inorder() {
		System.out.print("Inorder : ");
		inorcer_rec(root);
		System.out.println();
	}

	private void inorcer_rec(Node root2) {
		if(root2 == null) {
			return;
		}
		inorcer_rec(root2.left);
		System.out.print(root2.data+" ");
		inorcer_rec(root2.right);
	}

	public void balanceBST() {
		Vector<Node> vec = new Vector<>();
		storeInorder(vec, root);
		root = balanceBST_rec(vec, 0, vec.size()-1);
	}

	private Node balanceBST_rec(Vector<Node> vec, int i, int j) {
		if(i > j) {
			return null;
		}
		
		int mid = (i + j)/2;
		Node node = vec.get(mid);
		node.left = balanceBST_rec(vec, i, mid-1);
		node.right = balanceBST_rec(vec, mid+1, j);
		return node;
	}

	private void storeInorder(Vector<Node> vec, Node root) {
		if(root == null) {
			return;
		}
		
		storeInorder(vec, root.left);
		vec.add(root);
		storeInorder(vec, root.right);
	}

}


public class BST_to_Balanace_BST_recursion {
	public static void main(String[] args) {
		BST obj = new BST();
		obj.inorder();
		obj.root = new Node(12);
		obj.root.left = new Node(6);
		obj.root.left.left = new Node(2);
		obj.root.left.left.left = new Node(1);
		obj.inorder();
		
		obj.balanceBST();
		obj.inorder();
		
	}
}
/**
Inorder : 
Inorder : 1 2 6 12 
Inorder : 1 2 6 12 

*/