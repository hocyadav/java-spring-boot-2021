package ds_29th_dec_night;

import java.util.Iterator;
/**
 * 
 * @author Hariom Yadav | 29-Dec-2019
 *
 */
class GraphDFS extends Graph{//extending graph impl and adding dfs method

	public GraphDFS(int vsize) {
		super(vsize);
	}
	
	public void dfsConnected(int startVertex) {
		//1. boolean visited array
		//2. dfs util
		
		boolean[] visited = new boolean[this.vsize];//by default all false
		dfsUtil(visited, startVertex);
	}

	private void dfsUtil(boolean[] visited, int v) {
		//1. visited vertex -> true
		//2. call child dfs util
		
		visited[v] = true;
		System.out.print(v + " ");
		Iterator<Integer> it = adjList[v].iterator();
		while(it.hasNext()) {
			int vv = it.next();
			if(visited[vv] == false) {
				dfsUtil(visited, vv);
			}
		}
	}
}

public class Graph_DFS {
	public static void main(String[] args) {
		GraphDFS obj = new GraphDFS(5);
		obj.addEdge(0, 1); obj.print();
		obj.addEdge(0, 4); obj.print();
		obj.addEdge(1, 4); 
		obj.addEdge(1, 2); 
		obj.addEdge(1, 3); 
		//obj.addEdge(2, 1); //not required since undirected so we are adding both 
		obj.addEdge(2, 3); 
		//obj.addEdge(3, 1); 
		obj.addEdge(3, 4); 
		//obj.addEdge(3, 2);
		//obj.addEdge(4, 3); 
		//obj.addEdge(4, 0); 
		//obj.addEdge(4, 1);
		
		obj.print();
		obj.dfsConnected(0);
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
0 1 4 3 2 
*/