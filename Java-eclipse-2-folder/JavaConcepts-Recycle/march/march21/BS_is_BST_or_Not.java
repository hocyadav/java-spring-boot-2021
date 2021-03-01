package march21;

class Node{
	int data;
	Node left, right;
	
	public Node(int d) {
		data = d;
	}
}

class BSTOrNot{
	Node root;
	
	public void isBSTorNot() {
		boolean b = rec_bst(root, null, null);
		if(b == true)
			System.out.println("bst..");
		else
			System.out.println("not a bst..");
	}

	private boolean rec_bst(Node root, Integer min, Integer max) {
		if(root == null) 
			return true;
		if((min != null && root.data <= min) || (max != null && root.data >= max))
			return false;
		
		boolean leftTree = rec_bst(root.left, min, root.data);
		boolean rightTree = rec_bst(root.right, root.data, max);
		
		return leftTree && rightTree;
	}
}

public class BS_is_BST_or_Not {

	public static void main(String[] args) {
		BSTOrNot tree = new BSTOrNot();
		tree.root = new Node(Integer.MAX_VALUE);
		tree.root.left = new Node(Integer.MAX_VALUE);
		
		tree.isBSTorNot();
	}
}
