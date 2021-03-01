package feb_11th;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author Hariom Yadav | 11-Feb-2020
 *	Mirror BST using level order traversal
 */
class BST2 extends BST{
	public void BST_Mirror2() {
		Queue<Node> qq = new LinkedList<>();
		qq.add(root);
		
		while(!qq.isEmpty()) {
			Node t = qq.poll();
			
			//swap
			Node temp = t.left;
			t.left = t.right;
			t.right = temp;
			
			//add
			if(t.left != null)
				qq.add(t.left);
			if(t.right != null)
				qq.add(t.right);
			
		}
		
		
	}
}


public class BST_mirror_Queue_levelOrder {
	public static void main(String[] args) {
		BST2 obj = new BST2();
		obj.inorder();
		obj.root = new Node(12);
		obj.root.left = new Node(6);
		obj.root.left.left = new Node(2);
		obj.root.left.left.left = new Node(1);
		obj.inorder();
		obj.BST_Mirror2();
		System.out.println();
		obj.inorder();
	}
}

/**
Inorder : 
Inorder : 1 2 6 12 

Inorder : 12 6 2 1 

*/