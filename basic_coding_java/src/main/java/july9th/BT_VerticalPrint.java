package july9th;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


class Node{
	int data;
	Node left;
	Node right;
	
	public Node(int data) {
		this.data = data;
	}
}

class BT{
	Node root;
	
	
	public void printVertical(){
		//map to store diatance and list
		Map<Integer, List<Integer>> map = new TreeMap();
		int distance = 0;
		rec_verticalUtil(root, map, distance);
		
		for (Map.Entry<Integer, List<Integer>> it : map.entrySet()) {
			//List<Integer> value = it.getValue();
			System.out.println(it.getKey() +" "+it.getValue());
		}
		
	}

	/** fill data in map based on distance as key and value as all node that are at that distance  **/
	private void rec_verticalUtil(Node root, Map<Integer, List<Integer>> map, int distance) {
		if(root == null)
			return;
		
		List<Integer> list = map.get(distance);
		if (list == null) 
			list = new ArrayList();
		
		list.add(root.data);
		
		map.put(distance, list);
		
		rec_verticalUtil(root.left, map, distance - 1);
		rec_verticalUtil(root.right, map, distance + 1);
	}

	public void inorderBT() {
		System.out.print("Inorder : ");
		rec_inorder(root);
		System.out.println();
	}

	private void rec_inorder(Node root) {
		if(root == null) return;
		rec_inorder(root.left);
		System.out.print(root.data+" ");
		rec_inorder(root.right);
	}
}

public class BT_VerticalPrint {
	public static void main(String[] args) {
		BT tree = new BT();
		
		tree.root = new Node(12);tree.inorderBT();
		tree.root.left = new Node(11);tree.inorderBT();
		tree.root.right = new Node(15);tree.inorderBT();
		tree.root.left.left = new Node(19);tree.inorderBT();
		tree.root.left.left.left = new Node(115);tree.inorderBT();
		tree.root.left.left.right = new Node(150);tree.inorderBT();
		
		tree.printVertical();
	}
}
/**
 * 
Inorder : 12 
Inorder : 11 12 
Inorder : 11 12 15 
Inorder : 19 11 12 15 
Inorder : 115 19 11 12 15 
Inorder : 115 19 150 11 12 15 
-3 [115]
-2 [19]
-1 [11, 150]
0 [12]
1 [15]

 * 
 */
