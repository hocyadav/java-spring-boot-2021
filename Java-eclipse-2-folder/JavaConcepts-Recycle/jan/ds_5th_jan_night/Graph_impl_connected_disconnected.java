package ds_5th_jan_night;

import java.util.Iterator;
import java.util.LinkedList;

//ds : LinkedList<Integer>[] array + linkedList<Integer>

class Graph{
	int vsize;
	LinkedList<Integer>[] adjList;
	
	public Graph(int size) {
		this.vsize = size;
		adjList = new LinkedList[this.vsize];
		
		for(int i=0; i<this.vsize; i++)
			adjList[i] = new LinkedList<Integer>();
	}
	
	public void addEdge(int src, int given) {
		adjList[src].add(given);
	}
	
	public void DFS_Connected_Graph(int startV) {
		//1. visited boolean 
		//2. call DFS util that will work like Queue
		
		boolean[] visited = new boolean[this.vsize];
		
		DFSUtil(visited, startV);
		System.out.println();
		
	}
	
	//only difference is call DFS util in for loop so that disconnected vertex can be covered
	public void DFS_Disconnected_Graph() {
		//1. visited boolean 
		//2. call DFS util that will work like Queue
		
		boolean[] visited = new boolean[this.vsize];
		
		for(int i=0; i<this.vsize; i++) {
			if(visited[i] == false)//call DFS util only for non visited vertex
				DFSUtil(visited, i);
		}
		System.out.println();
	}

	private void DFSUtil(boolean[] visited, int givenV) {
		//1. make visited flag true 
		//2. print
		//3. call its child : like queue
		
		visited[givenV] = true;
		System.out.print(givenV+" ");
		
		Iterator<Integer> it = adjList[givenV].iterator();
		while(it.hasNext()) {
			int i = it.next();
			if(visited[i] == false) {//check for not visited only
				DFSUtil(visited, i);
			}
		}
		
	}
	
}

public class Graph_impl_connected_disconnected {
	public static void main(String[] args) {
		Graph obj = new Graph(4);
		obj.addEdge(0, 1); obj.addEdge(1, 2);
		obj.addEdge(2, 3); obj.addEdge(1, 3);
		obj.addEdge(3, 2); obj.addEdge(1, 0);
		obj.addEdge(3, 1);
		obj.DFS_Connected_Graph(2);
		obj.DFS_Disconnected_Graph();//obj.DFS_Disconnected_Graph(2) with argument this is also fine 
									//but inside we are using for loop so not required because 
									//it is printing using for loop 
	}
}
