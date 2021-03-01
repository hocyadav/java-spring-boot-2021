package coding_12th_dec_night;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 
 * @author Hariom Yadav | 12-Dec-2019
 *
 *
 */
public class Count_pairs_2_sorted_array_sum_equal_sum_value {
	public static void main(String[] args) {
		int arr1[] = {3,1,5,7};
		int arr2[] = {8,2,5,3,9};
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		//input is sorted array
		
		int sum = 10;
		
		int count = countPairsInLL_M1(arr1, arr2, sum);//2 for loop/iterator
		System.out.println("method1 : "+count);
		
		int count2 = countPairsInLL_M2(arr1, arr2, sum);//1st array store in hash set
		System.out.println("method2 : "+count2);
		
		int count3 = countPairsInLL_M3(arr1, arr2, sum);//best method : 2 pointer left for arr1 and right for arr2
		System.out.println("method3 : "+count2);
		
		int count4 = countPairsInLL_M4(arr1, arr2, sum);//using binary search
		System.out.println("method4 : "+count4);
		
	}
	
	private static int countPairsInLL_M4(int[] arr1, int[] arr2, int sum) {
		//for each value from 1st array : sum - 1st array value search in 2nd array
		int count = 0;
		for(int i=0; i<arr1.length ; i++) {
			int x = sum - arr1[i];
			
//			if(searchBinarySearchIterative(arr2, 0, arr2.length-1, x)) {
//				count++;
//			}
			if(searchBinarySearchRecursive(arr2, 0, arr2.length-1, x)) {
				count++;
			}
		}
		return count;
	}
	//2,3,4,5,6
	private static boolean searchBinarySearchIterative(int[] arr2, int left, int right, int x) {
		//find mid
		while(left <= right) {
			int mid = (right + left)/2;
			if(x == arr2[mid])
				return true;
			else if(x > arr2[mid])
				left = mid+1;
			else
				right = mid -1;
		}
		return false;
	}

	private static boolean searchBinarySearchRecursive(int[] arr2, int left, int right, int x) {
		//find mid
		if(left > right)//base case
			return false;
		else {//rec case
			int mid = (left + right)/2;
			if(arr2[mid] == x)
				return true;
			else if(x > arr2[mid])
				return searchBinarySearchRecursive(arr2, mid+1, right, x);
			else
				return searchBinarySearchRecursive(arr2, left, mid-1, x);
		
		}
	}
	
	
	//array is sorted : so 2 pointer logic : left and right
	private static int countPairsInLL_M3(int[] arr1, int[] arr2, int sum) {
		int count = 0;
		
		int left_pointer = 0;//start from left : array 1
		int right_pointer = arr2.length - 1;//start from right : array 2
		
		while(left_pointer < arr1.length && right_pointer >= 0) {
			
			int x = arr1[left_pointer] + arr2[right_pointer];
			
			if(x == sum) {
				left_pointer++; right_pointer--;
				
				count++;
			}else if(x > sum)
				right_pointer--;
			else
				left_pointer++;
		}
		
		return count;
	}

	//hashing : use hashset
	private static int countPairsInLL_M2(int[] arr1, int[] arr2, int sum) {
		int count =0;
		
		HashSet<Integer> us = new HashSet<>();
		for(int i: arr1)
			us.add(i);
		
		System.out.println("hash set : "+us);
		
		for(int j : arr2) {
			int x = sum - j;
			if(us.contains(x))
				count++;
		}
		return count;
	}

	//2 for loop
	private static int countPairsInLL_M1(int[] arr1, int[] arr2, int sum) {
		int count =0;
		
		for(int i : arr1) {
			for(int j : arr2) {
				if(i+j == sum)
					count++;
			}
		}
		return count;
	}
}
/**

method1 : 3
hash set : [1, 3, 5, 7]
method2 : 3
method3 : 3
method4 : 3
 */
