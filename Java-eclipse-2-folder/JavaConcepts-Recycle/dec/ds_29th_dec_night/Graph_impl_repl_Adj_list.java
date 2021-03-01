package ds_29th_dec_night;

import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav | 29-Dec-2019
 *
 */

class Graph{//this is minimum basic impl of graph 
	int vsize;
	LinkedList<Integer> adjList[];
	
	public Graph(int vsize) {
		super();
		this.vsize = vsize;
		adjList = new LinkedList[this.vsize];
		
		for(int i=0; i<this.vsize; i++)
			adjList[i] = new LinkedList<Integer>();
	}
	
	//undirected graph 
	/**
	 * Undirected graph addEdge method
	 * @param src
	 * @param dest
	 */
	public void addEdge(int src, int dest) {//undirected both side addition required s-->d and d-->s 
		adjList[src].add(dest); //s-->d
		adjList[dest].add(src); //d-->s
	}
	
	/**
	 * Print graph adj list
	 */
	public void print() {
		for(int i=0; i<this.vsize; i++) {
			System.out.print(i+" -> ");
			
			for(Integer j : adjList[i]) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
		System.out.println("---------");
	}
	
	
}


public class Graph_impl_repl_Adj_list {
	public static void main(String[] args) {
		Graph obj = new Graph(5);
		//obj.print();
		obj.addEdge(0, 1); obj.print();
		obj.addEdge(0, 4); obj.print();
		obj.addEdge(1, 4); 
		obj.addEdge(1, 2); 
		obj.addEdge(1, 3); 
		//obj.addEdge(2, 1); //not required since we undirected so we are adding both 
		obj.addEdge(2, 3); 
		//obj.addEdge(3, 1); 
		obj.addEdge(3, 4); 
		//obj.addEdge(3, 2);
		//obj.addEdge(4, 3); 
		//obj.addEdge(4, 0); 
		//obj.addEdge(4, 1);
		
		obj.print();
		
		
		
	}
}
/**
0 -> 1 
1 -> 0 
2 -> 
3 -> 
4 -> 
---------
0 -> 1 4 
1 -> 0 
2 -> 
3 -> 
4 -> 0 
---------
0 -> 1 4 
1 -> 0 4 2 3 
2 -> 1 3 
3 -> 1 2 4 
4 -> 0 1 3 
---------
*/