import org.w3c.dom.ls.LSInput;

import java.time.temporal.Temporal;
import java.util.*;
import java.util.regex.Matcher;

/**
 * Created by Edmond on 10/6/16.
 */
public class LeetcodeSolution6 {
    // 93. Restore IP Addresses
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        solveRestoreIpAddresses(result, s, "", 0, "", 0);
        return result;
    }

    private static void solveRestoreIpAddresses(List<String> result, String s, String Ip, int curIndex, String piece, int times) {
        if (times < 4 && times > 0) {
            Ip = Ip + piece + '.';
            piece = "";
        } else if (times == 4 && curIndex == s.length()) {
            result.add(Ip + piece);
        }
        for (int i = curIndex; i < s.length() && times < 4; i++) {
            piece = piece + s.charAt(i);
            if (piece.length() > 1 && piece.startsWith("0") || Integer.parseInt(piece) > 255) {
                break;
            }
            solveRestoreIpAddresses(result, s, Ip, i + 1, piece, times + 1);
        }
    }

    // 94. Binary Tree Inorder Traversal
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }

    // 96. Unique Binary Search Trees
    public int numTrees(int n) {
        //http://bangbingsyb.blogspot.com/2014/11/leetcode-unique-binary-search-trees-i-ii.html
        int[] numBST = new int[n + 1];
        numBST[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                numBST[i] += numBST[j] * numBST[i - j - 1];
            }
        }
        return numBST[n];
    }

    // 95. Unique Binary Search Trees II
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateDiffTrees(1, n);
    }

    private List<TreeNode> generateDiffTrees(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = generateDiffTrees(start, i - 1);
            List<TreeNode> right = generateDiffTrees(i + 1, end);
            for (int j = 0; j < left.size(); j++) {
                for (int k = 0; k < right.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = left.get(j);
                    root.right = right.get(k);
                    result.add(root);
                }
            }
        }
        return result;
    }

    // 98. Validate Binary Search Tree
    private TreeNode lastNode;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (lastNode != null && lastNode.val >= root.val) {
            return false;
        }
        lastNode = root;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }

    // 103. Binary Tree Zigzag Level Order Traversal
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> curLevel = new Stack<>();
        Stack<TreeNode> nextLevel = new Stack<>();
        Stack<TreeNode> tmp = new Stack<>();
        boolean isCurNormal = true;
        curLevel.push(root);

        while (!curLevel.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            while (!curLevel.isEmpty()) {
                TreeNode cur = curLevel.pop();
                list.add(cur.val);
                if (isCurNormal) {
                    if (cur.left != null) {
                        nextLevel.push(cur.left);
                    }
                    if (cur.right != null) {
                        nextLevel.push(cur.right);
                    }
                } else {
                    if (cur.right != null) {
                        nextLevel.push(cur.right);
                    }
                    if (cur.left != null) {
                        nextLevel.push(cur.left);
                    }
                }
            }

            result.add(list);
            tmp = curLevel;
            curLevel = nextLevel;
            nextLevel = tmp;
            isCurNormal = !isCurNormal;
        }
        return result;
    }

    // 144. Binary Tree Preorder Traversal
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            result.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return result;
    }

    // 108. Convert Sorted Array to Binary Search Tree
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int start = 0;
        int end = nums.length - 1;
        return solveSortedArrayToBST(nums, start, end);
    }

    private TreeNode solveSortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode cur = new TreeNode(nums[mid]);
        cur.left = solveSortedArrayToBST(nums, start, mid - 1);
        cur.right = solveSortedArrayToBST(nums, mid + 1, end);
        return cur;
    }

    // 109. Convert Sorted List to Binary Search Tree
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode mid = findListMid(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(removeLastNode(left));
        root.right = sortedListToBST(right);
        return root;
    }

    private ListNode findListMid(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode removeLastNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next.next != null) {
            cur = cur.next;
        }
        System.out.println(cur.next.val);
        cur.next = null;
        return head;
    }

    // 113. Path Sum II
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> list = new ArrayList<>();
        solvePathSum(root, sum, result, list);
        return result;
    }

    private void solvePathSum(TreeNode root, int sum, List<List<Integer>> result, List<Integer> list) {
        if (root == null) {
            return;
        }
        int curVal = root.val;
        if (root.left == null && root.right == null && curVal > sum) {
            return;
        }
        if (root.left == null && root.right == null && curVal == sum) {
            list.add(curVal);
            result.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        list.add(curVal);
        solvePathSum(root.left, sum - curVal, result, list);
        solvePathSum(root.right, sum - curVal, result, list);
        list.remove(list.size() - 1);
    }

    // 114. Flatten Binary Tree to Linked List
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode right = root.right;
                // right most node of the left part
                TreeNode rightMost = findRightMost(root.left);
                root.right = root.left;
                rightMost.right = right;
                root.left = null;
            }
            root = root.right;
        }
    }

    private TreeNode findRightMost(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    // 116. Populating Next Right Pointers in Each Node
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeLinkNode> curLevel = new LinkedList<>();
        Queue<TreeLinkNode> nextLevel = new LinkedList<>();
        Queue<TreeLinkNode> tmp = new LinkedList<>();

        curLevel.offer(root);
        while (!curLevel.isEmpty()) {
            while (!curLevel.isEmpty()) {
                TreeLinkNode cur = curLevel.poll();
                if (cur.left != null) {
                    nextLevel.offer(cur.left);
                }
                if (cur.right != null) {
                    nextLevel.offer(cur.right);
                }
                cur.next = curLevel.peek();
            }
            tmp = curLevel;
            curLevel = nextLevel;
            nextLevel = tmp;
        }
    }

    // 120. Triangle
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int rows = triangle.size();
        int cols = triangle.size();
        for (int row = rows - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                int left = triangle.get(row + 1).get(col);
                int right = triangle.get(row + 1).get(col + 1);
                int val = triangle.get(row).get(col);
                triangle.get(row).set(col, val + Math.min(left, right));
            }
        }
        return triangle.get(0).get(0);

    }

    // 122. Best Time to Buy and Sell Stock II
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int sum = 0;
        int buy = prices[0];
        int sell = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= sell) {
                sell = prices[i];
                if (i == prices.length - 1) {
                    sum += sell - buy;
                }
            } else {
                sum += sell - buy;
                buy = prices[i];
                sell = buy;
            }
        }
        return sum;
    }

    // 127. Word Ladder
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        HashMap<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1);
        if (wordList.contains(beginWord)) {
            wordList.remove(beginWord);
        }
        while(!queue.isEmpty()){
            String top = queue.poll();
            int length = top.length();
            StringBuilder strTry;

            int level = map.get(top);
            for (int i = 0; i < length; i++) {
                strTry = new StringBuilder(top);
                for (char c = 'a'; c <= 'z'; c++) {
                    strTry.setCharAt(i, c);
                    String tmpStr = strTry.toString();
                    if (tmpStr.equals(top)) {
                        continue;
                    }
                    if (tmpStr.equals(endWord)) {
                        return level + 1;
                    }
                    if (wordList.contains(tmpStr)) {
                        queue.offer(tmpStr);
                        wordList.remove(tmpStr);
                        map.put(tmpStr, level + 1);
                    }
                }
            }
        }
        return 0;
    }

    // 129. Sum Root to Leaf Numbers
    private int sumNumbersRes;
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sumNumbersRes = 0;
        solveSumNumbers(root, 0);
        return sumNumbersRes;
    }

    private void solveSumNumbers(TreeNode root, int num) {
        num = num * 10 + root.val;
        if (root.left == null && root.right == null) {
            sumNumbersRes += num;
            return;
        }
        if (root.left != null) {
            solveSumNumbers(root.left, num);
        }
        if (root.right != null) {
            solveSumNumbers(root.right, num);
        }
    }

    // 130. Surrounded Regions
