package oldDefault;

class Node {
	String data;
	Node left;
	Node right;
	
	public Node(String data) {
		this.data = data;
	}
}
class BST {
	Node root;
	int count = 0;
	
	public int clumps() {
		rec(root);
		return count;
	}
	
//	public int rec1(Node root) {
//		if(root == null) return 0;
//		if(root.left == null && root.right == null) {
//			return 1;
//		}
//		
//		String rootData = root.data;
//		
//		int leftCount = rec1(root.left);
//		int rightCount = rec1(root.right);
//		
//		if(leftCount != 0) {//left present
//			if(rightCount != 0) {//right present
//				if(root.data != root.left.data && 
//					root.data != root.right.data) {
//					
//				}
//			}
//		}
//		
//		
//	}

	private void rec(Node root) {
		//base case
		if(root == null) 
			return;
		if(root.left == null && root.right == null) {//leaf
			count++;
		}
		
		rec(root.left);
		rec(root.right);
		
		String leftData = "";
		if(root.left != null) {
			leftData = root.left.data;
			
		}
		String rightData = "";
		if(root.right !=null) {
			rightData = root.right.data;
			
		}
		
		if(leftData == rightData) {//same child node
			if(root.data != rightData) {
				count++;
			}else {
				count--;
			}
		} else if(leftData != rightData) {//different child
			if(root.data != rightData) {
				count++;
			}
		}
	}
}

public class Test {
	public static void main(String[] args) {
		BST tree = new BST();
		
		tree.root = new Node("R");
		
		tree.root.left = new Node("R");
		tree.root.right = new Node("R");
		
		tree.root.left.left = new Node("G");
		tree.root.left.right = new Node("G");
		
		tree.root.right.left = new Node("R");
		tree.root.right.right = new Node("R");
		
		int clumps = tree.clumps();
		System.out.println(clumps);
		
	}
}
