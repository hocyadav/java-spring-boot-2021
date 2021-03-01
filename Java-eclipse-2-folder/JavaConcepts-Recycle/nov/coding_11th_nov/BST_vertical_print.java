package coding_11th_nov;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;

/**
 * 
 * @author Hariom Yadav - Nov 11, 2019
 *
 */

//data structure 
class Node{
	int data;
	Node left, right;
	
	public Node(int data) {
		this.data = data;
	}
}

//use of DS
class BST{
	Node root;

	public void insert(int i) {
		// TODO Auto-generated method stub
		root = ins_rec(root, i);
		
	}

	private Node ins_rec(Node root, int key) {
		if(root == null) {//in this case root value change that why return type is Node
			root = new Node(key);
			return root;
		}else {
			if(key < root.data)//update left and right
				root.left = ins_rec(root.left, key);
			else
				root.right = ins_rec(root.right, key);
		}
		return root;//after update left and right return root
	}

	public void inorder() {
		// TODO Auto-generated method stub
		System.out.print("Inorder : ");
		inor_rec(root);
		System.out.println();
		
	}
	private void inor_rec(Node root) {
		if(root == null) return;
		else {
			inor_rec(root.left);
			System.out.print(root.data+" ");
			inor_rec(root.right);
		}
		
	}

	
	public void printVerticalBST() {
		//declare hash map - with key and value as linked list
		//HashMap<Integer, Vector<Node>> hmap = new HashMap<Integer, Vector<Node>>();
		//take treemap that will be sorted
		Map<Integer, Vector<Integer>> hmap = new TreeMap<>();
		//TreeMap<Integer, Vector<Integer>> hmap = new TreeMap<>();//above and below line are same
		//call inorder and save in this hmap
		int distance = 0;//root as 0 distance , move left and -1, right +1
		//verticalUtility(root, hmap, hmap.size(), distance);
		//verticalUtility(root, hmap, distance, hmap.size());
		verticalUtility(root, hmap, distance);
		
		System.out.println("BST Vertical Order : ");
		//print the tree map - entry wise
		for(Entry<Integer, Vector<Integer>> t : hmap.entrySet()) {
			System.out.println(t.getValue());
		}
		
	}
	/**
	 * update hmap instance
	 * @param hmap
	 * @param size
	 * @param distance
	 */
	private void verticalUtility(Node root, Map<Integer,Vector<Integer>> hmap, int distance) {
		//get distance value from hasmap and return vector(linked list), 
		if(root == null)
			return;
		
		//store current data in dynamic array(Vector / LL) 
		Vector<Integer> arrLL = hmap.get(distance);//get all data at particular distance value, we will get 0 or more
		if(arrLL == null) {//if list not present then create add data
			arrLL = new Vector<Integer>();
			arrLL.add(root.data);
		}else//simple append with old data
			arrLL.add(root.data);
		
		//now put these in hmap
		hmap.put(distance, arrLL);//distance = 0 : mapped with arraylist that contain all node that have distance 0

		//now call left side
		verticalUtility(root.left, hmap, distance-1);
		
		//now call right side
		verticalUtility(root.right, hmap, distance+1);
	}
	
}


public class BST_vertical_print {
	public static void main(String[] args) {
		BST obj = new BST();
		obj.insert(10);obj.insert(7);obj.insert(8);
		obj.insert(13);
		obj.insert(11);obj.insert(15);
		obj.insert(12);
		obj.insert(14);
		obj.insert(9);
		obj.inorder();
		
		obj.printVerticalBST();
	}
}
/**
 *console output

Inorder : 7 8 9 10 11 12 13 14 15 
BST Vertical Order : 
[7]
[10, 8, 11]
[9, 13, 12, 14]
[15]

 */
