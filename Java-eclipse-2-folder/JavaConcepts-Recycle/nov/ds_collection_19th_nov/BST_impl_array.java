package ds_collection_19th_nov;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Hariom Yadav - Nov 19, 2019
 *
 */

//ds
class Node{
	int data;
	Node left, right;
	
	public Node(int i) {
		data = i;
	}
}

//use of DS
class BST{
	Node root;
	
	//does something
	public void search(int key) {
		Node t = se_rec(root, key);
		if(t!=null)
			System.out.println("Found --> "+t.data);
		else
			System.out.println(key+" Not found");
	}
	
	private Node se_rec(Node root, int key) {
		if(root == null || root.data == key) {
			return root;
		}else {
			if(key < root.data)
				return se_rec(root.left, key);
			else
				return se_rec(root.right, key);
		}
	}
	
	public void insert(int key) {
		root = ins_rec(root, key);
	}
	
	private Node ins_rec(Node root, int key) {
		if(root == null) {
			Node nn = new Node(key);
			return nn;
		}else {//update left / right node 
			if(key < root.data)
				root.left = ins_rec(root.left, key);
			else
				root.right = ins_rec(root.right, key);
		}
		return root; //after update return root
	}
	
	public void inorder_DFS() {//give output as sorted
		ino_rec(root);
	}
	
	private void ino_rec(Node root) {
		if(root == null) return;
		else {
			ino_rec(root.left);
			System.out.print(root.data+" ");
			ino_rec(root.right);
		}
	}
	
	public void levelOrderTraversal_BFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			Node t = q.poll();
			System.out.print(t.data+" ");
			
			if(t.left != null) q.add(t.left);
			if(t.right != null) q.add(t.right);
		}
	}
	
	public void levelOrderTraversal_BFS2_right_to_left_zig_zag() {
		Queue<Node> q = new LinkedList<>();
		
		q.add(root);
		
		while(!q.isEmpty()) {
			Node t = q.poll();
			System.out.print(t.data+" ");
			
			//if child count is 2 : add right side then left side
			//if child count is 1 : add left side
			int childCount = 0;
			if(t.right != null)
				++childCount;
			if(t.left != null)
				++childCount;
					
			if(childCount == 2) {
				q.add(t.right);
				q.add(t.left);
			}else if(childCount == 1) {
				q.add(t.left);
			}
		}
		
	}
	

	
}

public class BST_impl_array {
	public static void main(String[] args) {
		BST obj = new BST();
		obj.insert(12); obj.insert(20); obj.insert(4); 
		
		obj.inorder_DFS();
		System.out.println();
		
		obj.levelOrderTraversal_BFS();
		System.out.println();
		
		System.out.print("Right to left : ");
		obj.levelOrderTraversal_BFS2_right_to_left_zig_zag();
		System.out.println();
		
		obj.insert(3);
		obj.inorder_DFS();
		System.out.println();
		obj.levelOrderTraversal_BFS2_right_to_left_zig_zag();

		
	}
}