//    public void solve(char[][] board) {
//        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
//            return;
//        }
//        int rows = board.length;
//        int cols = board[0].length;
//        for (int row = 0; row < rows; row++) {
//            for (int col = 0; col < cols; col++) {
//                if (board[row][col] == 'O') {
//                    solveHelper(board, row, col);
//                }
//            }
//        }
//    }

    private boolean solveHelper(char[][] board, int row, int col) {
        int[] rowDirs = {-1, +1, 0, 0};
        int[] colDirs = {0, 0, -1, +1};
        for (int dir = 0; dir < 4; dir++) {
            int nextRow = row + rowDirs[dir];
            int nextCol = col + colDirs[dir];
            if (nextRow < 0 || nextRow >= board.length || nextCol < 0 || nextCol >= board[0].length) {
                return false;
            }
            if (board[nextRow][nextCol] == 'X') {
                continue;
            }
            if (board[nextRow][nextCol] == 'O') {
                board[row][col] = 'X';
                boolean result = solveHelper(board, nextRow, nextCol);
                if (!result) {
                    board[row][col] = 'O';
                    return false;
                } else {
                    return true;
                }
            }
        }
        board[row][col] = 'X';
        return true;
    }

    private void DFS(char[][] board, int row, int col) {
        board[row][col] = '1';
        if (row + 1 < board.length && board[row + 1][col] == 'O') {
            DFS(board, row + 1, col);
        }
        if (row - 1 > 0 && board[row - 1][col] == 'O') {
            DFS(board, row - 1, col);
        }
        if (col - 1 > 0 && board[row][col - 1] == 'O') {
            DFS(board, row, col - 1);
        }
        if (col + 1 < board[0].length && board[row][col + 1] == 'O') {
            DFS(board, row, col + 1);
        }
    }

    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }
        int rows = board.length;
        int cols = board[0].length;
        for (int row = 0; row < rows; row++) {
            if (board[row][0] == 'O') {
                DFS(board, row, 0);
            }
        }
        for (int row = 0; row < rows; row++) {
            if (board[row][cols - 1] == 'O') {
                DFS(board, row, cols - 1);
            }
        }
        for (int col = 0; col < cols; col++) {
            if (board[rows - 1][col] == 'O') {
                DFS(board, rows - 1, col);
            }
        }
        for (int col = 0; col < cols; col++) {
            if (board[0][col] == 'O') {
                DFS(board, 0, col);
            }
        }

        for(int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == '1') {
                    board[row][col] = 'O';
                } else if (board[row][col] == 'O') {
                    board[row][col] = 'X';
                }
            }
        }
    }

    // 131. Palindrome Partitioning
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        List<String> list = new ArrayList<>();
        solvePartition(s, result, list);
        return result;
    }

    private static void solvePartition(String s, List<List<String>> result, List<String> list) {
        if (s == null || s.length() == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int end = 1; end <= s.length(); end++) {
            String sub = s.substring(0, end);
            if (isPalindrome(sub)) {
                list.add(sub);
                solvePartition(s.substring(end, s.length()), result, list);
                list.remove(list.size() - 1);
            } else {
                continue;
            }
        }
    }

    private static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 1) {
            return true;
        }
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // 134. Gas Station
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = gas.length - 1;
        int end = 0;
        int sum = gas[start] - cost[start];
        while (start > end) {
            if (sum >= 0) {
                sum += gas[end] - cost[end];
                end++;
            } else {
                start--;
                sum += gas[start] - cost[start];
            }
        }
        if (sum >= 0) {
            return start;
        } else {
            return -1;
        }


