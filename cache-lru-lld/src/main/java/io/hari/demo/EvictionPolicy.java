package io.hari.demo;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author Hariom Yadav
 * @create 21-03-2021
 */
public class EvictionPolicy<Key> {//use dll + hashmap
    Deque<Key> dll = new LinkedList<>();
    Map<Key, Deque<Key>> mapEvict = new HashMap<>();


    public void keyAccess(Key key) {
        if (mapEvict.containsKey(key)) {

        } else {

        }
    }

    public Key evictKey() {
        return null;
    }
}
