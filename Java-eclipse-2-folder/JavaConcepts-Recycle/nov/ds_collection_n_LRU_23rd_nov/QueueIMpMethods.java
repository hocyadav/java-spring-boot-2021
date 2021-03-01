package ds_collection_n_LRU_23rd_nov;

import java.util.LinkedList;
import java.util.Queue;

public class QueueIMpMethods {

	public static void main(String[] args) {
		Queue<Integer> qq = new LinkedList<>();
		
		//add - enqueu - add at last in array list
		qq.add(1);
		qq.add(2);
		qq.add(3);
		qq.add(4);
		System.out.println(qq);
		
		//remove - dequeue - remove 1st in array list
		qq.remove();
		System.out.println(qq);
	}

}
