package algorithm.leetcodecow.HJ9提取不重复的整数;

import java.util.Scanner;

/**
 * @Author: eric
 * @Date: 2022/3/30 9:12 下午
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        String str = number + "";

        String newStr = "";
        int length = str.length();
        for (int i = length -1; i >= 0 ; i --) {
            char c = str.charAt(i);
            if (!newStr.contains(c+"")){
                newStr+= c;
            }
        }
        System.out.println(newStr);
    }
}
