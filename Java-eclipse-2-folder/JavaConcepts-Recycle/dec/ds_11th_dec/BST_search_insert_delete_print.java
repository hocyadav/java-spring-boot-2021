package ds_11th_dec;
/**
 * 
 * @author Hariom Yadav | 11-Dec-2019
 *
 */

//data structure for BST
class Node2{
	int data;
	Node2 left, right;
	
	public Node2(int data) {
		super();
		this.data = data;
	}
}

//use of data structure 
class BST{
	Node2 root;
	
	public void search(int v) {
		Node2 t = rec_search(root, v);
		if(t != null)
			System.out.println("Found "+v);
		else
			System.out.println("Not found "+v);
	}
	
	private Node2 rec_search(Node2 root, int v) {
		if(root == null || root.data == v) 
			return root;
		else {
			if(v < root.data)
				return rec_search(root.left, v);
			else
				return rec_search(root.right, v);
		}
	}
	
	public void insert(int v) {
		root = rec_insert(root, v);
	}

	private Node2 rec_insert(Node2 root, int v) {
		if(root == null) {
			Node2 nn = new Node2(v);
			root = nn;
			return root;
		}else {//update left and right Node2
			if(v < root.data)
				root.left = rec_insert(root.left, v);
			else
				root.right = rec_insert(root.right, v);
		}
		return root;
	}
	
	public void delete(int v) {
		root = rec_del(root, v);
	}

	private Node2 rec_del(Node2 root, int v) {
		if(root == null)
			return null;
		else if(v < root.data)//element at left side 
			root.left = rec_del(root.left, v);//updating left root pointer 
		else if(v > root.data)
			root.right = rec_del(root.right, v);//updating right root pointer
		else {//element present at root + updating left , right root pointer 
			//case 1: 0 child element 
			if(root.left == null && root.right == null )
				return null;
			
			//case 2: 1 child
			if(root.left == null)
				return root.right;
			if(root.right == null)
				return root.left;
			
			//case 3: 2 child
			Node2 t = minNode(root.right);//3.1.find min in left side
			root.data = t.data;//3.2.replace root data with min data
				//3.3.delete that min value from right side
			root.right = rec_del(root.right, t.data);
		}
		
		return root;//above after update + return root value
	}
	/**
	 * Return min element present : go left most
	 * @param right
	 * @return
	 */
	
	private Node2 minNode(Node2 right) {
		Node2 t = right;
		while(t.left != null)
			t = t.left;
		return t;
	}
	
	public void printInorder() {
		System.out.print("Inorder : ");
		rec_inorder(root);
		System.out.println();
	}

	private void rec_inorder(Node2 root) {
		if(root == null)
			return;
		
		rec_inorder(root.left);
		System.out.print(root.data+" ");
		rec_inorder(root.right);
	}
}

//test data structure 
public class BST_search_insert_delete_print {
	public static void main(String[] args) {
		BST obj = new BST();
		obj.delete(12);obj.printInorder();
		obj.insert(12); obj.printInorder();
		obj.insert(1); obj.printInorder();
		obj.insert(10); obj.printInorder();
		obj.delete(12); obj.printInorder();
	}
}
/**
Inorder : 
Inorder : 12 
Inorder : 1 12 
Inorder : 1 10 12 
Inorder : 1 10 

*/