package algorithm.leetcodecow.HJ4字符串分隔;

import java.util.Scanner;

/**
 * @Author: eric
 * @Date: 2022/3/31 11:30 下午
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.nextLine();
            while (str.length() > 8){
                System.out.println(str.substring(0,8));
                str = str.substring(8,str.length());
            }
            if (str.length() > 0){
                str += "00000000";
                System.out.println(str.substring(0,8));
            }
        }
    }
}
