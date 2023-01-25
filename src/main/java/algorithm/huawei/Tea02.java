package algorithm.huawei;

import java.util.Scanner;

/**
 * @Author: eric
 * @Date: 2022/4/23 11:26 上午
 */
public class Tea02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long s = in.nextLong();
        int y = in.nextInt();

        long now = 1;
        for (int i = 0; i < y; i++) {
            now *= 26;
        }

        int ans = 0;
        while (now < s) {
            now *= 10;
            ans++;
        }
        System.out.println(ans);
    }
}
