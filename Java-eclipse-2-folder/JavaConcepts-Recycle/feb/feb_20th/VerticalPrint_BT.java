package feb_20th;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
/**
 * 
 * @author Hariom Yadav | 20-Feb-2020
 *
 */
class Node {
	int data;
	Node left, right;
	public Node(int d){//default only use inside same package
		data = d;
	}
}
class BinaryTree {
	Node root;
	
	public void verticalPrint() {
		Map<Integer, Vector<Integer>> map = new HashMap();
		rec_vertical(root, map, 0);
		
		//print
		Set<Map.Entry<Integer, Vector<Integer>>> set = map.entrySet();
		for(Map.Entry<Integer, Vector<Integer>> s : set) {
			System.out.println(s.getValue());
		}
		
	}

	private void rec_vertical(Node root, Map<Integer, Vector<Integer>> map, int distance) {
		if(root == null) {
			return;
		}
		
		Vector<Integer> list = map.get(distance);
		if(list == null) {
			list = new Vector();
		}
		list.add(root.data);
		
		map.put(distance, list);
		
		rec_vertical(root.left, map, distance - 1);
		rec_vertical(root.right, map, distance + 1);
		
	}
	
	
}
public class VerticalPrint_BT {
	public static void main(String[] args) {
		BinaryTree obj = new BinaryTree();
		obj.root = new Node(100);
		
		obj.root.left = new Node(50);
		obj.root.right = new Node(150);
		
		obj.root.left.left = new Node(15);
		obj.root.left.right = new Node(40);
		
		obj.verticalPrint();
	}
}
/**
[100, 40]
[50]
[15]
[150]

 */
