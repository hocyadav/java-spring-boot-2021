package ds_6th_Nov;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author Hariom Yadav - Nov 6, 2019
 *
 */

//1. "data structure" use for Tree
class Node{
	int data;
	Node left, right;
	
	Node(int v){
	 data = v;
	}
}

//2. data structure implementation
class BST{
	Node root;
	//search key inside tree
	public Node search(Node root, int key) {
		if(root == null || root.data == key) {
			return root;
		}else {
			if(key < root.data)
				return search(root.left, key);
			else
				return search(root.right, key);
		}
	}
	

	//TREE BFS - inorder , preorder, postorder
	void inorder() {
		inrec(root);
	}
	private void inrec(Node rt) {
		if(rt == null) return;//null check , recursion base case
		else {
			inrec(rt.left);
			System.out.print(rt.data+" ");
			inrec(rt.right);
		}
	}
	
	void preorder() {
		prec(root);
	}
	private void prec(Node ro2) {
		if(ro2 == null) return;//recursion base case
		else {
			System.out.print(ro2.data+" ");
			prec(ro2.left);
			prec(ro2.right);
			
		}
	}
	void postorder() {
		postRec(root);
	}
	private void postRec(Node pr) {
		if(pr == null) return;//recursion base case
		else {
			postRec(pr.left);
			postRec(pr.right);
			System.out.print(pr.data+" ");
		}
	}
	
	//TREE DFS - also know as Level Order
	void LevelOrder() {
		Queue<Node> qobj = new LinkedList<Node>();//queue
		qobj.add(root);
		
		while(!qobj.isEmpty()) {//check until queue has value
			//Remove from queue
			Node temp = qobj.poll();
			System.out.print(temp.data+" ");
			
			//Insert into queue
			if(temp.left != null)
				qobj.add(temp.left);
			if(temp.right != null)
				qobj.add(temp.right);
		}
	}
	
	//insert new data correct position in Binary search tree
	void insert(int key) {//Note : update root, since root will change
		root = inrec(root, key);
	}
	private Node inrec(Node ri,int v) {//base case create node and return --> go left or right
		//Tree is empty : create new node + set data and return
		if(ri == null) {
			ri = new Node(v);
			return ri;
		}
		//Tree is not empty : recursion : go left or right
		if(v < ri.data) {
			ri.left = inrec(ri.left, v);
		}else {
			ri.right = inrec(ri.right, v);
		}
		return ri;
	}
}

//3. Test data structure implementation
public class BST_inPrePost_n_Level {
	public static void main(String[] args) {
		BST obj = new BST();
		obj.root = new Node(10);
		obj.root.left = new Node(5);
		obj.root.right = new Node(15);
		
		obj.inorder();
		System.out.println("");
		
		obj.preorder();
		System.out.println("");
		
		obj.postorder();
		System.out.println("");
		
		obj.LevelOrder();
		System.out.println("");
		
		obj.insert(4);
		obj.insert(16);
		obj.insert(6);
		System.out.println("");
		obj.LevelOrder();
		
		Node temp = obj.search(obj.root, 11);
		if(temp != null)
			System.out.println("found "+temp.data);
	}
}
