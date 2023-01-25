package algorithm.leetcode.LRUCache;

import java.util.LinkedHashMap;

/**
 * @Author: eric
 * @Date: 2022/2/23 11:57 下午
 */
public class Solution {

    private LinkedHashMap<Integer, Integer> map;
    private Integer                         capacity;

    public Solution(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Integer value = map.get(key);
            map.remove(key);
            map.put(key, value);
            return value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
            map.put(key, value);
            return;
        }
        if (map.size() < capacity) {
            map.put(key, value);
            return;
        }
        map.remove(map.keySet().iterator().next());
        map.put(key, value);
    }
}
