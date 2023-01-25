package algorithm.leetcode.HJ14最长公共前缀;

/**
 * @Author huns
 * @Date 2022/6/17 0:34
 */
public class Solution {
    public static void main(String[] args) {
        String[] strs = new String[]{"abc","e","ab"};

        String s = longestCommonPrefix(strs);
        System.out.println("===="+s);
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
