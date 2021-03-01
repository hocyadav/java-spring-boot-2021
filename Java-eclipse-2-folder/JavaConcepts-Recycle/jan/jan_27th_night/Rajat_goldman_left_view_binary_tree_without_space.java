package jan_27th_night;

class Node {
	int data;
	Node left, right;
	
	public Node(int d) {
		data = d;
	}
}


class BinaryTree {
	Node root;
	public static int max_height;
	
	public void inorder() {
		System.out.print("Inorder : ");
		rec_inorder(root);
		System.out.println();
	}

	private void rec_inorder(Node root) {
		if(root == null) {
			return;
		}
		rec_inorder(root.left);
		System.out.print(root.data+" ");
		rec_inorder(root.right);
	}
	
	public void height(int h) {
		Node t = rec_ht(root, h);
		if(t != null) {
			//System.out.println(h+" : "+t.data);
			System.out.print(t.data+" ");
		} else {
			//System.out.println(h+" : null");
			System.out.print(" ");
		}
	}

	private Node rec_ht(Node root, int h) {
		if(h == 0 || root == null) {
			return root;
		}
		Node leftSide	= rec_ht(root.left, h-1);
		Node rightSide 	= rec_ht(root.right, h-1);
		
		if(leftSide != null) {
			return leftSide;
		} else {
			return rightSide;
		}
		
	}
	
	public void heightOfBT() {
		int t = maxDepth(root);
		max_height = t;
		//System.out.println("Height of BT : "+t);
	}
	
	public int maxDepth(Node node)  
    { 
        if (node == null) 
            return 0; 
        else 
        { 
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.left); 
            int rDepth = maxDepth(node.right); 
   
            /* use the larger one */
            if (lDepth > rDepth) 
                return (lDepth + 1); 
             else 
                return (rDepth + 1); 
        } 
    } 
	
}


public class Rajat_goldman_left_view_binary_tree_without_space {

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.right.left = new Node(4);
		tree.root.right.right = new Node(5);
		tree.root.left.right = new Node(6);
		tree.root.left.left = new Node(7);
		tree.root.left.right.left = new Node(88);
		tree.root.left.left.left = new Node(99);
//		tree.inorder();
//		tree.height(0);
//		tree.height(1);
//		tree.height(2);
//		tree.height(3);
		tree.heightOfBT();//order of n
		
		
		for(int i = 0; i < tree.max_height; i++) {//order of n
			tree.height(i);//order of n
		}//total : order of n^2
		
	}
}
