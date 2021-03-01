package ds_5th_Nov_Evening;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
/**
 * 
 * @author Hariom Yadav - Nov 5, 2019
 *
 */
public class KthSmallest {
	
	// Function to find the K'th smallest element in the
		// array using min-heap
		public static int findKthSmallest(List<Integer> A, int k)
		{
			// create an empty min-heap and initialize it with all input elements
			PriorityQueue<Integer> pq = new PriorityQueue<>(A);

			// pop from min-heap exactly (k-1) times
			while (--k > 0) {
				pq.poll();
			}

			// return the root of min-heap
			return pq.peek();
		}

		public static void main(String[] args)
		{
			List<Integer> A = Arrays.asList(7, 4, 6, 3, 9, 1);
			int k = 3;

			System.out.println("K'th smallest element in the array is " +
										findKthSmallest(A, k));
		}
}
