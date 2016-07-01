
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            head = null;
            return head;
        }
        ListNode forNode = head;
        ListNode backNode = head;
        for (int i = 0; i < n; i++) {
            forNode = forNode.next;
        }
        while (forNode.next != null) {
            forNode = forNode.next;
            backNode = backNode.next;
        }
        ListNode targetNode = backNode.next;
        backNode.next = targetNode.next;
        return targetNode;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
