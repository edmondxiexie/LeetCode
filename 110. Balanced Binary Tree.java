/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        else if (node.left == null && node.right == null) {
            return 1;
        }
        else {
            int left = getDepth(node.left);
            int right = getDepth(node.right);
            return 1 + (left > right ? left : right);
        }
    }
    
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        System.out.println(left);
        System.out.println(left);
        System.out.println("---");
        if (left - right > 1 || right -left > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }
}