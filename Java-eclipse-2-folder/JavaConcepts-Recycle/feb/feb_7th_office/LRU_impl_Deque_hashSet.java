package feb_7th_office;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
/**
 * 
 * @author Hariom Yadav | 07-Feb-2020
 *
 */

class LRU {
	int size;
	Deque<Integer> dq;
	Set<Integer> set;
	
	public LRU(int s) {
		size = s;
		dq = new LinkedList<>();
		set = new HashSet<>();
	}
	
	public void refer(int key) {
		if(!dq.contains(key)) {//key not contain
			if(dq.size() == size) {//make free space
				int t = dq.removeLast();
				set.remove(t);
			}
			dq.addFirst(key);//add
			set.add(key);
		} else { //key contain
			//find, make free space and add
			if(dq.getFirst() != key) {
				Iterator<Integer> it = dq.iterator();
				while(it.hasNext()) {
					if(it.next() == key) {
						it.remove();
						break;
					}
				}
				dq.addFirst(key);
			}
		}
	}
	
	public void print() {
		Iterator<Integer> it = dq.iterator();
		System.out.print("LRU cache : ");
		while(it.hasNext()) {
			System.out.print(it.next()+" ");
		}
		System.out.println();
	}
	
	
}

public class LRU_impl_Deque_hashSet {

	public static void main(String[] args) {
		LRU lruCache = new LRU(4);
		lruCache.print();
		lruCache.refer(12);lruCache.print();
		lruCache.refer(12);lruCache.print();
		lruCache.refer(14);lruCache.print();
		lruCache.refer(16);lruCache.print();
		lruCache.refer(19);lruCache.print();
		lruCache.refer(12);lruCache.print();
	}

}

/*
LRU cache : 
LRU cache : 12 
LRU cache : 12 
LRU cache : 14 12 
LRU cache : 16 14 12 
LRU cache : 19 16 14 12 
LRU cache : 12 19 16 14 
*/