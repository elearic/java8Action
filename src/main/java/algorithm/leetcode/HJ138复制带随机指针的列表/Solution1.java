package algorithm.leetcode.HJ138复制带随机指针的列表;

import java.util.HashMap;

public class Solution1 {

    /**
     * 方式一: 通过HashMap 实现
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (null == head) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node currentNode = head;
        while (null != currentNode) {
            if (!map.containsKey(currentNode)) {
                map.put(currentNode, new Node(currentNode.val));
            }
            if (null != currentNode.next) {
                if (map.containsKey(currentNode.next)) {
                    map.get(currentNode).next = map.get(currentNode.next);
                } else {
                    Node newNode = new Node(currentNode.next.val);
                    map.get(currentNode).next = newNode;
                    map.put(currentNode.next, newNode);
                }
            }
            if (null != currentNode.random) {
                if (map.containsKey(currentNode.random)) {
                    map.get(currentNode).random = map.get(currentNode.random);
                } else {
                    Node newNode = new Node(currentNode.random.val);
                    map.get(currentNode).random = newNode;
                    map.put(currentNode.random, newNode);
                }
            }
            currentNode = currentNode.next;
        }
        return map.get(head);
    }
}