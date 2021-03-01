package ds_5th_Nov_Evening;
/**
 * 
 * @author Hariom Yadav - Nov 5, 2019
 *
 */
//ds
class Node{
	int data;
	Node left, right;
	//does something
	Node(int v){//not a method- allocate obj memory
		data = v;
	}
}

//use of DS
class BinaryST{
	//know
	Node root;
	
	//does something
	void insert(int v){//call rec
		root = insert_rec(root, v);
	}
	private Node insert_rec(Node root1, int v) {//go leaf node and insert
		if(root1 == null) {
			root1 = new Node(v);
			return root1;
		}else {
			if(v < root1.data) {//go left
				root1.left = insert_rec(root1.left, v);
			}else {//go right
				root1.right = insert_rec(root1.right, v);
			}
		}
		return root1;
	}
	
	void delete() {
		root = del_rec(root);
	}
	
	private Node del_rec(Node root2) {
		//tree empty - base case
		if(root == null) return root;
		return null;
	}
	void inorder() {
		in_rec(root);
	}
	
	private void in_rec(Node rt) {
		if(rt == null) return;
		else {
			in_rec(rt.left);
			System.out.print(rt.data+" ");
			in_rec(rt.right);
		}
		//System.out.println("");
	}
}


public class BST_delete {
	public static void main(String[] args) {
		BinaryST obj = new BinaryST();
		//obj.root = new Node(1);
		
		//obj.root.left = new Node(2);
		//obj.root.right = new Node(3);
		obj.insert(3);
		obj.insert(1);
		obj.insert(2);
		obj.inorder();
		//obj.inorder();
		
	}
}
