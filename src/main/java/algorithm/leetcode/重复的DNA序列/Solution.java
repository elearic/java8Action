package algorithm.leetcode.重复的DNA序列;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: eric
 * @Date: 2022/2/21 12:40 上午
 */
public class Solution {

    public static void main(String[] args) {

    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap();
        for (int i = 0; i + 10 <= s.length(); i++) {
            String substring = s.substring(i, i + 10);
            Integer cnt = map.getOrDefault(substring, 0);
            if (cnt == 1) {
                list.add(substring);
            }
            map.put(substring, cnt + 1);
        }
        return list;
    }
}
