package io.hari.apachecamelintegrationpattern;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestMapDifference {
    @Test
    public void test() {
        Map<String, Integer> left = new HashMap<>();
        left.put("hari", 12);
        left.put("om", 23);
        left.put("yadav", 34);

        Map<String, Integer> right = new HashMap<>();
        right.put("om", 23);
        right.put("prakash", 34);

        MapDifference<String, Integer> difference = Maps.difference(left, right);
        Map<String, Integer> onlyOnLeft = difference.entriesOnlyOnLeft();//left - right
        System.out.println("onlyOnLeft = " + onlyOnLeft);
        Map<String, Integer> onlyOnRight = difference.entriesOnlyOnRight();//right - left
        System.out.println("onlyOnRight = " + onlyOnRight);
    }
}
