package leetcode_19th_jan;

import java.util.LinkedList;
import java.util.List;

public class Print_word_vertically {

	public static void main(String[] args) {
		//System.out.println(printVertically("ABCD ABC AKK"));
		System.out.println(printVertically("CONTEST IS COMING"));
	}

	public static List<String> printVertically(String s) {
        List<String> list = new LinkedList<>();
        String[] sarr = s.split(" ");
        int len = sarr.length;
        int[] right = new int[sarr.length];//at given index largest present or not on right side
        StringBuilder sb = new StringBuilder("");
        
        int largestString = 0;
        
        right[len - 1] = sarr[len - 1].length();
        for(int k = len-2; k >= 0; k--) {
        	right[k] = Math.max(right[k+1], sarr[k].length());
        	largestString = Math.max(largestString , right[k]);
        }
        
        System.out.println(largestString);
        
        for(int j = 0; j < largestString; j++) {//4
            for(int i = 0; i < sarr.length; i++) {//1
            	
            	//1. " "
            	//2. ""
            	//3. charAt(j)
            	
            	
            	if(j < sarr[i].length()) {
            		sb.append(sarr[i].charAt(j));
            	}else {
            		if(right[i] > sarr[i].length()) {
            			sb.append(" ");
            		} else{
            			sb.append("");
            		}
            	}
            }
            list.add(sb.toString());
            sb.setLength(0);
        } 
        
        return list;
    }

}
