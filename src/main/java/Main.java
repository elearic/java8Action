import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();

        for (Character c : s.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                Integer cout = map.get(c);
                map.put(c, null == cout ? 1 : cout + 1);
            }
        }

        Collection<Integer> values = map.values();
        List<Integer> list = values.stream().sorted().collect(Collectors.toList());

        for (int i = list.size(); i >= 0; i--) {
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (i == entry.getValue()) {
                    System.out.println((entry.getKey() + ":" + entry.getValue() + ";"));
                }
            }
        }
    }
}

