package io.hari.demo;

import java.util.Map;

/**
 * @Author Hariom Yadav
 * @create 21-03-2021
 */
public class Storage_HashMap_n_DLL<Key, Value> {

    Map<Key, Value> map;
    private int capacity;
//    Deque<Key> deque;//not here this is used for eviction policy


    public Storage_HashMap_n_DLL(Map<Key, Value> map, int capacity) {
        this.map = map;
        this.capacity = capacity;
    }

    //TODO : add null checks
    public void add(Key key, Value data) {
        map.put(key, data);
    }

    public Value get(int key) {
        final Value value = map.get(key);
        return value;
    }

    public void remove(Key data) {
        map.remove(data);
    }

    public boolean isStorageFull() {
        return map.size() == this.capacity;
    }
}
