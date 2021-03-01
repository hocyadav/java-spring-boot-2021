package algo_2nd_dec;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav | 02-Dec-2019
 *
 */

//DS  - LinkedList<Integer>[]

//use of DS
class Graph{
	int vertexSize;
	LinkedList<Integer>[] adjList;
	
	//does something
	public Graph(int vertexSize) {
		super();
		this.vertexSize = vertexSize;
		adjList = new LinkedList[this.vertexSize];
		
		//add list obj to each adj list- used for adding connected vertex
		for(int i=0; i<vertexSize; i++)
			adjList[i] = new LinkedList<Integer>();
	}
	
	public void addEdge(int givenV, int conV) {
		adjList[givenV].add(conV);
	}

	public void DFS(int startVertex) {
		//1. visited
		boolean[] visited = new boolean[vertexSize];
		//2. call rec - dfs util
		DFSUtil(startVertex, visited);
		
	}

	protected void DFSUtil(int givenVertex, boolean[] visited) {//TODO: why protected required
		//1. make visited -> true + print
		//2. check connected vertex and if not visited call this method
		
		visited[givenVertex] = true;
		System.out.print(givenVertex+" ");
		
		Iterator<Integer> it = adjList[givenVertex].iterator();
		while(it.hasNext()) {
			int i = it.next();
			if(visited[i] == false) {//not visited
				DFSUtil(i, visited);
			}
		}
		
	}
	
}

//Test graph DS
public class Graph_impl_DFS_connected {
	public static void main(String[] args) {
		Graph obj = new Graph(4);
		obj.addEdge(0, 1); obj.addEdge(1, 2);
		obj.addEdge(2, 3); obj.addEdge(1, 3);
		obj.addEdge(3, 2); obj.addEdge(1, 0);
		obj.addEdge(3, 1);
		obj.DFS(2);
	}
}
/**
2 3 1 0 
 */
