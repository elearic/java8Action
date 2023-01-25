package algorithm.leetcode.反转字符串2;

/**
 * @Author: eric
 * @Date: 2022/3/28 11:40 下午
 */
public class Solution {

    public static void main(String[] args) {
        reverseString("abcd", 4);

    }

    public static String reverseString(String s, int k) {
        char[] chars = s.toCharArray();
        int split = chars.length / (2 * k);

        if (split == 0) {
            if (chars.length < k) {
                int l = 0;
                int r = l + chars.length - 1;
                revert(chars, l, r);
            } else if (chars.length >= k && chars.length < 2 * k) {
                int l = 0;
                int r = l + k - 1;
                revert(chars, l, r);
            }
            String str = "";
            for (int i = 0; i < chars.length; i++) {
                str += chars[i];
            }
            return str;
        }

        for (int i = 0; i < split; i++) {
            int l = i * 2 * k;
            int r = l + k - 1;
            revert(chars, l, r);
        }

        int last = chars.length % (2 * k);
        if (last < k) {
            int l = split * (2 * k);
            int r = l + last - 1;
            revert(chars, l, r);
        } else if (last > k && last < 2 * k) {
            int l = split * (2 * k);
            int r = l + k - 1;
            revert(chars, l, r);
        }

        String str = "";
        for (int i = 0; i < chars.length; i++) {
            str += chars[i];
        }
        return str;
    }

    public static void revert(char[] chars, int l, int r) {

        while (l < r) {
            char rr = chars[l];
            char ll = chars[r];
            chars[l] = ll;
            chars[r] = rr;
            l++;
            r--;
        }
    }
}
