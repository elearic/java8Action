package algorithm.leetcodecow.HJ13句子逆序;

import java.util.Scanner;

/**
 * @Author: eric
 * @Date: 2022/4/1 12:18 上午
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            String[] s1 = s.split(" ");
            String newStr = "";
            for (int i = s1.length-1; i >=0; i--) {
                newStr+=s1[i];
                newStr+=" ";
            }
            System.out.println(newStr.trim());
        }
    }
}
