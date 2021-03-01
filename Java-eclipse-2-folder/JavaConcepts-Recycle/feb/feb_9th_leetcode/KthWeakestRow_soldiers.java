package feb_9th_leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * @author Hariom Yadav | 09-Feb-2020
 * based on CONCEPT : sorting on 2nd index
 * i.e. Sort based on 2nd index and print 1st index
 */

public class KthWeakestRow_soldiers {
	public static void main(String[] args) {
		int[][] arr1 = {
				{1,1,0,1,1},
				{1,1,0,0,0},
				{1,1,0,1,1},
				{1,1,0,0,1}
		};
		kWeakestRows(arr1,3);
		/**
		 * Logic
		 * 1. instead of hashmap take list<int[]> of array
		 * 2. sort based on 2nd index : using Comparator
		 * 3. print 1st index value
		 * 
		 */
		System.out.println();
		int[] t = new int[3];
		t = kWeakestRows2(arr1, 3);
		System.out.println("print 1st k=3 element..");
		printArray(t);
		
	}
	
	
	private static void kWeakestRows(int[][] mat, int k) {
        List<int[]> list = new ArrayList<>();
        
        for(int i = 0; i < mat.length; i++) {
            int count = 0;
            for(int m : mat[i]) 
                if(m == 1) count++;
            list.add(new int[]{i, count});
        }
        System.out.println("before sorting.. ");
       print(list);
       
       //sort based on 2nd index
        Comparator<int[]> cobj = new Comparator<int[]>() {//sort based on 2nd argument  : see file priorityQueue_method.java for comparator sort
        	public int compare(int[] arr1, int[] arr2) {
        		if(arr1[1]  > arr2[1]) 
        			return 1;
        		else if (arr1[1] < arr2[1]) 
        			return -1;
        		return 0;
        	}
		};
		
        Collections.sort(list, cobj);
        System.out.println("After sorting based on 2nd index..");
        print(list);
        
        //store new 1st index
        int[] r = new int[list.size()];
        for(int i = 0; i < r.length; i++) {
        	r[i] = list.get(i)[0];
        }
        
        System.out.println("print 1st index ");
        printArray(r);
    }


	private static void printArray(int[] r) {
		for(int i =0; i < r.length; i++) {
			System.out.print(r[i]+" ");
		}
	}


	private static void print(List<int[]> list) {
		Iterator<int[]> it = list.iterator();
		int[] t = new int[2];
 		while(it.hasNext()) {
			t = it.next();
			System.out.println(t[0]+" "+t[1]);
		}
		
		
	}
	
	
	private static int[] kWeakestRows2(int[][] mat, int k) {
        List<int[]> list = new ArrayList<>();
        
        for(int i = 0; i < mat.length; i++) {
            int count = 0;
            for(int m : mat[i]) 
                if(m == 1) count++;
            list.add(new int[]{i, count});
        }
        
        Comparator<int[]> cobj = new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if(arr1[1] > arr2[1])//sorting on 2nd index
                    return 1;
                else if(arr1[1] < arr2[1])
                    return -1;
                return 0;
            }
        };
        
        Collections.sort(list, cobj);
        
        int[] result = new int[k];
        
        for(int i = 0; i < k; i++) {
            result[i] = list.get(i)[0];//storing 1st index
        }
        
        return result;
    }
	
}

/**
 * 
before sorting.. 
0 4
1 2
2 4
3 3
After sorting based on 2nd index..
1 2
3 3
0 4
2 4
print 1st index 
1 3 0 2 
print 1st k=3 element..
1 3 0 
*/

