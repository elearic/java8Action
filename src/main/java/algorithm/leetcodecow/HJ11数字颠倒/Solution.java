package algorithm.leetcodecow.HJ11数字颠倒;

import java.util.Scanner;

/**
 * @Author: eric
 * @Date: 2022/2/20 12:12 上午
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        String str = String.valueOf(number);
        String newStr = "";
        for (int i = str.length() - 1; i < str.length(); i--) {
            newStr += str.charAt(i);
        }
        System.out.println(newStr);
    }
}
