package algorithm.leetcode.丑数;

/**
 * @Author: eric
 * @Date: 2022/2/23 12:14 上午
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.nthUglyNumber(15);
    }

    public int nthUglyNumber(int n) {
        int i = 1;
        int index = 1;
        while (true){
            boolean flag = isUgly(i);
            if (flag){
                index ++;
                if (index == n){
                    break;
                }
            }
            i++;
        }
       return i;
    }

    public boolean isUgly(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}
