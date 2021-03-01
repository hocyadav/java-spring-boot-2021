package coding_11th_dec_night;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Hariom Yadav | 11-Dec-2019
 * https://www.geeksforgeeks.org/print-left-view-binary-tree/
 * level order traversal and print 1st element
 */

class BinaryTree{
	Node root;
	HashMap<Integer, List<Node> > map = new HashMap<>();

	public void leftView() {
		storeEachLevelInHashMap(root,0);//1. Store level order in map
		System.out.print("Left view of BT : ");
		print1stValueFromLevelOrder();//2. Get 1st element from map list
	}

	private void print1stValueFromLevelOrder() {
		Iterator it = map.entrySet().iterator();//get entry set obj(all key value will be iterator) --> then iterator from that obj
		while(it.hasNext()) {
			Map.Entry mm = (Map.Entry)it.next();//get entry obj
			List<Node> l = (List<Node>) mm.getValue();//type cast value
			//System.out.println(l);
			System.out.print(l.get(0).data +" ");
		}
	}

	private void storeEachLevelInHashMap(Node root, int level) {
		if(root == null) return;
		
		List<Node> listOfNodeAtEachLevel = map.get(level);
		
		if(listOfNodeAtEachLevel == null) {
			listOfNodeAtEachLevel = new LinkedList<Node>();
			listOfNodeAtEachLevel.add(root);
		}else {
			listOfNodeAtEachLevel.add(root);
		}
		
		map.put(level, listOfNodeAtEachLevel);
		
		storeEachLevelInHashMap(root.left, level+1);
		storeEachLevelInHashMap(root.right, level+1);
	}
}

public class Left_View_Of_Binary_Tree_using_DFS_print1stNode_using_HashMap {
	public static void main(String[] args) {
		BinaryTree obj = new BinaryTree();
		obj.root = new Node(4);
		
		obj.root.left = new Node(5);
		obj.root.right = new Node(2);
		
		obj.root.right.left = new Node(3);
		obj.root.right.right = new Node(1);
		obj.root.right.left.left = new Node(6);
		obj.root.right.left.right = new Node(7);		
		
		obj.leftView();
		//System.out.println(obj.map);
	}
}
/**
Left view of BT : 4 5 3 6 
 */
