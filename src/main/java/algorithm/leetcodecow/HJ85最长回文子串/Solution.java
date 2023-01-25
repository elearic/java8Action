package algorithm.leetcodecow.HJ85最长回文子串;

import java.util.Scanner;

/**
 * @Author: eric
 * @Date: 2022/2/20 11:05 上午
 */
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < line.length(); i++) {
            if (i >= line.length() / 2) {
                break;
            }
            char head = line.charAt(i);
            //对应i的尾端
            char tail = line.charAt(line.length() - 1 - i);
            if (head == tail) {
                if (i - 1 != leftIndex) {
                    leftIndex = i;
                    rightIndex = line.length() - 1 - i;
                } else {
                    leftIndex = Integer.min(leftIndex, i);
                    rightIndex = Integer.max(rightIndex, line.length() - 1 - i);
                }
            } else {
                leftIndex = 0;
                rightIndex = 0;
            }
        }
        System.out.println(rightIndex - leftIndex + 1);
    }
}
