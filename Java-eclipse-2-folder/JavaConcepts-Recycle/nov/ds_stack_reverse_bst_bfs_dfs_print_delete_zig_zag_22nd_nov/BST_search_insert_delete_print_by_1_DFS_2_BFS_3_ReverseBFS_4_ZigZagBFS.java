package ds_stack_reverse_bst_bfs_dfs_print_delete_zig_zag_22nd_nov;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * 
 * @author Hariom Yadav - Nov 22, 2019
 *
 */

//data structure
class Node{
	int data;
	Node left, right;
	
	Node(int v){
		data = v;
	}
}

//use of Data structure
class BstAllMethods{
	Node root;
	/**
	 * Search inside BST
	 * @param key
	 */
	public void search(int key) {
		Node t = rec_s(root, key);
	}
	
	private Node rec_s(Node root, int key) {
		if(root.data == key || root == null)
			return root;
		else {
			if(key < root.data)
				return rec_s(root.left, key);
			else
				return rec_s(root.right, key);
		}
	}
	
	public void insert(int key) {
		root = rec_i(root, key);
	}
	
	private Node rec_i(Node root, int key) {
		if(root == null) {//no data - simple add and return
			root = new Node(key);
			return root;
		}else {//more data - 1st go to correct place 2nd add(that will be done by base case), so only take care of 1st
			if(key < root.data)
				root.left = rec_i(root.left, key);
			else
				root.right = rec_i(root.right, key);
		}//we update left and right node -So return root 
		return root;
	}
	
	public void printInorder() {
		System.out.print("Inorder / (DFS) / Sorted	: ");
		rec_in(root);
		System.out.println();
	}
	
	private void rec_in(Node root) {
		if(root == null)
			return;
		else {
			rec_in(root.left);
			System.out.print(root.data+" ");
			rec_in(root.right);
		}
	}
	
	public void delete(int key) {
		root = rec_del(root, key);
	}
	
	private Node rec_del(Node root, int key) {
		/*
		if(root == null || root.data == key)//no data or empty tree
			return null;
		*/
		if(root == null)//no data or empty tree
			return null;
		else if(key < root.data)//delete data present on left side
			root.left =  rec_del(root.left, key);//dont return here - just updte left pointer
		else if(key > root.data)//delete data present on right side
			root.right =  rec_del(root.right, key);// return is taken care at the end of else
		else {//delete data present at root place
			//case 1: 0 child
			if(root.left == null && root.right == null)
				return null;
			
			//case 2: 1 child
			if(root.left == null)
				return root.right;//making connection + delink 
			if(root.right == null)
				return root.left;//making connection
			
			//case 3: 2 child
			//1st find min in right side
			Node t = minElementInRight(root.right);//from right side : find min
			//2nd copy that min ele in root place
			root.data = t.data;
			//3rd remove min element(i.e. t node) from tree - coz that value now present at root- see above step
			root.right = rec_del(root.right, t.data);//from righ side : delete t.data
		}
		return root;
	}

	private Node minElementInRight(Node root2) {
		Node temp = root;
		while(temp.left != null)//go left side for min for a given root node
			temp = temp.left;
		return temp;
	}
	
	public void levelOrderBFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		System.out.print("Level Order traversal (BFS) 	: ");
		while(!q.isEmpty()) {
			//1. print + remove
			Node t = q.poll();
			System.out.print(t.data+" ");
			
			//2. add child
			if(t.left != null)
				q.add(t.left);
			if(t.right != null)
				q.add(t.right);
			
		}
		System.out.println();
	}
	
	public void levelOrderReverseBFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		System.out.print("Reverse Level Order (BFS) 	: ");
		while(!q.isEmpty()) {
			Node temp = q.poll();
			System.out.print(temp.data+ " ");
			
			//add child from right to left
			int childCount = 0;
			if(temp.left !=  null)
				++childCount;
			if(temp.right != null)
				++childCount;
			
			if(childCount == 1) {//then only left is present
				q.add(temp.left);
			}
			else if(childCount == 2) {//both child present - add right 1st then left
				q.add(temp.right);
				q.add(temp.left);
			}
			
		}
		System.out.println();
	}
	
	public void ZigZagLelelOrderBFS() {
		Stack<Node> st1 = new Stack<>();
		Stack<Node> st2 = new Stack<>();
		
		st1.push(root);
		System.out.print("Level Order Zig Zag BFS 	: ");
		while(! st1.empty()) {
			
			while(!st1.empty()) {
				Node t = st2.peek();
				System.out.print(t.data+" ");
				st2.push(t);
			}
			
			//add child node - same order as in BFS Queue
			while(! st2.empty()) {
				Node t = st2.pop();
				
			}
		}
		System.out.println();
	}
	
}


public class BST_search_insert_delete_print_by_1_DFS_2_BFS_3_ReverseBFS_4_ZigZagBFS {
	public static void main(String[] args) {
		BstAllMethods obj = new BstAllMethods();
		obj.insert(15);obj.printInorder();
		obj.insert(5);obj.printInorder();
		obj.insert(20);obj.printInorder();
		obj.insert(3);obj.printInorder();
		obj.delete(3); obj.printInorder();
		obj.levelOrderBFS();
		obj.levelOrderReverseBFS();
		//obj.ZigZagLelelOrderBFS();
		
		obj.insert(3); obj.levelOrderBFS(); obj.printInorder();
		obj.insert(25); 
		obj.insert(17);
		obj.insert(8); obj.printInorder();
		obj.levelOrderBFS();
		obj.levelOrderReverseBFS();
		//obj.ZigZagLelelOrderBFS(); TODO : impl
		
	}
}
