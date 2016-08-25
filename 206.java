public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

// iteration
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode next;
        pre.next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}

// recursion
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head.next;
        ListNode reversedCur = reverseList(cur);

        head.next = null;
        cur.next = head;

        return reversedCur;
    }
}