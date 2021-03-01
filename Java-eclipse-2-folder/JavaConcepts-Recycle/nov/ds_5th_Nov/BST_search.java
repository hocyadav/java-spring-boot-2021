package ds_5th_Nov;
/**
 * 
 * @author Hariom Yadav - Nov 5, 2019
 *
 */
//ds - Node

//use of DS
class BinaryTree{
	//know some
	Node root;
	
	//does some
	public Node search(Node root,int key){
		if(root == null || root.data == key) {
			return root;
		}else {
			if(root.data < key) {
				return search(root.right, key);
			}else {
				return search(root.left, key);
			}
		}
	}
	
	void insert(Node root, int key){//go to leaf node && insert
		Node temp = search(root, key);
		if(temp != null) {
			if(key > temp.data) 
				temp.right = new Node(key);
			else
				temp.left = new Node(key);
		}else {
			root = new Node(key);;
		}
	}
	
	public Node insert_rec(Node root, int key) {//go leaf node and insert
		if(root == null) {//1st element
			root = new Node(key);
			return root;
		}else {
			if(key > root.data)//go right
				root.right = insert_rec(root.right, key);
			else//go left
				root.left = insert_rec(root.left, key);
		}
		return root;
	}
	
}

public class BST_search {
	public static void main(String[] args) {
		BinaryTree obj = new BinaryTree();
		obj.root = new Node(10);
		obj.root.left = new Node(7);
		obj.root.right = new Node(14);
		obj.root.left.left = new Node(6);
		obj.root.left.right = new Node(8);
		obj.root.right.left = new Node(11);
		obj.root.right.right = new Node(15);
		
		
		for(int i=0;i<16; i++) {
			Node temp = obj.search(obj.root, i);
			if(temp != null)
				System.out.println(temp.data+" : "+i);
			else
				System.out.println("no data : "+i);
		}
		
	}
}
