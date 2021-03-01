package com.example.demojava;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
/**
 * @author HariomYadav
 * @since 17/01/21
 */
public class TestClass {

    @Test
    public void test1() {
        foo();
    }


    public int foo() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        Collections.sort(list);
        int ans = 0;
        for (int i = list.size()/3; i < list.size(); i = i + 2) {
            ans += list.get(i);
        }
        System.out.println("ans = " + ans);
        return ans;
    }
}
