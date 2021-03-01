package july7th;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** https://www.programcreek.com/2014/07/leetcode-meeting-rooms-java/ **/
public class MeetingRoom_leetcode {
	//logic ;: sort based on start time 
	//traverse start from 1 and check end of previous is greater than start of next then return false
	public static void main(String[] args) {
		List<int[]> list = new LinkedList<>();
		list.add(new int[] {0,30});
		list.add(new int[] {5,10});
		list.add(new int[] {15,20});
		
		List<int[]> list2 = new LinkedList<>();
		list2.add(new int[] {10,20});
		list2.add(new int[] {0,10});
		list2.add(new int[] {20,30});
			
		System.out.println(extracted(list));//start from 0 index
		System.out.println(extracted2(list));//start from 1 index
		
		
		System.out.println(extracted(list2));//start from 0 index
		System.out.println(extracted2(list2));//start from 1 index
	}

	//sort based on start index -> traverse and check end of previous is greater than start of next
	private static boolean extracted(List<int[]> list) {
		Collections.sort(list, (a, b) -> a[0] - b[0]);
		
		for(int i = 0; i < list.size() - 1; i++) {//start from 0 index
			if(list.get(i)[1] > list.get(i + 1)[0])
				return false;
		}
		return true;
	}
	
	private static boolean extracted2(List<int[]> list) {
		Collections.sort(list, (a, b) -> a[0] - b[0]);
		
		for(int i = 1; i < list.size(); i++) {
			if(list.get(i - 1)[1] > list.get(i)[0])
				return false;
		}
		return true;
	}
}
/**
false
false
true
true

*/