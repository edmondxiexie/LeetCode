public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slowNode = head;
        ListNode fastNode = head;
        while(true) {
            if (slowNode.next != null) {
                slowNode = slowNode.next;
            } else {
                return false;
            }

            if (fastNode.next != null 
                && fastNode.next.next != null) 
            {
                fastNode = fastNode.next.next;
            } else {
                return false;
            }

            if (slowNode == fastNode) {
                return true;
            }
        }
    }
}