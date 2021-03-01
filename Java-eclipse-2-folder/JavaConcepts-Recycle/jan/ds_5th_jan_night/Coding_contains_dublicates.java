package ds_5th_jan_night;

import java.util.HashSet;
import java.util.Set;

public class Coding_contains_dublicates {
	public static void main(String[] args) {
		int[] arr = {1,2,3,1};
		System.out.println(containsDuplicate(arr));
	}
	
	public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++){
            if(set.contains(nums[i]) == false){//not contain
                set.add(nums[i]);
            }else //contain that mean dublicate
                return true;
        }
        return true;
    }
}
