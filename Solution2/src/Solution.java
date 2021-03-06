import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Solution {
    
    // 70. Climbing Stairs
    // 此题其实是一个动态规划问题，其本质是一个菲波那切数列。
    // 单序列型动态规划问题 - Sequence DP
    // 考虑要上的台阶数是n，则step(n) = step(n-1) + step(n-2)。
    // 因为对于n-1的情况，只能是再上一阶台阶成为n，而对于n-2的情况，如果走一步，那么久和n-1一样了，因此若要不同就只能走2步直接变成n。
    public static int climbStairs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        // if n >= 3
        int step1 = 1;
        int step2 = 2;
        int temp;
        for (int step = 3; step <= n; step++) {
            temp = step1 + step2;
            step1 = step2;
            step2 = temp;
        }
        return step2;
    }
    
    // 88. Merge Sorted Array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        // merge from the tail of both arrays
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 || j >= 0) {
            if (i < 0) {
                nums1[i + j + 1] = nums2[j];
                j--;
            } 
            else if (j < 0) {
                nums1[i + j + 1] = nums1[i];
                i--;
            }
            else if (nums1[i] >= nums2[j]) {
                nums1[i + j + 1] = nums1[i];
                i--;
            } else {
                nums1[i + j + 1] = nums2[j];
                j--;
            }
        }
        
        // test
        for (int index = 0; index < m + n; index++) {
            System.out.println(nums1[i]);
        }
    }
    
    
//     Definition for a binary tree node.
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }
    
    // 100. Same Tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
        } else {
            return false;
        }
    }
    
    // 15. 3Sum
    public static Set<ArrayList<Integer>> twoSum(int[] nums, int target, int firstIndex) {
        Set<ArrayList<Integer>> result = new HashSet<ArrayList<Integer>>();
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (i == firstIndex) {
                continue;
            }
            if (set.contains(target - nums[i])) {
                ArrayList<Integer> subRes = new ArrayList<Integer>();
                subRes.add(nums[i]);
                subRes.add(target - nums[i]);
                Collections.sort(subRes);
                result.add(subRes);
            }
            set.add(nums[i]);
        }
        return result;    
    }
    
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Set<ArrayList<Integer>> setResult = new HashSet<ArrayList<Integer>>();
        for (int index = 0; index < nums.length; index++) {
            int remaining = -nums[index];
            Set<ArrayList<Integer>> temp = new HashSet<ArrayList<Integer>>();
            temp = twoSum(nums, remaining, index);
            if (temp.size() != 0) {
                for (ArrayList<Integer> subTemp : temp) {
                    subTemp.add(nums[index]);
                    Collections.sort(subTemp);
                    setResult.add(subTemp);
                }  
            }       
        }
