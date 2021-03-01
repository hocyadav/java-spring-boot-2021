package advance_ds_28th_nov;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

//data structure
class Node{
	int data;
	Vector<Node> child = new Vector<>();
	
	public Node(int key) {
		this.data = key;
	}
}

class NArry{
	//know something
	Node root;
	
	//does something
	public void insert(int key) {
		root = rec_insert(root, key);
	}
	
	private Node rec_insert(Node root2, int key) {
		Node nn = new Node(key);
		
		if(root == null) {
			root = nn;
			return root;
		}else {//TODO 
			//traverse(for loop children) and get correct node --> then insert
		}
		return null;
	}

	public void NArrayLevelOrderTraversal() {
		Queue<Node> qq = new LinkedList<>();
		qq.add(root);//enQ
		
		while(!qq.isEmpty()) {
			int n = qq.size();
			
			while(n > 0) {
				//deQ
				Node nn = qq.poll();
				System.out.print(nn.data+" ");
				
				//enQ all children
				for(int i=0; i< nn.child.size(); i++) {
					qq.add(nn.child.get(i));
				}
				--n;
			}
		}
	}
}


public class N_array_impl_LevelOrderTraversal_BFS {
	public static void main(String[] args) {
		NArry obj = new NArry();
		obj.insert(12); obj.NArrayLevelOrderTraversal();
		//obj.insert(10); obj.NArrayLevelOrderTraversal(); //error impl : TODO fix
		
	}
}
