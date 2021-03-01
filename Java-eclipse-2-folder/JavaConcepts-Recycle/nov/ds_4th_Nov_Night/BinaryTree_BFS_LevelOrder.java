package ds_4th_Nov_Night;
/**
 * 
 * @author Hariom Yadav - Nov 4, 2019
 *
 */
import java.util.LinkedList;
import java.util.Queue;

//ds - take from DFS code

//use of DS
class Binary_BFS extends BinaryT{
	//know something
	//take from DFS
	
	//does something
	Binary_BFS(int v) {//auto - required
		super(v);
	}
	
	void bfs(){
		//queue
		//temp = root node 
		//while temp != null
			//print temp
			//enqueue : push all children of temp
			//dequeue : update temp 
		
		Queue<Node> qu = new LinkedList<Node>();
		Node temp = root;
		
		while(temp != null) {
			//1
			System.out.print(temp.data+" ");

			//2 - enququ
			//to find children - for loop for graph, here 2 if block requires since 2 childern left and right
			//for(int i=0;i<)
			if(temp.left != null)
				qu.add(temp.left);
			if(temp.right != null)
				qu.add(temp.right);
			
			//3 dequeue - update temp
			temp = qu.peek();
			qu.remove();
			
		}
		
		
		
	}
	
	void bfs2() {
		Queue<Node> qq = new LinkedList<Node>();
		qq.add(root);
		while(root!=null) {
			Node temp = qq.poll();//print + remove top
			System.out.print(temp.data+" ");
			if(temp.left != null) {
				qq.add(temp.left);
			}
			if(temp.right != null) {
				qq.add(temp.right);
			}
			
			
		}
	}
	
}


public class BinaryTree_BFS_LevelOrder {
	public static void main(String[] args) {
		Binary_BFS obj = new Binary_BFS(10);
		obj.root.left = new Node(2);
		obj.root.right = new Node(3);
		
		obj.root.left.left = new Node(4);
		obj.root.left.right = new Node(5);
		
		obj.root.right.left = new Node(6);
		obj.root.right.right = new Node(7);
		
		obj.bfs2();
		
	}
}
