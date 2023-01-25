package algorithm.huawei;

import java.util.Scanner;

/**
 * @Author: eric
 * @Date: 2022/4/23 11:03 上午
 */
public class Tea01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().split(",");

        String s = "";
        for (String st : str) {
            s += st;
        }
        String[] newStr = s.split("0");
        int count = 0;
        for (int i = 0; i < newStr.length; i++) {
            int length = newStr[i].length();

            if (length == 0) {
                continue;
            }

            if (length % 3 != 0) {
                count += (length - length % 3) / 3 + 1;
            } else {
                count += length / 3;
            }
        }
        System.out.println(count);
    }
}
