package jan_18th;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;
import java.util.Vector;

class Node{
	int data;
	Node left, right;
	
	public Node(int d) {
		data = d;
	}
}


class BST{
	Node root;
	
	public void insert(int key) {
		root = rec_ins(root, key);
	}

	private Node rec_ins(Node root, int key) {
		if(root == null) {
			root = new Node(key);
			return root;
		}else {//update left , right
			if(key < root.data)
				root.left = rec_ins(root.left, key);
			else
				root.right = rec_ins(root.right, key);
		}
		return root;
	}
	
	public void inorder() {
		// TODO Auto-generated method stub
		System.out.print("Inorder : ");
		inor_rec(root);
		System.out.println("\n");
		
	}
	
	private void inor_rec(Node root) {
		if(root == null) return;
		else {
			inor_rec(root.left);
			System.out.print(root.data+" ");
			inor_rec(root.right);
		}
		
	}

	//1. hashmap to store verical
	//2. distance = 0
	//3. recursion method for vertical call
	public void printVertical() {
		TreeMap<Integer, Vector<Integer>> hmap = new TreeMap<>();
		int distance = 0;
		rec_vertical(root, hmap, distance);
		
		for(Entry<Integer, Vector<Integer>> it :hmap.entrySet()) {
			System.out.println(it.getValue());
		}
		System.out.println();
	}

	//1. hmap.get (distance) + add current root value
	//2. update hmap.put()
	//3. call recursion distance -1 +1
	private void rec_vertical(Node root, TreeMap<Integer, Vector<Integer>> hmap, int distance) {
		if(root == null)
			return;
		
		
		Vector<Integer> list =  hmap.get(distance);
		if(list == null) {
			list = new Vector<Integer>();
			list.add(root.data);
		}else {
			list.add(root.data);
		}
		
		hmap.put(distance, list);
		
		rec_vertical(root.left, hmap, distance-1);
		rec_vertical(root.right, hmap, distance+1);
	}
	
	//1. queue
	//1.2. add 1st root node 
	//2. enqueue and print
	//3. call child of enq obj + dequeue
	public void levelOrder() {
		Queue<Node> qq = new LinkedList<Node>();
		qq.add(root);
		
		while(!qq.isEmpty()) {
			Node nn = qq.poll();
			System.out.print(nn.data+" ");
			
			if(nn.left != null)
				qq.add(nn.left);
			if(nn.right != null)
				qq.add(nn.right);
			
		}
		System.out.println("\n");
	}
	
	//1. hashmap key value pair
	//2. level = 0 and call recursion
	//3. recursion method
	HashMap<Integer, List<Integer>> hmap = new HashMap<>();
	public void levelOrder_HashMap() {
		int level =0;
		rec_levelOrderHashMap(hmap, root,level);
		
		for(Map.Entry mp: hmap.entrySet()) {
			System.out.println(mp.getKey() +" "+mp.getValue());
		}
	}

	private void rec_levelOrderHashMap(HashMap<Integer, List<Integer>> hmap2, Node root, int level) {
		
		if(root == null)
			return;
		
		List<Integer> list = hmap2.get(level);
		if(list == null) {
			list = new LinkedList<Integer>();
			list.add(root.data);
		}else {
			list.add(root.data);
		}
		
		hmap2.put(level, list);
		
		rec_levelOrderHashMap(hmap2, root.left, level+1);
		rec_levelOrderHashMap(hmap2, root.right, level+1);
	}
}


public class BST_vertical_print_levelOrder_print_imp {
	public static void main(String[] args) {
		BST obj = new BST();
		obj.insert(12);
		obj.insert(10);
		obj.insert(20);
		obj.insert(11);
		obj.insert(13);
		obj.insert(9);

		obj.inorder();
		
		System.out.println("Vertical order : ");
		obj.printVertical();
		
		System.out.println("Level order 1: ");
		obj.levelOrder();
		
		System.out.println("Level order 2: ");
		obj.levelOrder_HashMap();
	}
}
