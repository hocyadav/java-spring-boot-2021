package ds_5th_Nov_Evening;

import java.util.Arrays;
/**
 * 
 * @author Hariom Yadav - Nov 5, 2019
 *
 */
public class KthLarger {
	public static void main(String[] args) {
		int[] ary = {10,9,8,7,6,55};
		finKth(ary);
	
	}
	//https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/413844/Super-easy-solution
	private static void finKth(int[] ary) {
		Arrays.sort(ary);
		System.out.println(ary[ary.length - 1]);
	}
}
