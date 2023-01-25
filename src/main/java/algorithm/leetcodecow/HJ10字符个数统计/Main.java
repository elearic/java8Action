package algorithm.leetcodecow.HJ10字符个数统计;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: eric
 * @Date: 2022/4/1 12:07 上午
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> set = new HashSet<>();
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            for (int i = 0; i < s.length(); i++) {
                Integer integer = Integer.valueOf(s.charAt(i));
                if (0 <= integer && integer <= 127){
                    set.add(integer);
                }
            }
            System.out.println(set.size());
        }
    }
}
