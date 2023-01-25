package algorithm.leetcode.三角形中最小路径之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @Author: eric
 * @Date: 2022/2/22 11:05 下午
 */
public class Solution {
    public static void main(String[] args) {
        List l1 = Arrays.asList(2);
        List l2 = Arrays.asList(3, 4);
        List l3 = Arrays.asList(5, 6, 7);
        List l4 = Arrays.asList(4, 1, 8, 3);
        List<List<Integer>> l5 = new ArrayList();
        l5.add(l1);
        l5.add(l2);
        l5.add(l3);
        l5.add(l4);

        Solution solution = new Solution();
        solution.minimumTotal(l5);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        //最后一行
        List<Integer> lastRow = triangle.get(triangle.size() - 1);
        int num = lastRow.size();
        //以最后一行的长度定义一个宽和高一样的二维数组
        int[][] dp = new int[num][num];

        for (int i = 0; i < lastRow.size(); i++) {
            dp[triangle.size()-1][i] = lastRow.get(i);
        }
        for (int i = num - 2; i >= 0; --i) {
            List<Integer> currentRow = triangle.get(i);
            int currentRowSize = currentRow.size();
            for (int j = 0; j < currentRowSize; j++) {
                dp[i][j] = Integer.min(dp[i + 1][j], dp[i + 1][j + 1]) + currentRow.get(j);
            }
        }
        return dp[0][0];
    }
}
