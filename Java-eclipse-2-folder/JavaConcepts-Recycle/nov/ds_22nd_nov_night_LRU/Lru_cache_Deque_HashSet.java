package ds_22nd_nov_night_LRU;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav - Nov 22, 2019
 *	
 * if (Not present in HashSet)
 * 		if HashSet size full -> free space + add
 * 		else 			     -> add
 * else Present in HashSet
 * 		Find(iterator) position in Deque LinkedList + remove
 * 		addFirst that element in Queue + add in HashSet
 *
 */
class LruCache{
	//two data structure --> deque + hash set
	//deque Linked list --> for cache
	//hash set --> check key present in deque or not (coz searching in dequeu is n time)
	
	//know something
	Deque<Integer> dq = new LinkedList<>();
	HashSet<Integer> set = new HashSet<>();
	int cSize = 4;
	
	//does something
	public void refer(int key) {
		if(! set.contains(key)) {//not in set -> that means not in DQ cache --> that means add in both DQ + set
			//1. full then make space by removing
			if(dq.size() == cSize) {//if deque size is full --> remove last from DQ + remove from set
				int last = dq.removeLast();//remove last ele from DQ
				set.remove(last);//remove reference from set
			}
			//2. add
			dq.addFirst(key);
			set.add(key);
			
		}else {//present in set -> that means present in deque cache
			//1. make space - search + remove
			System.out.println("else ");
			Iterator<Integer> it = dq.iterator();
			int index = 0;
			while(it.hasNext()) {
				index++;
				if(it.next() == key) {
					break;
				}
			}
			dq.remove(index);
			
			//2. add
			dq.addFirst(key);
			System.out.println("key contains : "+key+" - "+set.contains(key));
			set.add(key);//not required since else block here means already present in set - we can cross verify
		}
	}
	
	/**
	 * print content of LRU Cache
	 */
	public void print() {
		Iterator<Integer> it = dq.iterator();
		System.out.print("LRU Cache : ");
		while(it.hasNext()) 
			System.out.print(it.next()+" ");
				
		System.out.println();
	}
}

public class Lru_cache_Deque_HashSet {
	public static void main(String[] args) {
		LruCache obj = new LruCache();
		obj.refer(1); obj.print();
		obj.refer(2); obj.print();
		obj.refer(4); obj.print();
		obj.refer(1); obj.print();
		obj.refer(1); obj.print();
		obj.refer(5); obj.print();
		obj.refer(1); obj.print();
	}
}
