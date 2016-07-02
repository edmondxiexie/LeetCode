// 160. Intersection of Two Linked Lists
// Write a program to find the node at which the intersection of two 
// singly linked lists begins.

// Notes:
// If the two linked lists have no intersection at all, return null.
// The linked lists must retain their original structure after the function 
// returns.
// You may assume there are no cycles anywhere in the entire linked structure.
// Your code should preferably run in O(n) time and use only O(1) memory.

public class Solution {
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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;
        int lengthA = getLength(headA);
        int lengthB = getLength(headB);
        int dif = lengthA - lengthB;
        if (dif > 0) {
            for (int i = 0; i < dif; i++) {
                tempA = tempA.next;
            }
        } else {
            for (int i = 0; i < -dif; i++) {
                tempB = tempB.next;
            }
        }
        while (tempA != null && tempB != null) {
            if (tempA == tempB) {
                return tempA;
            }
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return null;
    }
}