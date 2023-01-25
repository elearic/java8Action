package algorithm.leetcodecow.HJ8合并表记录;

import java.util.*;

/**
 * @Author: eric
 * @Date: 2022/3/13 1:14 上午
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            String s1 = scanner.nextLine();
            if ("".equals(s1)){
                continue;
            }
            String[] split1 = s1.split(" ");
            sb.append(split1[0]).append(" ").append(split1[1]).append(";");
        }

        TreeMap<Integer, Integer> hashMap = new TreeMap<Integer, Integer>();
        String[] split = sb.toString().split(";");
        for (int i = 0; i < split.length; i++) {
            String[] s = split[i].split(" ");
            String index = s[0];
            String value = s[1];

            Integer orDefault = hashMap.getOrDefault(Integer.parseInt(index), 0);
            orDefault += Integer.parseInt(value);
            hashMap.put(Integer.parseInt(index), orDefault);
        }

        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " " +entry.getValue());
        }
    }
}
