package ds_22nd_nov_night_LRU;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author Hariom Yadav - Nov 22, 2019
 *
 */
public class QueueAllMethods {

	public static void main(String[] args) {
		Queue<Integer> qq = new LinkedList<>();
		//add - enqueue in Queue
		qq.add(1);
		qq.add(2);
		qq.add(3);
	
		System.out.println(qq);
		
		//remove - dequeue in Queue .. last added deleted first, so remove 1
		qq.poll();//remove 1, print + delete
		//qq.remove();//same as poll
		System.out.println(qq);
		
	}

}
