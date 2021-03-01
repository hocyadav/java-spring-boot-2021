package ds_5th_jan_night;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Coding_Amazon {

	public static void main(String[] args) {
		int[] arr = {2,7,11,15};
		int[] arr2 = {1,3,3};
		int target = 9;
		int[] r = twoSum(arr, target);
		System.out.println(r[0]+" "+r[1]);
		
		int[] r2 = two_Sum(arr2, 6);
		System.out.println(r2[0]+" "+r2[1]);
	}
	
	public static int[] twoSum(int[] nums, int target) {
        //take temp hashmap to store value with index coz after sorting index will disturb
        HashMap<Integer, Integer> hmap = new HashMap<>();
        
        for(int i=0; i<nums.length; i++)
            hmap.put(nums[i], i);
        
        //sort original arry
        Arrays.sort(nums);
        
        int i=0;
        int j=nums.length-1;
        int[] r = new int[2];
        while(i<j){
            int sum = nums[i] + nums[j];
            if(sum > target)
                j--;
            else if(sum > target)
                i++;
            else{//equal to target
                r[0] = hmap.get(nums[i]);
                r[1] = hmap.get(nums[j]);
                break;
            }
        }
        return r;
    }
	
	public static int[] two_Sum(int[] nums, int target) {
		//1. hmap
		//2. for loop : find out difference and check that difference present inhmap or not if not then only add in hmap
		int[] result = new int[2];
		Map<Integer, Integer> hmap = new HashMap<>();
		
		for(int i=0; i<nums.length; i++) {
			int diff = target - nums[i];
			if(hmap.containsKey(diff)) {
				result[0] = hmap.get(diff);
				result[1] = i;
				return result;
			}
			hmap.put(nums[i], i);
		}
		return result;
	}
}
