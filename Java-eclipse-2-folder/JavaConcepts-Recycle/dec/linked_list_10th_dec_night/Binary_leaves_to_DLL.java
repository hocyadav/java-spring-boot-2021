package linked_list_10th_dec_night;
/**
 * 
 * @author Hariom Yadav | 10-Dec-2019
 *
 */

class Node{
	int data;
	Node left;
	Node right;
	
	public Node(int v) {
		data = v;
	}
}

class BinaryTree{
	Node root;
	Node dllHead;
	
	public BinaryTree() {
		root = null;
		dllHead = null;
	}
	
	public void printInorder() {
		System.out.print("Inorder : ");
		rec_inorder(root);
		System.out.println();
	}

	private void rec_inorder(Node root) {
		if(root == null) 
			return;
		
		rec_inorder(root.left);
		System.out.print(root.data+" ");
		rec_inorder(root.right);
	}
	
	public void binaryTreeLeavesToDLL() {
		root = rec_binaryTreeLeavesToDLL(root);//root will update only in case of single node
	}
	/**
	 * for a given node n store Binary leaves into DLL
	 * @param n
	 * @return
	 */
	private Node rec_binaryTreeLeavesToDLL(Node n) {
		//leave node : add that node to dllHead + remove from tree
		if(n == null) 
			return null;
		if(n.left == null && n.right == null) {//leave node : both left and right null
			//1. add leave node to dllHead
			n.right = dllHead;//add at 1st place: beginning
			if(dllHead != null)//except for first time dll : coz 1st time dll head is null so left of dll head will not update
				dllHead.left = n;
			dllHead = n;//move dll head to left side : adding node left i.e. at 1st place like LL
			//2. remove that leave node from tree
			return null;//since leave node so parent will now point to null..simply deleting leave node
		}else {//not a leave node, call rec for left and right and update left and right node
			n.right = rec_binaryTreeLeavesToDLL(n.right);//if right is leave then null will assign : that means leave is deleted
			n.left = rec_binaryTreeLeavesToDLL(n.left);
			return n;
		}
	}
	
	public void printDLL() {
		Node t = dllHead;
		
		System.out.print("leaves DLL : ");
		
		while(t != null) {
			System.out.print(t.data+" ");
			t = t.right;
		}
		
		System.out.println();
	}
	
}


public class Binary_leaves_to_DLL {
	public static void main(String[] args) {
		BinaryTree obj = new BinaryTree();
		obj.root = new Node(1); 
		obj.root.left = new Node(2); 
		obj.root.right = new Node(3); 
   
		obj.root.left.left = new Node(4); 
		obj.root.left.right = new Node(5); 
		obj.root.right.right = new Node(6); 
        obj.root.left.left.left = new Node(7); 
        obj.root.left.left.right = new Node(8); 
        obj.root.right.right.left = new Node(9); 
        obj.root.right.right.right = new Node(10); 
        obj.printInorder();
		
		obj.binaryTreeLeavesToDLL(); obj.printDLL();
		obj.printInorder();
		
		
	}
}
/**
Inorder : 7 4 8 2 5 1 3 9 6 10 
leaves DLL : 7 8 5 9 10 
Inorder : 4 2 1 3 6 


*/