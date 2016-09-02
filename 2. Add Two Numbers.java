
public class Solution {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { 
            val = x;
        }
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        int carry = 0;
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode head = new ListNode(0);
        ListNode curr = head;

        while (p1 != null || p2 != null) {
            int x = (p1 != null) ? p1.val : 0;
            int y = (p2 != null) ? p2.val : 0;
            sum = x + y + carry;
            carry =  sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }           
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return head.next;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
}
