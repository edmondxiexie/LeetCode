
public class example {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    public static int getLength(ListNode head) {
        int result = 0;
        ListNode currNode = head;
        while (currNode != null) {
            result++;
            currNode = currNode.next;
        }
        return result;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;
        int lengthA = getLength(headA);
        int lengthB = getLength(headB);
        int dif = lengthA - lengthB;
        System.out.println(dif);
        if (dif > 0) {
            for (int i = 0; i < dif; i++) {
                tempA = tempA.next;
            }
        } else {
            for (int i = 0; i < -dif; i++) {
                tempB = tempB.next;
                System.out.println(1);
            }
        }
        System.out.println(tempA);
        System.out.println(tempB);
        while (tempA != null && tempB != null) {
            if (tempA == tempB) {
                return tempA;
            }
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return null;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ListNode a = new ListNode(10);
        ListNode b = new ListNode(20);
        b.next = a;
        ListNode c = a;
        System.out.println(getIntersectionNode(c, b) == c);
        
        System.out.println(c == b.next);
//        System.out.println(a == c);
        
    }

}
