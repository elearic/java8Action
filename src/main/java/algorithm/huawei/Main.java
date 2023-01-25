package algorithm.huawei;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String tag = in.nextLine();
        String data = in.nextLine();
        String[] split = data.split("\\s+");

        for (int i = 0; i < split.length; ) {
            int length = Integer.parseInt(split[i + 2] + split[i + 1], 16);
            if (tag.equals(split[i])) {
                StringBuilder builder = new StringBuilder();
                for (int j = i + 3; j < i + 3 + length; j++) {
                    builder.append(split[j]).append(" ");
                }
                System.out.println(builder.toString());
                break;
            } else {
                i += length + 3;
            }
        }
        in.close();
    }
}
