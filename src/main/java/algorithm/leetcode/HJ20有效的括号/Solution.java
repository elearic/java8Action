package algorithm.leetcode.HJ20有效的括号;

/**
 * @Auther:huns
 * @CreateTime: 2022-05-24 01:12
 */

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * Stack
 * push 把项压入堆栈顶部
 * pop 移除堆栈顶部的对象，并作为此函数的值返回该对象。
 * peek 查看堆栈顶部的对象，但不从堆栈中移除它
 * empty 测试堆栈是否为空
 * search 返回对象在堆栈中的位置，以1为基数。如果对象 o 是堆栈中的一个项。此方法返回距堆栈顶部最近的出现位置到堆栈顶部的距离;
 * 堆栈中最顶部项的距离为1。使用equals 方法比较 o 与堆栈中的项
 */
public class Solution {

    public static void main(String[] args) {
        isValid("{[]}");
    }

    public static boolean isValid(String s) {
        if (null == s || s.length() == 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.toCharArray().length; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character peek = stack.pop();
                    if (peek != '(') {
                        return false;
                    }
                }
            }
            if (c == '}') {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character peek = stack.pop();
                    if (peek != '{') {
                        return false;
                    }
                }

            }
            if (c == ']') {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character peek = stack.pop();
                    if (peek != '[') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}