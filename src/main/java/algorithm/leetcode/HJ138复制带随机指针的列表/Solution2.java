package algorithm.leetcode.HJ138复制带随机指针的列表;

public class Solution2 {


    public static void main(String[] args) {
        Node node7 = new Node(7);
        Node node13 = new Node(13);
        Node node11 = new Node(11);
        Node node10 = new Node(10);
        Node node1 = new Node(1);
        node7.next = node13;
        node13.next = node11;
        node11.next = node10;
        node10.next = node1;
        node13.random = node7;
        node11.random = node1;
        node10.random = node11;
        node1.random = node7;

        Node node = copyRandomList(node7);
        System.out.println("node");
    }

    public static Node copyRandomList(Node head) {
        if (null == head) {
            return null;
        }
        coryNext(head);
        copyRandom(head);
        return split(head);
    }

    private static Node split(Node head) {
        Node result = head;
        while (null != head.next) {
            result = head.next;
            result.next = head.next.next;
            result.val = head.next.val;
            result.random = head.next.random;

            if (null != head.next.next) {
                head = head.next.next;
            }
        }
        return result;
    }

    private static void copyRandom(Node head) {
        while (null != head) {
            if (null != head.random && null != head.next) {
                head.next.random = head.random;
            }
            head = head.next.next;
        }
    }

    private static void coryNext(Node head) {
        while (null != head) {
            Node node = new Node(head.val);
            node.next = head.next;
            head.next = node;
            head = node.next;
        }
    }

}
