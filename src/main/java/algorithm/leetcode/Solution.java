package algorithm.leetcode;

import java.util.List;

/**
 * @Author: eric
 * @Date: 2022/2/12 9:23 上午
 */
public class Solution {
    public static void main(String[] args) {
        Character har = 'a';
        Solution solution = new Solution();
        solution.isOneBitCharacter(new int[] { 0, 1, 0 });
    }

    /**
     * 两数之和
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int returnArr[] = new int[] { -1, -1 };
        for (int i = 0; i < nums.length; i++) {
            boolean flag = false;
            for (int j = 0; j < returnArr.length; j++) {
                if (i == returnArr[j]) {
                    flag = true;
                }
            }
            if (flag) {
                continue;
            }
            boolean flag1 = false;
            for (int k = i + 1; k < nums.length; k++) {
                if (nums[i] == target - nums[k]) {
                    returnArr[0] = i;
                    returnArr[1] = k;
                    flag1 = true;
                    continue;
                }
            }
            if (flag1) {
                break;
            }
        }
        return returnArr;
    }

    /**
     * 两数之差
     * @param nums
     * @param target
     * @return
     */
    public static int countKDifference(int[] nums, int target) {
        int count = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int k = i + 1; k < len; k++) {
                if (nums[i] - nums[k] == target || nums[i] - nums[k] == -target) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isOneBitCharacter(int[] bits) {
        int length = bits.length - 1;
        boolean flag = true;
        for (int i = 0; i < length; i++) {
            int bit = bits[i];
            if (bit == 1) {
                i++;
            }
            if (i >= length){
                flag = false;
            }
        }
        return flag;
    }

    public List<String> findRepeatedDnaSequences(String s) {


        return null;
    }
}