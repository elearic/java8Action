package algorithm.leetcode.爬楼梯;

/**
 * @Author: eric
 * @Date: 2022/2/22 9:42 下午
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public int climbStairs(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}