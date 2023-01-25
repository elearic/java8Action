package algorithm.leetcodecow.HJ75公共子串计算;

import java.util.Scanner;

/**
 * @Author: eric
 * @Date: 2022/2/20 12:31 上午
 */
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int maxCount = 0;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < i; j++) {
                String substring = s1.substring(j, i);
                if (s2.contains(substring) && substring.length() > maxCount){
                    maxCount = substring.length();
                }
            }
        }
        System.out.println(maxCount);
    }
}
