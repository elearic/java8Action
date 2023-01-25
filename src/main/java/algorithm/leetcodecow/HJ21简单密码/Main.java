package algorithm.leetcodecow.HJ21简单密码;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: eric
 * @Date: 2022/4/1 11:07 下午
 */
public class Main {
    public static Map<String, Integer> map = new HashMap();

    static {
        map.put("1", 1);
        map.put("abc", 2);
        map.put("def", 3);
        map.put("ghi", 4);
        map.put("jkl", 5);
        map.put("mno", 6);
        map.put("pqrs", 7);
        map.put("tuv", 8);
        map.put("wxyz", 9);
        map.put("0", 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.nextLine();
            char[] chars = next.toCharArray();
            String str = "";
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                int ascii = (int) c;
                //大写
                if (65 <= ascii && ascii <= 90) {
                    if (ascii == 90) {
                        str += 'a';
                    } else {
                        char c1 = (char) (ascii + 1 + 32);
                        str += c1;
                    }
                    //小写
                } else if (97 <= ascii && ascii <= 122) {
                    Set<Map.Entry<String, Integer>> entries = map.entrySet();
                    for (Map.Entry<String, Integer> entry : entries) {
                        if (entry.getKey().contains(c + "")) {
                            str += entry.getValue();
                        }
                    }

                    //数字
                } else {
                    str += c;
                }
            }
            System.out.println(str);
        }
    }
}
