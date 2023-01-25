package algorithm.leetcode.无重复字符的最长子串;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: eric
 * @Date: 2022/2/21 12:46 上午
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.lengthOfLongestSubstring("abba");
    }

    public int lengthOfLongestSubstring(String s) {
        // 存放各个字符出现的最大长度
        Map<Character, Integer> map = new HashMap<>();
        // 最大值
        int max = 0;
        // start滑动窗口的左边界, end为滑动窗口的右边界
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            if (map.containsKey(s.charAt(end))) {
                start = Integer.max(start, map.get(s.charAt(end)) + 1);
            }
            map.put(s.charAt(end), end);
            max = Integer.max(max, end - start + 1);
        }
        return max;
    }
}
