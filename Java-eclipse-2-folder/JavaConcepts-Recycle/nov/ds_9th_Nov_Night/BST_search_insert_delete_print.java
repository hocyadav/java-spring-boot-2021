package ds_9th_Nov_Night;
/**
 * 
 * @author Hariom Yadav - Nov 9, 2019
 *
 */
//Data structure
class Node{
	//know something
	int data;
	Node left, right;
	//does something
	Node(int data){//no need to initialize left and right, sice constructor initialize non primitive data as null
		this.data = data;
	}
}

//use of Data structure
class Bst{
	//field
	Node root;
	
	//methods
	boolean search(int key){
		return (se_rec(root,key) != null) ? true : false;
	}
	private Node se_rec(Node root, int key) {
		if(root == null || key == root.data)
			return root;
		else {
			if(key < root.data)
				return se_rec(root.left, key);
			else
				return se_rec(root.right, key);
		}
	}
	
	void insert(int key) {
		root = in_rec(root, key);
	}
	/**
	 * Helper Method to insert 
	 * @param root - root node address
	 * @param key - insert key
	 * @return - updated root
	 * 
	 */
	private Node in_rec(Node root, int key) {
		if(root == null) {
			root = new Node(key);
			return root;
		}else {//update left and right side
			if(key < root.data)
				root.left = in_rec(root.left, key);
			else
				root.right = in_rec(root.right, key);
		}
		return root;//after update left and right return main head root
	}
	
	void inorder() {
		System.out.print("INOREDR : ");
		ino_rec(root);
		System.out.println("");
	}
	private void ino_rec(Node root) {
		if(root == null) return;
		else {
			ino_rec(root.left);
			System.out.print(root.data+" ");
			ino_rec(root.right);
		}
	}
	/**
	 * @param key
	 * key to be deleted
	 * @param key2
	 * key2 to be deleted
	 */
	void delete(int key) {
		System.out.println("Delete : "+key + " ");
		root = del(root, key);
	}
	private Node del(Node root, int key) {
		if(root == null) return null;
		
		else if(key < root.data)//go left + update left root pointer
			root.left = del(root.left, key);
		else if(key > root.data)//go right
			root.right = del(root.right, key);
		else {//Dont go anywhere found --> : case 1,2,3
			//case 1: 0 child
			if(root.left == null && root.right == null) {
				return null;
			}else if(root.left == null) {//case 2 : 1 child - right side
				return root.right;
			}else if(root.right == null) {//case 2 : 1 child - left side
				return root.left;
			}else {// 2 child - left and right both side
				//1. find min in right side
				int t = min(root.right);
				
				//2. replace current address i.e. root with min value
				root.data = t;
				
				//3. delete that min value in right side - call right side recursion
				root.right = del(root.right, t);
				
			}
		}
		return root;
	}

	private int min(Node right) {
		//go left most
		Node temp = right;
		int min = temp.data;
		while(temp != null) {
			min = temp.data;
			temp = temp.left;
		}
		return min;
	}
	
}

public class BST_search_insert_delete_print {
	public static void main(String[] args) {
		Bst obj = new Bst();
		obj.inorder();
		obj.insert(10);obj.inorder();
		obj.insert(1);obj.insert(100);obj.insert(20); obj.inorder();
		System.out.println("10 : "+obj.search(10));
		System.out.println("20 : "+obj.search(20));
		System.out.println("11 : "+obj.search(11));
		
		obj.delete(100);
		obj.inorder();
		
		
		obj.delete(20);
		obj.inorder();
		obj.delete(1);
		obj.inorder();
		
		obj.delete(10);
		obj.inorder();
		
		obj.delete(100);
		obj.inorder();
		
		
		
	
		
		
		
	}
}
