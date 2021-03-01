package ds_12th_nov;

import java.util.Vector;
//TODO : not working
class Node{
	int data;
	Node left, right;
	
	public Node(){}
	
	public Node(int x){
		data = x;
	}
}


class Bst{
	Node root;

	public Node balanceTree(Node root) {
		//store nodes in dynamic array
		Vector<Node> vnode =  new Vector<>();
		storeBstInorderInVector(root, vnode);//store in vector by calling modified in-order
		
		//get nodes and create tree
		return buildTreeUtil(vnode, 0, vnode.size()-1);
		
		
	}

	private Node buildTreeUtil(Vector<Node> vnode, int start, int end) {
		if(start > end) 
			return null;
		else {
			int mid = (start + end)/2;//get mid index
			Node node = vnode.get(mid);//get mid value
			
			node.left = buildTreeUtil(vnode, start, mid-1);//recursion - update left root
			node.right = buildTreeUtil(vnode, mid+1, end);//recursion - update right root
			
			return node;
		}
	}

	private void storeBstInorderInVector(Node root, Vector<Node> vnode) {
		if(root == null) 
			return;
		else {
			storeBstInorderInVector(root.left, vnode);
			vnode.add(root);//adding all root node in dynamic array
			storeBstInorderInVector(root.right, vnode);
		}
	}
	
	public void print() {
		rec_inorder(root);
	}

	private void rec_inorder(Node root) {
		if(root == null)
			return;
		rec_inorder(root.left);
		System.out.println(root.data+" ");
		rec_inorder(root.right);
	}
	
}


public class BST_balance {
	public static void main(String[] args) {
		Bst obj = new Bst();
		obj.root = new Node(10);
		obj.root.left = new Node(9);
		obj.root.left.left = new Node(8);
		obj.root.left.left.left = new Node(6);
		obj.root.left.left.left.left = new Node(4);
		obj.root.left.left.left.left.left = new Node(2);
		
		obj.balanceTree(obj.root);
		
		obj.print();
		
		
	}
}
