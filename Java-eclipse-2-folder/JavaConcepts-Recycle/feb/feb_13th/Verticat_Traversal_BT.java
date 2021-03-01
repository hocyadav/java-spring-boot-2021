package feb_13th;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/**
 * 
 * @author Hariom Yadav | 13-Feb-2020
 *
 */

class Node1 {
	int data;
	Node1 left, right;
	
	public Node1(int data) {
		super();
		this.data = data;
	}
}


class BinaryT {
	Node1 root;
	
	public void inorder() {
		System.out.print("Inorder : ");
		rec_inorder(root);
		System.out.println();
	}

	private void rec_inorder(Node1 root) {
		if(root == null) {
			return;
		}
		rec_inorder(root.left);
		System.out.print(root.data+" ");
		rec_inorder(root.right);
	}

	public void verticalTraversal() {
		//integer , list : 0 store as all 0 list
		
		//Map<Integer, List<Integer>> hmap = new HashMap<>(); // we will get ans from root as recursion call
		Map<Integer, List<Integer>> hmap = new TreeMap<>(); // we will get ans in sorted order
		
		rec_vertical(hmap, root, 0);
		
		Set< Map.Entry<Integer, List<Integer> > > entry = hmap.entrySet();// inside set : Map.Entry<key, value> 
		
		for(Map.Entry<Integer, List<Integer>> m : entry) {//data type is inside of above set
			List<Integer> list = m.getValue();
			
			System.out.println(m.getKey()+" - "+list);
		}
		
		
	}

	private void rec_vertical(Map<Integer, List<Integer>> hmap, Node1 root, int index) {
		
		if(root == null) {
			return;
		}
		
		List<Integer> list = hmap.get(index);
		
		if(list == null) {
			list = new ArrayList<>();
		}
		list.add(root.data);

		hmap.put(index, list);
		
		rec_vertical(hmap, root.left, index - 1);
		rec_vertical(hmap, root.right, index + 1);
		
	}
}

public class Verticat_Traversal_BT {
	public static void main(String[] args) {
		BinaryT tree = new BinaryT();
		
		tree.root = new Node1(12);tree.inorder();
		tree.root.left = new Node1(11);tree.inorder();
		tree.root.right = new Node1(15);tree.inorder();
		tree.root.left.left = new Node1(19);tree.inorder();
		tree.root.left.left.left = new Node1(115);tree.inorder();
		tree.root.left.left.right = new Node1(150);tree.inorder();
		
		tree.verticalTraversal();
		
	}
}
/**
Inorder : 12 
Inorder : 11 12 
Inorder : 11 12 15 
Inorder : 19 11 12 15 
Inorder : 115 19 11 12 15 
Inorder : 115 19 150 11 12 15 
-3 - [115]
-2 - [19]
-1 - [11, 150]
0 - [12]
1 - [15]

*/