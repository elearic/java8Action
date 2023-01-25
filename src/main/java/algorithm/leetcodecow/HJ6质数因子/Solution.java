package algorithm.leetcodecow.HJ6质数因子;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: eric
 * @Date: 2022/3/12 7:00 下午
 */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();
        List list = new ArrayList<>();
        for (long i = 2; i <= number;){
            if (number % i == 0){
                list.add(i);
                number = number / i;
                continue;
            }
            if (number == i){
                list.add(i);
                break;
            }
            i++;
        }
        String str = "";
        for (Object o : list) {
            str += o + " ";
        }
        System.out.println(str);
    }
}
