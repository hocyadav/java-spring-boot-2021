package ds_graph_30th_nov_night;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav | 30-Nov-2019
 *
 */

//data structure
class Graph{//private-package/default : package level access
	int vertesSize;
	LinkedList<Integer>[] adjLL;
	
	@SuppressWarnings("unchecked")
	public Graph(int size) {
		vertesSize = size;
		adjLL = new LinkedList[vertesSize];
		
		for(int i=0; i<vertesSize; i++)
			adjLL[i] = new LinkedList<Integer>();
	}
	
	public void addEdge(int vertex, int connectedVerted) {
		adjLL[vertex].add(connectedVerted);
	}
	
	//other methods
	public void DFS(int startVertex) {//DFS : for connected graph
		boolean visitedVertex[] = new boolean[vertesSize];
		
		DFSUtil(startVertex, visitedVertex);
	}

	private void DFSUtil(int givenVertex, boolean[] visitedVertex) {
		visitedVertex[givenVertex] = true;
		System.out.print(givenVertex+" ");
		
		Iterator<Integer> it = adjLL[givenVertex].iterator();
		while(it.hasNext()) {
			int n = it.next();
			if(! visitedVertex[n] )
				DFSUtil(n, visitedVertex);
		}
	}
	
}
//use of DS - not required
class GraphImpl{
	
}

public class Graph_impl_DFS_for_connected {
	public static void main(String[] args) {
		Graph obj = new Graph(4);//4 size vertex graph 
		obj.addEdge(0, 1); obj.addEdge(0, 2);//0 is connected with 1, 2
		obj.addEdge(1, 2); 					//1 is connected with 2
		obj.addEdge(2, 0); obj.addEdge(2, 3);// 2 is connected with 0,3
		obj.addEdge(3, 3);					//3 is  connected with 3.. self loop
		
		obj.DFS(2);
		System.out.println();
		obj.DFS(0);
		
	}
}
/** output 
2 0 1 3 
0 1 2 3
*/