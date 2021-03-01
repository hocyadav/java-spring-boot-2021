package jan_11th;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav | 11-Jan-2020
 * https://www.youtube.com/watch?v=kHWy5nEfRIQ&t=242s
 */

class Graph{
	int vSize;
	LinkedList<Integer>[] adjList;
	
	public Graph(int size){
		this.vSize = size;
		adjList = new LinkedList[this.vSize];
		
		for(int i=0; i<this.vSize; i++)//for each vertex assign a new list , for storing connected vertex
			adjList[i] = new LinkedList<Integer>();
	}
	
	public void printAdjList() {
		for(int i=0; i<adjList.length; i++) {
			System.out.print(i+" -> ");
			Iterator<Integer> it = adjList[i].iterator();
			while(it.hasNext()) {
				System.out.print(it.next()+" ");
			}
			System.out.println();
		}
	}
	
	public void dfs(int vertex) {
		//dfs start
		boolean visited[] = new boolean[this.vSize];//1 more size coz we have created vertex + 1 size graph
		rec_dfs(vertex, visited);
		System.out.println();
		//dfs end
		
		if(visited[visited.length-1] == true)
			System.out.println("reached vertex 6");
		else
			System.out.println("Not reached vertex 6");
	}
	
	public void rec_dfs(int vertex, boolean[] visited){
		visited[vertex] = true;
		System.out.print(vertex+" ");
		
		Iterator<Integer> it = adjList[vertex].iterator();
		while(it.hasNext()) {
			int val = it.next();
			if(visited[val] == false){//only for not visited
				rec_dfs(val, visited);
			}
		}
	}
	
}

public class Leetcode_tower_hopping {
	public static void main(String[] args) {
		int arr[] = {4,2,0,0,2,0};
		int graphLen = arr.length+1;
		Graph obj = new Graph(graphLen);
		obj.printAdjList();
		System.out.println("-------------");
		//convert array into adj list graph
		for(int i=0; i < arr.length; i++) {
			if(arr[i] != 0) {
				for(int j=i; j < Math.min(arr.length, i+arr[i]) ; j++) {
					obj.adjList[i].add(j+1);
				}
			}
		}
		
		obj.printAdjList();
		obj.dfs(0);
//		obj.dfs(1);
//		obj.dfs(2);
//		obj.dfs(3);
//		obj.dfs(4);
//		obj.dfs(5);
//		obj.dfs(6);
		
	}

}
/**

0 -> 
1 -> 
2 -> 
3 -> 
4 -> 
5 -> 
6 -> 
-------------
0 -> 1 2 3 4 
1 -> 2 3 
2 -> 
3 -> 
4 -> 5 6 
5 -> 
6 -> 
0 1 2 3 4 5 6 
reached vertex 6

*/