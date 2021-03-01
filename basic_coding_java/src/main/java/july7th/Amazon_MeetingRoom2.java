package july7th;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/** https://www.youtube.com/watch?v=PWgFnSygweI **/
public class Amazon_MeetingRoom2 {
	public static void main(String[] args) {
		List<int[]> list = new LinkedList<>();
		list.add(new int[] {9,10});
		list.add(new int[] {0,10});
		list.add(new int[] {15,20});

		meetingRoom(list);
		//numOfMeetingRoom(list);//NOT working

		List<int[]> list2 = new LinkedList<>();
		list2.add(new int[] {2,15});
		list2.add(new int[] {36,45});
		list2.add(new int[] {9,29});
		list2.add(new int[] {16,23});
		list2.add(new int[] {4,9});
		
		meetingRoom(list2);
		//numOfMeetingRoom(list2);
	}

	private static void printLL(List<int[]> list) {
		for (int[] i : list) {
			System.out.println(i[0]+" "+i[1]);
		}
		System.out.println("-----------");
	}

	//logic : sort based on start time
	//then take minheap - person standing in front of door only know end time of meeting rooms that are allocated till now
	private static void numOfMeetingRoom(List<int[]> list) {
		Collections.sort(list, (a, b) -> a[0] - b[0]);//sort based on start index
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);//min head root contain earliest end meeting
		
		//pq.add(list.get(0));//min 1 room required so give 1 room to 1st meeting
		//traverse all meetings
		int count  = 0;
		for(int[] val : list) {
			int start	= val[0];
			int end 	= val[1];
			if(pq.isEmpty()) {
				pq.add(val);
				count++;
			}else {
				if(start >= pq.peek()[1]) {//remove pq and add this new meeting
					pq.poll();
					pq.add(val);
				} else {
					pq.add(val);
					count++;
				}
			}
		}
		System.out.println("count : "+count);
	}

	private static int meetingRoom(List<int[]> list) {
		if(list == null || list.size() == 0) return 0;
		
		//printLL(list);
		Collections.sort(list, (a, b) -> a[0] - b[0]);
		//printLL(list);
		PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);//min heap is person standing from of door and know only last meeting end time
		minHeap.add(list.get(0));
		
		for (int i = 1; i < list.size(); i++) {
			int[] current = list.get(i);
			int[] earliest = minHeap.poll();
			
			if(current[0] >= earliest[1]) {//start of new input is compare with end of top of minheap
				earliest[1] = current[1];
			} else {
				minHeap.add(current);
			}
			minHeap.add(earliest);
		}
		System.out.println("heap size "+minHeap.size());
		return minHeap.size();
	}

}
