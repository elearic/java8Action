package algorithm.leetcode.查询无效交易;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: eric
 * @Date: 2022/3/5 9:12 下午
 */
public class Solution {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<Integer>();
        list.add(8);
        list.add(3);
        list.add(12);
        list.add(1);
        System.out.println(list.toString());
        list.sort(new Comparator<Integer>() {
            @Override public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(list.toString());
    }

    public List<String> invalidTransactions(String[] transactions) {
        return null;
    }
}
