package july9th;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

//cache ds
class LRU{
	Deque<Integer> dq = new LinkedList();	//store actual content
	Set<Integer> set = new HashSet();		//for searching key is present in cache or not, contain same number of element as deque
	int size;
	
	public LRU(int size) {
		this.size = size;
	}

	public void refer(int key) {
		if(!set.contains(key)) {		//key not present in cache
			if(dq.size() == this.size) {//if cache is full then free space
				dq.removeLast();
				set.remove(key);
			}
			dq.addFirst(key);
			set.add(key);
		}else { 					//key present in cache + get and place in 1st place
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
		System.out.print("LRU : ");
		while(it.hasNext()) {
			System.out.print(it.next()+" ");
		}
		System.out.println();
	}
	
	
}

public class LRUCacheIMPL {
	public static void main(String[] args) {
		LRU lru = new LRU(3);
		lru.print();
		lru.refer(1); lru.print();
		lru.refer(1); lru.print();
		lru.refer(11); lru.print();
		lru.refer(12); lru.print();
		lru.refer(13); lru.print();
		lru.refer(13); lru.print();
		lru.refer(100); lru.print();
	}
}

/**
LRU : 
LRU : 1 
LRU : 1 
LRU : 11 1 
LRU : 12 11 1 
LRU : 13 12 11 
LRU : 13 12 11 
LRU : 100 13 12 

 */
