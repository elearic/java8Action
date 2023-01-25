package algorithm.leetcodecow.HJ22汽水瓶;

import java.util.Scanner;

/**
 * @Author: eric
 * @Date: 2022/4/1 11:52 下午
 */
public class Main {
    private static Integer count = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            count = 0;
            int next = scanner.nextInt();
            if (0 == next) {
                return;
            }
            if (next < 3) {
                System.out.println(0);
                return;
            }
            get(next);
            System.out.println(count);
        }
    }

    private static void get(int next) {
        int i1 = next / 3;
        int i2 = next % 3;
        count += i1;
        int i3 = i1 + i2;
        if (i3 == 2){
            count += 1;
            return;
        }
        if (i3 < 2) {
            return;
        }
        get(i3);
    }
}
