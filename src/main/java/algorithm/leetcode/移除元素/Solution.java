package algorithm.leetcode.移除元素;

/**
 * @Author: eric
 * @Date: 2022/3/22 11:12 下午
 */
public class Solution {
    public static void main(String[] args) {

    }

    public int removeElement(int[] nums, int val) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == val){
                for (int j = i+1; j < size; j++) {
                    nums[j-1] = nums[j];
                }
                i--;
                size--;
            }
        }
        return size;
    }
}
