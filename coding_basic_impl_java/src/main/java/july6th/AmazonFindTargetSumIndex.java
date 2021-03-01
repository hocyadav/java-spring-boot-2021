package july6th;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AmazonFindTargetSumIndex {
	public static void main(String[] args) {
		/** create list or arraylist to store int[] array **/
		List<int[]> list = new LinkedList();
		list.add(new int[] {0,10});
		list.add(new int[] {1,90});
		list.add(new int[] {2,5});
		//printLL(list);
		/**  sort above list/arraylist based on 2nd index **/
		Collections.sort(list, (o1, o2) -> {
			return o1[1] - o2[1];
		});
		printLL(list);
		int p = 0;
		int q = list.size() - 1;
		int targetSum = 95;
		while(p < q) {
			int[] l = list.get(p);
			int[] m = list.get(q);
			if(l[1] + m[1] == targetSum) {
				System.out.println("sum found");
				System.out.println("index 1 "+l[0]);
				System.out.println("index 2 "+m[0]);
				break;
			}
			if(l[1] + m[1] < targetSum) {
				p++;
			}else {
				p--;
			}
		}
		//System.out.println("not found");
	}

	private static void printLL(List<int[]> list) {
		for(int[] i : list) {
			System.out.println(i[0]+" "+i[1]);
		}
	}
	
}
