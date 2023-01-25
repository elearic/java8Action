package algorithm.huawei;

import java.util.Scanner;

/**
 * @Author: eric
 * @Date: 2022/4/23 11:45 上午
 */
public class Tea03 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();

        int minCost = 0;
        for (int i = 0; i < m; i++) {
            while (in.hasNext()) {
                int jqNum = in.nextInt();
                for (int j = 0; j < jqNum; j++) {
                    String bTime = in.next();
                    String jTime = in.next();
                    int i1 = Integer.parseInt(bTime) + Integer.parseInt(jTime);
                    minCost = Math.max(minCost, i1);
                }
                System.out.println(minCost);
            }
        }
    }
}
