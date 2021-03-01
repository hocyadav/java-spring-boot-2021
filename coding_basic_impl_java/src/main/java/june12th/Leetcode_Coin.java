package june12th;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Leetcode_Coin {
	public static void main(String[] args) {
		Solution obj = new Solution();
		int[] arr = {1,2,3};
		int amount = 20;
		int coinChange = obj.coinChange(arr, amount);
		System.out.println(coinChange);
	}
}

class Solution {
    int result = Integer.MAX_VALUE;
    List<Integer> list = new LinkedList();//command + shift + o : import
    Map<Integer, Integer> map = new HashMap();//amount , result
    public int coinChange(int[] coins, int amount) {
        int level = 0;
        rec(coins, amount, level);
        Collections.sort(list);
        System.out.println(result);
        System.out.println(list);
        
        if(list.size() == 0) {
            return -1;
        }
        return list.get(0);
    }
    public void rec(int[] coins, int amount, int l) {
        if(amount == 0) {
            result = Math.min(result, l);
            list.add(l);
            return;
        }
        if(amount < 0) {
            //result = Math.min(result, -1);
            //list.add(-1);
            return;
        }
        for(int i = 0; i < coins.length; i++) {
            int am = amount - coins[i];
            System.out.println(l);
            if(am >= 0) {
                rec(coins, am, l + 1);
            }
        }
    }
    
}