//        System.out.println(setResult.toString());
        for (ArrayList<Integer> subSet : setResult) {
//            List<Integer> listSubRes = new ArrayList<Integer>(subSet);
//            Collections.sort(subSet);
            result.add(subSet);
        }
        return result;
    }
    
    // 17. Letter Combinations of a Phone Number
    public static List<String> letterCombinations(String digits) {
        Map<Character, char[]> digitsMap = new HashMap<Character, char[]>();
        digitsMap.put('2', new char[] {'a', 'b', 'c'});
        digitsMap.put('3', new char[] {'d', 'e', 'f'});
        digitsMap.put('4', new char[] {'g', 'h', 'i'});
        digitsMap.put('5', new char[] {'j', 'k', 'l'});
        digitsMap.put('6', new char[] {'m', 'n', 'o'});
        digitsMap.put('7', new char[] {'p', 'q', 'r', 's'});
        digitsMap.put('8', new char[] {'t', 'u', 'v'});
        digitsMap.put('9', new char[] {'w', 'x', 'y', 'z'});

        char[] digitsArray = digits.toCharArray();    
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < digitsArray.length; i++) {
            List<String> temp = new ArrayList<String>();
            char[] letterArray = digitsMap.get(digitsArray[i]);
            if (result.size() == 0) {
                for (char letter : letterArray) {
                    temp.add("" + letter);
                }
            }
            else {
                for (String str : result) {
                    for (char letter : letterArray) {
                        temp.add(str + letter);
                    }
                }
            }
            result = temp;
        }
        return result;
    }
    
    static String electionWinner(String[] votes) {
        if (votes.length == 1) {
            return votes[0];
        }
        int max = 0;
        ArrayList<String> names = new ArrayList<String>();
        Map<String, Integer> voteTable = new HashMap<String, Integer>();
        for (String name : votes) {
            if (voteTable.containsKey(name)) {
                voteTable.put(name, voteTable.get(name) + 1);
            } else {
                voteTable.put(name, 1);
            }
            if (voteTable.get(name) > max) {
                max = voteTable.get(name);
            }
        }
        for (String name : voteTable.keySet()) {
            if (voteTable.get(name) == max) {
                names.add(name);
            }
        }
        Collections.sort(names);
        return names.get(names.size() - 1);
    }
    
    // 22. Generate Parentheses
    public static List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<String>();
        String str = new String();
        if (n < 0) return result;
        tryParenthesis(result, str, n, n);
        return result;
        
    }
    
    public static void tryParenthesis(List<String> result, String str, int left, int right) {
        if (left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            result.add(new String(str));
            return;
        }
        if (left > 0) {
            tryParenthesis(result, str + "(", left - 1, right);
        }
        if (right > 0) {
            tryParenthesis(result, str + ")", left, right - 1);

        }        
    }
    
    // 29. Divide Two Integers
    public static int divide(int dividend, int divisor) {
        if (divisor == 1) return dividend;
        if ((dividend == Integer.MIN_VALUE && divisor == -1) || divisor == 0) {
            return Integer.MAX_VALUE;
        }
        
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            sign = -1;
        }
        long dvd = Math.abs((long)dividend);
        long dvs = Math.abs((long)divisor);
        int power = 31;
        long dvsPower = dvs << power;
        long quotient = 0;
        
        while (dvd >= dvs) {
            
            while (dvsPower > dvd) {
                dvsPower >>= 1;
                power--;
            }
            dvd -= dvsPower;
            quotient += (1L << power);
            
        }
        if (sign < 0) {
            return (int) (0 - quotient);
        } else {
            return (int) quotient;
        }
    }

    // 31. Next Permutation
    public static void nextPermutation(int[] nums) {
        // array print use Arrays.toString(nums)
        // {6, 5, 2, 4, 3, 1} 从后向前找，找到第一个不是降序的元素，这里是2，pivot
        // 从末尾到pivot之间，从后向前找第一个比2大的数，这里是3
        // 交换2和3，pivot之后顺序排列
        
        if (nums.length <= 1) 
            return;
        boolean changed = false;
        int pivot = nums.length - 2;
        while (pivot >= 0) {
            if (nums[pivot] < nums[pivot + 1]) {
                changed = true;
                break;
            }
            pivot--;
        }
        
        if (changed == false) {
            Arrays.sort(nums);
            return;
        }
        
        System.out.println(pivot);
        for (int i = nums.length - 1; i > pivot; i--) {
            if (nums[i] > nums[pivot]) {
                int temp = nums[pivot];
                nums[pivot] = nums[i];
                nums[i] = temp;
                System.out.println(66666);

                break;
            }
        }
        Arrays.sort(nums, pivot + 1, nums.length);
    }
    
    // 102. Binary Tree Level Order Traversal
    
    public void getOrder(List<List<Integer>> result, 
                         List<Integer> subRes,
                         TreeNode root) {
        
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            // necessary step
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }
        return result;    
    }
    
    // 104. Maximum Depth of Binary Tree
    public int maxDepth(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            if (level.size() > 0) {
                depth++;
            }
        }      
        return depth;
    }
    
    // 107. Binary Tree Level Order Traversal II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }            
            result.add(level);
        }
        Collections.reverse(result);
        return result;
    }
    
    // 110. Balanced Binary Tree
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
        if (left - right > 1 || right - left > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }
    
    // 111. Minimum Depth of Binary Tree
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
    
    // 112. Path Sum
//    private int curSum = 0;
    
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
    
    // 118. Pascal's Triangle
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int row = 0; row < numRows; row++) {
            List<Integer> level = new ArrayList<Integer>();
            if (row == 0) {
                level.add(1);
            } else {
                level.add(1);
                for (int i = 0; i < row - 1; i++) {
                    List<Integer> lastLevel = result.get(row - 1);
                    int value = lastLevel.get(i) + lastLevel.get(i + 1);
                    level.add(value);
                }
                level.add(1);
            }
            result.add(level);
        }
        return result;
    }
    
    public static void main(String[] args) {
//        int[] nums = {6, 5, 2, 4, 3, 1};
        int[] nums = {1, 1};
        int n = 10;
        for (int i = 0; i < n; i++) {
            System.out.println(i);
            n--;
        }
//        nextPermutation(nums);
//        System.out.println(Arrays.toString(nums));
//        System.out.println(divide(102, 2));
//        int[] nums = {-13,5,13,12,-2,-11,-1,12,-3,0,-3,-7,-7,-5,-3,-15,-2,14,14,13,6,-11,-11,5,-15,-14,5,-5,-2,0,3,-8,-10,-7,11,-5,-10,-5,-7,-6,2,5,3,2,7,7,3,-10,-2,2,-12,-11,-1,14,10,-9,-15,-8,-7,-9,7,3,-2,5,11,-13,-15,8,-3,-7,-12,7,5,-2,-6,-3,-10,4,2,-5,14,-3,-1,-10,-3,-14,-4,-3,-7,-4,3,8,14,9,-2,10,11,-10,-4,-15,-9,-1,-1,3,4,1,8,1};
//        System.out.println(letterCombinations("233"));
        String[] votes = {"a", "b", "c", "a"};
//        System.out.println(generateParenthesis(3));
//        System.out.println(electionWinner(votes));
        //        System.out.println(twoSum(nums, 5, 0).size());
//        System.out.println(twoSum(nums, 0, 0).toString());
//        System.out.println(threeSum(nums));
//        newNums.add(Arrays.copyOfRange(nums, 0, 2));
//        System.out.println(Arrays.toString(newNums));
        // TODO Auto-generated method stub
//        System.out.println(climbStairs(1));
//        System.out.println(climbStairs(2));
//        System.out.println(climbStairs(3));
//        System.out.println(climbStairs(4));
    }

}
