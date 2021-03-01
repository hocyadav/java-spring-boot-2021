package march4thOffice;

import java.util.Random;
/**
 * 
 * @author Hariom Yadav | 04-Mar-2020
 *
 */
public class ArraySongSuffle {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9};
		print(arr);
		suffleArray(arr);
	}

	private static void print(int[] arr) {
		for(int i : arr)
			System.out.print(i+" ");
		System.out.println();
	}

	private static void suffleArray(int[] arr) {
		Random random = new Random();
		for(int i = arr.length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			//swap i and j value
			int t = arr[j];
			arr[j] = arr[i];
			arr[i] = t;
		}
		print(arr);
	}
}
/**
1 2 3 4 5 6 7 8 9 
3 6 7 9 8 1 2 5 4 
*/