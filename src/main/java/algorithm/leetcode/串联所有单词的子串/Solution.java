package algorithm.leetcode.串联所有单词的子串;

import java.util.*;

/**
 * @Author: eric
 * @Date: 2022/2/21 1:41 上午
 */
public class Solution {
    public static void main(String[] args) {
    }

    public List<Integer> findSubstring(String s, String[] words) {
        int splitSize = words[0].length();
        int splitLength = words.length;

        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i + (splitLength * splitSize) < s.length(); i++) {
            map.put(s.substring(i,i + (splitLength * splitSize)),i);
        }
        List<Integer> list = new ArrayList<>();
        Set<String> set = map.keySet();

        for (String s1 : set) {
            int count = 0;
            for (int i = 0; i < words.length; i++) {
                if (s1.contains(words[i])){
                    count ++;
                }
            }
            if (count == words.length){
                list.add(map.get(s1));
            }
        }

        return list;
    }
}
