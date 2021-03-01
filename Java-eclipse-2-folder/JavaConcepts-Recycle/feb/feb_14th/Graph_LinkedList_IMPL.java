package feb_14th;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav | 14-Feb-2020
 *
 */
//ds
class Graph {
	int vSize;
	LinkedList<Integer> adjList[];
	
	public Graph(int s) {
		vSize = s;
		adjList = new LinkedList[vSize];
		
		//alocate space
		for(int i = 0; i < vSize; i++) {
			adjList[i] = new LinkedList<>();
		}
	}
	
	public void addEdge(int s, int d) {
		adjList[s].add(d);
	}
	
	public void printGraphNodes() {
		for(int i = 0; i < vSize; i++) {
			System.out.print(i+" --> ");
			for(Integer l : adjList[i]) {//get list for a given node
				System.out.print(l + " ");
			}
			System.out.println();
		}
	}
	
	//connected garph
	public void DFS_ConnectedGraph() {
		//boolean visited
		// recursion : inside make visited true + get list + check visted and call dfs recursion
		
		boolean[] visited = new boolean[vSize];
		DFS_rec(visited, 3);//start vertex
		System.out.println();
		
	}

	private void DFS_rec(boolean[] visited, int i) {
		if(visited[i] == true) { //base case recursion
			return;
		}
		visited[i] = true;
		System.out.print(i +" ");
		
		Iterator<Integer> it = adjList[i].iterator();
		while(it.hasNext()) {
			int t = it.next();
			if(visited[t] == false) {
				DFS_rec(visited, t);
			}
		}
		
	}
}
public class Graph_LinkedList_IMPL {
	public static void main(String[] args) {
		Graph obj = new Graph(4);
		obj.printGraphNodes();
		obj.addEdge(0, 1); obj.printGraphNodes();
		obj.addEdge(0, 2);
		obj.addEdge(2, 3);obj.printGraphNodes();
		obj.addEdge(3, 2);
		obj.printGraphNodes();
		obj.DFS_ConnectedGraph();
	}
}
/**
0 --> 
1 --> 
2 --> 
3 --> 
0 --> 1 
1 --> 
2 --> 
3 --> 
0 --> 1 
1 --> 2 
2 --> 3 
3 --> 
0 --> 1 
1 --> 2 
2 --> 3 
3 --> 2 
2 3 


*/