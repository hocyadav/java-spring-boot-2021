package feb_6th;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-30
 * Description:https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
 * <p>
 * Given a string you need to print longest possible substring that has exactly M unique characters.
 * If there are more than one substring of longest possible length, then print any one of them.
 * Examples:
 * <p>
 * "aabbcc", k = 1
 * Max substring can be any one from {"aa" , "bb" , "cc"}.
 * <p>
 * "aabbcc", k = 2
 * Max substring can be any one from {"aabb" , "bbcc"}.
 * <p>
 * "aabbcc", k = 3
 * There are substrings with exactly 3 unique characters
 * {"aabbcc" , "abbcc" , "aabbc" , "abbc" }
 * Max is "aabbcc" with length 6.
 * <p>
 * "aaabbb", k = 3
 * There are only two unique characters, thus show error message.
 * [Google] [Amazon]
 */
public class LongestSubstringWith2char {

    public static void main(String []args) {

        System.out.println(longestSubStringKUniqueChar("aabbcc", 3) + " expected : aabbcc");

        System.out.println(longestSubStringKUniqueChar("aabbcc", 2) + "expected : aabb");

        System.out.println(longestSubStringKUniqueChar("aabbcc", 1) + " expected : aa");

        System.out.println(longestSubStringKUniqueChar("aabbcccccc", 1) + " expected : cccccc");

        System.out.println(longestSubStringKUniqueChar("aabacbebebe", 3) + " expected: cbebebe");

        System.out.println(longestSubStringKUniqueChar("aaabbb", 3) + " Expected: <EMPTY>");

        System.out.println(longestSubStringKUniqueChar("eceba", 2) + " Expected: ece");

        System.out.println(longestSubStringKUniqueChar("aa", 1) + " Expected: aa");
    }

    /**
     * time/space = O(n)/O(1)
     *
     * @param string
     * @param k
     * @return
     */
    public static String longestSubStringKUniqueChar(String string, int k) {

        String subStr = "";
        if (null == string || string.isEmpty() || k == 0)
            return subStr;

        System.out.println("\nInput :" + string + " k :" + k);
        string = string.trim();

        char str[] = string.toCharArray();

        Map<Character, Integer> map = new HashMap<>(); //O(1) at max 256 chars

        int totalUnique = 0, countOfUnique = 0;
        int start = 0;

        for (int i = 0; i < str.length; i++) {

            //this is unique char
            if (!map.containsKey(str[i])) {//1. present in map : ++ unique and total
                countOfUnique++;
                totalUnique++;
            }

            map.put(str[i], map.getOrDefault(str[i], 0) + 1);//2. put

            if (countOfUnique == k) {//3. creating final string
                if (subStr.length() < (i - start + 1))
                    subStr = string.substring(start, i + 1);
            }

            while (countOfUnique > k) {
                char c = str[start];
                if (map.get(c) > 0) {
                    map.put(c, map.getOrDefault(c, 1) - 1);

                    if (map.get(c) == 0)
                        countOfUnique--;

                }
                start++;
            }
            
            
        }

        if (totalUnique < k)
//            throw new IllegalArgumentException("input has less unique char then require");
            return "";
        return subStr;

    }


}