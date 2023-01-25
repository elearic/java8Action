package algorithm.leetcodecow.HJ46截取字符串;

import java.util.Scanner;

/**
 * @Author: eric
 * @Date: 2022/3/30 9:23 下午
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int num = scanner.nextInt();
        if (num == 0) {
            System.out.println();
            return;
        }
        System.out.println(line.substring(0, num));

    }
}
