package ds_10th_Nov;

import java.util.Vector;
/**
 * 
 * @author Hariom Yadav - Nov 10, 2019
 *
 */
public class Heap_insert {
	static final int MAX = 1000;
	public static void main(String[] args) {
		Vector<Integer> vectorObj = new Vector<>();
		vectorObj.add(1);
		vectorObj.add(20);
		vectorObj.add(11);
		vectorObj.add(6);
		vectorObj.add(8);
		vectorObj.add(4);
		//vector method used : .add(index, value), .remove(index), .size(), .elementAt(0), .addElement(100); 

		int size = vectorObj.size();
		for(Integer i:vectorObj)
			System.out.print(i+" ");
		System.out.println();
		
		buildHeap(vectorObj, size);
		
		for(Integer i:vectorObj)
			System.out.print(i+" ");
		System.out.println();
		
		//insert 
		//1. add element at last + call heapify for last element
		int key = 21;
		insert(vectorObj, key);
		for(Integer i:vectorObj)
			System.out.print(i+" ");
		System.out.println();
		 
	}
	//1 simply add
	//2. find non leaf node and call heapify
	private static void insert(Vector<Integer> vectorObj, int key) {
		vectorObj.add(key);
		
		for(int i=vectorObj.size()/2-1; i>=0; i--)
			heapify(vectorObj, vectorObj.size(), i);//latest size + sent last index
	}

	private static void buildHeap(Vector<Integer> vectorObj, int size) {
		//find non leaf node
		int nn = size/2 -1;
		for(int i=nn; i>=0; i--)
			heapify(vectorObj, size, i);
	}
	
	private static void heapify(Vector<Integer> vectorObj, int size, int index) {
		//all index
		int largest	= index;
		int left	= index*2 +1;
		int right 	= index*2 +2;
		//for parent node = (index - 1) /2;
		
		//find largest value index
		if(left < size && vectorObj.elementAt(left) > vectorObj.elementAt(largest))
			largest = left;
		if(right < size && vectorObj.elementAt(right) > vectorObj.elementAt(largest))
			largest = right;
		
		//if largest not same as index root then swap + call heapify
		if(largest != index) {
			Integer larg	= vectorObj.elementAt(largest);
			Integer ind	= vectorObj.elementAt(index);
			
			vectorObj.remove(largest);
			vectorObj.add(largest, ind);
			
			vectorObj.remove(index);
			vectorObj.add(index, larg);
			
			heapify(vectorObj, size, largest);
		}
	}	
}
