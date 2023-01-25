package algorithm.leetcode.剑指offer.翻转列表;

/**
 *
 */
public class Solution {

    public static void main(String[] args) {

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        four.next = five;
        three.next = four;
        two.next = three;
        one.next = two;

        reverseBetween(one,2,4);


    }



    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if(null == head || left == right){
            return head;
        }
        ListNode prev = new ListNode(-1);
        prev.next = head;
        ListNode prevLeftNode = prev;
        ListNode current = head;
        for(int i=1;i<left;i++){
            prevLeftNode = current;
            current = current.next;
        }

        int step = right-left;
        for (int i= 0;i < step ;i++){
            ListNode nNext = current.next;
            ListNode nPostNode = nNext.next;
            current.next = nPostNode;
            nNext.next = prevLeftNode.next;
            prevLeftNode.next = nNext;
        }
        return prev.next;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}