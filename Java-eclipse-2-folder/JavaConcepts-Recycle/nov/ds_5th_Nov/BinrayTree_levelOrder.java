package ds_5th_Nov;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author Hariom Yadav - Nov 5, 2019
 *
 */
//data structure used : Queue + Linked List

/*
class Node { //Linked List
	//know some
	int data;
	Node left, right;
	
	//does some
	Node(int v){//not a mtrhod
		data = v;
		left = right = null;
	}
	
}
*/

//use of data structure 
class LevelOrder{
	//know something
	Node root;
	
	//does something
	void printLevel(){
		Queue<Node> qu = new LinkedList<Node>();// Queue
		qu.add(root);
		
		while(!qu.isEmpty()) {
			//1. delete/dequeue front element from Queue
			Node temp = qu.poll();//return + removes
			System.out.print(temp.data+" ");
			
			//2. add/enQueue child element into Queue
			if(temp.left != null)
				qu.add(temp.left);
			
			if(temp.right != null)
				qu.add(temp.right);
		}
	}
	
}

//test data structure
public class BinrayTree_levelOrder {
	public static void main(String[] args) {
		LevelOrder obj = new LevelOrder();
		obj.root = new Node(1);
		obj.root.left = new Node(2);
		obj.root.right = new Node(3);
		obj.printLevel();
		System.out.println("");
		obj.root.left.left = new Node(4);
		obj.root.left.right = new Node(5);
		obj.root.right.left = new Node(6);
		obj.root.right.right = new Node(7);
		
		obj.printLevel();
		System.out.println("");
	}
}
