package algo_2nd_dec;
/**
 * 
 * @author Hariom Yadav | 02-Dec-2019
 * same as conncted : only difference is inside DFS call for all vertex for loop 
 * call only for not visited vertex
 */

//only modify DFS method, + in parent call make DFS UTIL method as protected %%
class GraphD extends Graph{

	public GraphD(int vertexSize) {
		super(vertexSize);
	}
	
	public void DFS(int startVertex) {
		//1. visited
		boolean[] visited = new boolean[vertexSize];
		//2. call rec - dfs util
		
		//super.DFSUtil(startVertex, visited);
		
		for(int i=0; i<vertexSize; i++) {//calling for all vertex since disconnected
			if(visited[i] == false) {//not visited 
				super.DFSUtil(i, visited);//TODO: why protected required
			}
		}
	}
	
}

public class Graph_impl_DFS_disconnected {
	public static void main(String[] args) {
		GraphD obj = new GraphD(4);
		obj.addEdge(0, 1); obj.addEdge(2, 3);
		obj.DFS(0);
		
	}
}
/**

0 1 2 3 
 */
