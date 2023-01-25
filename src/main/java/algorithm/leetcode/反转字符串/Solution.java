package algorithm.leetcode.反转字符串;

/**
 * @Author: eric
 * @Date: 2022/3/28 11:20 下午
 */
public class Solution {

    public static void main(String[] args) {

        int i = '1' ^ '2';

        System.out.println(i);
    }

    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            char rr = s[l];
            char ll = s[r];
            s[r] = rr;
            s[l] = ll;
            l++;
            r--;
        }
    }

}
