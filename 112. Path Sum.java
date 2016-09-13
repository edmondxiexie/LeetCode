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
    
        public boolean isSum(TreeNode node, int curSum, int target) {
        if (node == null) {
            return false; 
        }
        
        curSum = curSum + node.val;
        
        // 如果是叶节点，并且和等于目标值
        if (node.left == null && node.right == null && curSum == target) {
            return true;
        }
        
        // 处理左子树
        if (node.left != null) {
            boolean result = isSum(node.left, curSum, target);
            if (result == true) {
                return result;
            }
        }
        
        // 处理右子树
        if (node.right != null) {
            boolean result = isSum(node.right, curSum, target);
            if (result == true) {
                return result;
            }
        }
        
        return false;
    }


    public boolean hasPathSum(TreeNode root, int sum) {
        int curSum = 0;
        return isSum(root, curSum, sum);
    }
}