package july10th;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

class Node{
	int data;
	Node left, right;
	public Node(int data) {
		this.data = data;
	}
}

class BTImpl{
	Node root;
	
	public void printLeftView() {
		Map<Integer, Node> map = new TreeMap();
		rec_left(root, map, 0);
//		map.forEach(new BiConsumer<Integer, Node>() {
//			@Override
//			public void accept(Integer t, Node u) {
//				System.out.print(t+" "+u.data);
//			}
//		});
		
		for(Map.Entry<Integer, Node> en : map.entrySet()) {
			System.out.println(en.getValue().data);
		}
		
		System.out.println();
	}

	private void rec_left(Node root, Map<Integer, Node> map, int distance) {
		if(root == null)
			return;
		
		Node t = map.get(distance);
		if(t == null) {//only add 1st element from level order
			t = root;
			map.put(distance, t);
		}
		
		rec_left(root.left, map, distance + 1);
		rec_left(root.right, map, distance + 1);
	}
}


public class BT_Vertical_Right_Left_View {
	public static void main(String[] args) {
		BTImpl tree = new BTImpl();
		tree.root = new Node(10);
		tree.root.left = new Node(4);
		tree.root.right = new Node(40);
		tree.root.left.left = new Node(2);
		tree.printLeftView();
	
	}
}
