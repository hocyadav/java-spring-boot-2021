package ds_7th_Nov;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author Hariom Yadav - Nov 7, 2019
 *
 */
//ds
class Node{
	//know something
	int data;
	Node left, right;
	//does something
	Node(int v){
		data = v;
	}
}
//use of ds
class Bst{
	Node root;
	
	void insert(int key) {
		root = insrec(root,key);
	}
	private Node insrec(Node root, int key) {
		if(root == null) {
			return new Node(key);
		}else {
			if(key < root.data) {
				root.left = insrec(root.left, key);
			}else 
				root.right = insrec(root.right, key);
		}
		return root;
	}
	
	void inorder() {
		inorec(root);
		System.out.println();
	}
	private void inorec(Node root) {//sorted
		if(root == null) return;
		else {
			inorec(root.left);
			System.out.print(root.data+" ");
			inorec(root.right);
		}
	}
	
	Node search(int key){
		Node temp = ser(root, key);
		return temp;
	}
	private Node ser(Node root, int key) {
		if(root == null || root.data == key) {
			return root;
		}
		
		if(key<root.data)
			return ser(root.left, key);
		else
			return ser(root.right, key);
	}
	
	void level() {
		Queue<Node> qu = new LinkedList<Node>();
		qu.add(root);
		
		System.out.print("Level order - BFS ");
		while(!qu.isEmpty()) {
			Node tm = qu.poll();
			System.out.print(tm.data+" ");
			
			if(tm.left != null) {
				qu.add(tm.left);
			}
			if(tm.right != null)
				qu.add(tm.right);
		}
		System.out.println();
		
	}
}

public class BST_in_pre_post_level {
	public static void main(String[] args) {
		Bst obj = new Bst();
		obj.insert(10);
		obj.insert(8);
		obj.insert(13);obj.inorder();
		obj.insert(113);obj.inorder();
		obj.insert(1);obj.inorder();
		Node tm = obj.search(14);
		System.out.println();
		if(tm!=null)
			System.out.println(tm.data+"...");
		obj.level();
		
	}
}