//        for (int start = 0; start < gas.length; start++) {
//            boolean departure = false;
//            int i = start;
//            int tank = 0;
//            while (true) {
//                if (i == start && departure) {
//                    return i;
//                }
//                departure = true;
//                tank += gas[i];
//                if (tank < cost[i]) {
//                    break;
//                }
//                i = (i + 1) % gas.length;
//            }
//        }
//        return -1;
    }

    // 139. Word Break
    public boolean wordBreak(String s, Set<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(i, s.length());
            dp[i] = wordDict.contains(sub);
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            if (!dp[i]) {
                for (int j = i; j < s.length(); j++) {
                    String sub = s.substring(i, j + 1);
                    if (wordDict.contains(sub) && dp[j + 1]) {
                        dp[i] = true;
                    }
                }
            }
        }
        return dp[0];
    }

    private boolean solveWordBreak(String s, Set<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        for (int end = 1; end <= s.length(); end++) {
            String sub = s.substring(0, end);
            if (wordDict.contains(sub)) {
                boolean result = solveWordBreak(s.substring(end, s.length()), wordDict);
                if (result) {
                    return true;
                }
            }
        }
        return false;
    }

    // 150. Evaluate Reverse Polish Notation
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(first + second);
            } else if (token.equals("-")) {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(first - second);
            } else if (token.equals("*")) {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(first * second);
            } else if (token.equals("/")) {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(first / second);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
//        System.out.println(restoreIpAddresses("25525511135"));
        String s = "a";
        System.out.println(isPalindrome("aa"));
//        System.out.println(partition("aab"));
    }
}
