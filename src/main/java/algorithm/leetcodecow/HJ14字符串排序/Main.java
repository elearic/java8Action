package algorithm.leetcodecow.HJ14字符串排序;

import java.util.*;

/**
 * @Author: eric
 * @Date: 2022/4/1 12:23 上午
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] strArr = new String[n];
        for (int i = 0; i < n; i++) {
            strArr[i] = scanner.next();
        }
        Arrays.sort(strArr);
        for (String s : strArr) {
            System.out.println(s);
        }
    }
}
