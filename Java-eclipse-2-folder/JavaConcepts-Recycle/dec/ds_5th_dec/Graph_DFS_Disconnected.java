package ds_5th_dec;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Hariom Yadav | 05-Dec-2019
 *
 */

//ds
class GraphD{
	int vertexSize;
	List<Integer>[] adjVertex;
	
	public GraphD(int s) {
		vertexSize = s;
		adjVertex = new LinkedList[vertexSize];
		
		//give space to each vertex - for storing connected vertex
		for(int i=0; i<s; i++) {
			adjVertex[i] = new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int givenVertex, int connectedvertex) {
		adjVertex[givenVertex].add(connectedvertex);
		//adjVertex[givenVertex] --> this is one LL & we are adding data on this list
	}

	
	public void DFS_DisconnectedGraph(int startVertex) {
		boolean[] visited = new boolean[vertexSize];
		
		System.out.print("DFS : ");
		
		for(int i=0; i<vertexSize; i++) {
			DFS_Util2(i, visited);
		}
		System.out.println();
	}

	
	//same as DFSUtil connected --> update visited nod and print afte checking it is false(i.e. not visited)
	private void DFS_Util2(int givenVertex, boolean[] visited) {
		//1. visited - true + print
		//2. call not visited child - recursion
		if(visited[givenVertex] == false) {
			visited[givenVertex] = true;
			System.out.print(givenVertex+" ");
		}
		
		Iterator<Integer> it = adjVertex[givenVertex].iterator();
		while(it.hasNext()) {
			int i = it.next();
			if(visited[i] == false) {//non visited connected node
				DFS_Util2(i, visited);
			}
		}
		
	}
	
	
}

public class Graph_DFS_Disconnected {
	public static void main(String[] args) {
		GraphD obj = new GraphD(4);
		
		obj.addEdge(0, 1);
		obj.addEdge(1, 0);
		obj.addEdge(1, 2);
		obj.addEdge(2, 3);
		
		obj.DFS_DisconnectedGraph(1);
		
	}
	
}
/**
 * 
DFS : 0 1 2 3 
 */
