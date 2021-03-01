package ds_11th_dec;
/**
 * 
 * @author Hariom Yadav | 11-Dec-2019
 *
 */
//ds
class Node{
	int data;
	Node left, right;
	
	public Node(int data) {
		super();
		this.data = data;
	}
}
//use of ds
class BST2{
	//know something
	
	
	Node root;
	Node circularDll;
	
	public void bst_to_circularDLL() {
		circularDll = rec_bstCircularDll(root);
	}

	private Node rec_bstCircularDll(Node root) {
		
		if(root == null)
			return null;
		
		//1. call recursion left + right
		Node left = rec_bstCircularDll(root.left);
		Node right = rec_bstCircularDll(root.right);
		
		//2. circular DLL : combine 3 circulare dll : 1st root, 2nd left side , 3rd right side
		//1st root : make single node root as CLL
		root.left = root;
		root.right = root;
		
		//2nd combine left and right with root Circular dll
		//1st time left and right will be null : so null + root + null --> will form circular dll
		Node t1 = concatenate2CircularDll(left, root);
		Node t2 = concatenate2CircularDll(t1, right);
		
		return t2;//return combine circular dll
	}

	private Node concatenate2CircularDll(Node leftCircularDll, Node rightCircularDll) {
		//if anyone list is empty return other one
		if(leftCircularDll == null)
			return rightCircularDll;
		if(rightCircularDll == null)
			return leftCircularDll;
		
		//input is 1st node, now find last node of circular dll
		Node leftLast = leftCircularDll.left;
		Node rightLst = rightCircularDll.left;
		
		//join 2 cicular dll : total 4 connection : 1st 2 is inside , other 2 is outer
		leftLast.right = rightCircularDll;//1st connection
		rightCircularDll.left = leftLast;//2nd 
		
		leftCircularDll.left = rightLst;//3rd
		rightLst.right = leftCircularDll;//4th
		
		return leftCircularDll;
	}
	
	public void printCircularDll() {
		System.out.print("Circular dll : ");
		
		if(circularDll == null) {
			System.out.println();
			return;
		}
		Node t = circularDll;
		do {
			System.out.print(t.data+" ");
			t = t.right;
		}while(t != circularDll);
		
		System.out.println();
	}
	
	public void printInorder() {
		System.out.print("Inorder : ");
		rec_inorder(root);
		System.out.println();
	}

	private void rec_inorder(Node root2) {
		if(root2 == null)
			return;
		rec_inorder(root2.left);
		System.out.print(root2.data+" ");
		rec_inorder(root2.right);
	}
	
}
//test ds
public class BinaryTree_to_circularDLL {
	public static void main(String[] args) {
		BST2 obj = new BST2();
//		obj.root = new Node(12);obj.printInorder();
//		obj.printCircularDll();
//		
//		obj.root.left = new Node(20);obj.printInorder();
//		obj.root.right = new Node(100);obj.printInorder();
		
		obj.root = new Node(10); 
		obj.root.left = new Node(12); 
		obj.root.right = new Node(15); 
		obj.root.left.left = new Node(25); 
		obj.root.left.right = new Node(30); 
		obj.root.right.left = new Node(36);
		obj.printInorder();
		
		obj.bst_to_circularDLL(); obj.printCircularDll();
		
	}
}
/**
Inorder : 25 12 30 10 36 15 
Circular dll : 25 12 30 10 36 15 
 */
