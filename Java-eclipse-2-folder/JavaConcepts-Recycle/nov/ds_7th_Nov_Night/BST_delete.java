package ds_7th_Nov_Night;
/**
 * 
 * @author Hariom Yadav - Nov 7, 2019
 *
 */
//ds
class Node{
	int data;
	Node left, right;
	
	Node(int v){
		data = v;
	}
}

//use of DS
class BST{
	//know something
	Node root;
	
	//does something
	int min(Node root) {
		Node t = root;
		int min = root.data;
		while(t != null) {
			min = t.data;
			t = t.left;
		}
		return min;
	}
	
	void delete(int key) {
		System.out.println("delete "+key+" ");
		root = del_rec(root, key);
	}

	private Node del_rec(Node root, int key) {//30 min
		//base case
		if(root == null) return root;
		//if(root.data == key) return null; this case we are handling in else block
		
		if(key < root.data)//go left
			root.left = del_rec(root.left, key);
		else if(key > root.data)//go right
			root.right = del_rec(root.right, key);
		else {
			//case 1: 0 child
			if(root.left == null && root.right == null) {
				return null;
			}else if(root.left == null)//case 2: 1 child
				return root.right;//make connection :  linking dot dot - - - 
			else if(root.right == null)
				return root.left;//make connection
			else {
				//case 3 : 2 child -- reduce to case 2 / case 1
				//find root right side minimum & set that data to root node
				int t = min(root.right);
				//store in root place
				root.data = t;
				//dete that t data in right side : that is recursion and that will hit base case i.e. child with no root
				//now delete key that is present in right side since that key is dublicate
				root.right = del_rec(root.right, root.data);
			}
		}
		return root;
		//return null;
	}
	
	
	void insert(int key) {
		root = inc_rec(root,key);
	}
	
	private Node inc_rec(Node root, int key) {//null then remove
		if(root == null) {
			root = new Node(key);
			return root;
		}else {//updating left and right root node and then returning root below else
			if(key < root.data)
				root.left = inc_rec(root.left, key);
			else
				root.right = inc_rec(root.right, key);
		}
		return root;
	}
	
	void inorder() {
		in_rec(root);
	}
	private void in_rec(Node root) {
		if(root == null) return;
		else {
			in_rec(root.left);
			System.out.print(root.data+" ");
			in_rec(root.right);
		}
	}
	
}


public class BST_delete {
	public static void main(String[] args) {
		BST obj = new BST();
		obj.root = new Node(10);
		obj.root.left = new Node(8);
		obj.root.right = new Node(15);
		obj.inorder();
		System.out.println("");
		obj.delete(10);obj.inorder();System.out.println();
		obj.insert(10);obj.inorder();System.out.println();
		obj.delete(8);obj.inorder();System.out.println();
		obj.insert(8);
		obj.inorder();
		
	}
}
