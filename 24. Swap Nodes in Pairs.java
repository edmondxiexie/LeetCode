/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head; 
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode former = dummy;
        ListNode cur = dummy.next;
        dummy.next = cur.next;

        while (cur != null && cur.next != null) {
            former.next = cur.next;
            cur.next = cur.next.next;
            former.next.next = cur;
            
            cur = cur.next;
            former = former.next.next;
        }
        return dummy.next;
    }
}