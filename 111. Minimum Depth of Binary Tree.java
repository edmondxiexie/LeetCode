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
    private int min = Integer.MAX_VALUE;
    private int cur = 0;
    
    public void getMinDepth(TreeNode node) {
        if (node == null) {
            min = cur;
            return; 
        }
        
        // 当前处理的层次加1
        cur++;
        
        // 如果是叶节点，并且路径比记录的最小还小
        if (node.left == null && node.right == null && cur < min) {
            
            // 更新最小值
            min = cur;
        }
        
        // 处理左子树
        if (node.left != null) {
            getMinDepth(node.left);
        }
        
        // 处理右子树
        if (node.right != null) {
            getMinDepth(node.right);
        }
        
        // 还原
        cur--;
    }
        
    public int minDepth(TreeNode root) {
        getMinDepth(root);
        return min;
    }
}