package july5th;
/** Que :https://www.programcreek.com/2014/07/leetcode-largest-bst-subtree-java/ 
 *  Best simple Ans : https://www.youtube.com/watch?v=TSEeSXNfl04
 * **/
class Node {
	 int data;
	 Node left;
	 Node right;
	
	public Node(int data) {
		super();
		this.data = data;
	}
}

public class LargestBST_Subtree_InBS {
	public static void main(String[] args) {
		Node root = new Node(10);
		root.left = new Node(5);
		root.right = new Node(15);
		
		root.left.left = new Node(1);
		root.left.right = new Node(8);
		
		root.right.right = new Node(7);
		printBSTInorder(root);
		System.out.println();
		System.out.println(largestBST(root));
		
	}
	
	public static int largestBST(Node root) {
		if(root == null) return 0;
		if(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
			return size_(root);
		}
		return Math.max(largestBST(root.left), largestBST(root.right));
	}

	private static int size_(Node root) {
		if(root == null) return 0;
		return 1 + size_(root.left) + size_(root.right);
	}

	private static boolean isBST(Node root, int minValue, int maxValue) {
		if(root == null) return true;
		if(root.data < minValue || root.data > maxValue) return false;
		
		return isBST(root.left, minValue, root.data - 1) &&
				isBST(root.right, root.data +1, maxValue);
	}
	
	public static void printBSTInorder(Node root) {
		if(root == null) return;
		printBSTInorder(root.left);
		System.out.print(root.data+" ");
		printBSTInorder(root.right);
	}
}
