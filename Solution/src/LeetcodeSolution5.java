import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Edmond on 9/29/16.
 */
public class LeetcodeSolution5 {
    // 148. Sort List
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = getMidNode(head);
        ListNode next = mid.next;
        mid.next = null;
        return mergeTwoList(sortList(head), sortList(next));
    }

    public static ListNode mergeTwoList(ListNode headA, ListNode headB) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (headA != null && headB != null) {
            if (headA.val < headB.val) {
                cur.next = headA;
                headA = headA.next;
            } else {
                cur.next = headB;
                headB = headB.next;
            }
            cur = cur.next;
        }
        cur.next = headA == null ? headB : headA;
        return dummy.next;
    }

    public static ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 143. Reorder List
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode mid = getMidNode(head);
        ListNode next = mid.next;
        mid.next = null;
        next = reverseList(next);

        // head, next
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (head != null && next != null) {
            cur.next = head;
            head = head.next;
            cur = cur.next;

            cur.next = next;
            next = next.next;
            cur = cur.next;
        }
        if (head != null) {
            cur.next = head;
        }
        head = dummy.next;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy.next;
        while (head != null) {
            dummy.next = head;
            head = head.next;
            dummy.next.next = cur;
            cur = dummy.next;
        }
        return dummy.next;
    }

    public static void printList(ListNode head) {
        while (head != null && head.next != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.print(head.val);
        System.out.println();
    }

    // 62. Unique Paths
    public int uniquePaths(int m, int n) {
        int[][] ways = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    ways[i][j] = 0;
                } else {
                    ways[i][j] = ways[i - 1][j] + ways[i][j - 1];
                }
            }
        }
        return ways[m - 1][n - 1];
//        Map<Integer, Integer> valueMap = new HashMap<>();
//        return solveUniquePaths(valueMap, m, n, 0, 0);
    }

//    private int solveUniquePaths(Map<Integer, Integer> valueMap, int rows, int cols, int row, int col) {
//        if (row == rows - 1 && col == cols - 1) {
//            return 1;
//        }
//        if (row > rows - 1 || col > cols - 1) {
//            return 0;
//        }
//        int key = (rows - row) * 1000 + (cols - col);
//        if (valueMap.containsKey(key)) {
//            return valueMap.get(key);
//        }
//        int result = solveUniquePaths(valueMap, rows, cols, row + 1, col)
//                        + solveUniquePaths(valueMap, rows, cols, row, col + 1);
//        valueMap.put(key, result);
//        return result;
//    }

    // 63. Unique Paths II
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        int[][] ways = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    ways[i][j] = 0;
                }
                else if (i == 0 && j== 0) {
                    ways[i][j] = 1;
                }
                else if (i == 0 && j != 0) {
                    ways[i][j] = ways[i][j - 1];
                }
                else if (i != 0 && j == 0){
                    ways[i][j] = ways[i - 1][j];
                }
                else {
                    ways[i][j] = ways[i - 1][j] + ways[i][j - 1];
                }
            }
        }
        return ways[rows - 1][cols - 1];
    }

    // 64. Minimum Path Sum
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] sums = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    sums[i][j] = grid[i][j];
                } else if (i == 0 && j != 0) {
                    sums[i][j] = grid[i][j] + sums[i][j - 1];
                } else if (i != 0 && j == 0) {
                    sums[i][j] = grid[i][j] + sums[i - 1][j];
                } else {
                    sums[i][j] = grid[i][j] + Math.min(sums[i - 1][j], sums[i][j - 1]);
                }
            }
        }
        return sums[rows - 1][cols - 1];
    }

    // 69. Sqrt(x)
    // http://www.cnblogs.com/AnnieKim/archive/2013/04/18/3028607.html
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        double cur = 1;
        double last = 0;
        while (cur != last) {
            last = cur;
            cur = (cur + x / cur) / 2;
        }
        return (int)cur;
    }

    // 73. Set Matrix Zeroes
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<List<Integer>> list = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 0) {
                    List<Integer> pos = new ArrayList<>();
                    pos.add(row);
                    pos.add(col);
                    list.add(pos);
                }
            }
        }
        for (List<Integer> pos : list) {
            int row = pos.get(0);
            int col = pos.get(1);
            solveSetZeros(matrix, row, col);
        }
    }

    private void solveSetZeros(int[][] matrix, int row, int col) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int j = 0; j < cols; j++) {
            matrix[row][j] = 0;
        }
        for (int i = 0; i < rows; i++) {
            matrix[i][col] = 0;
        }
    }

    // 74. Search a 2D Matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = cols - 1;
        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
                continue;
            } else if (matrix[row][col] < target) {
                row++;
                continue;
            }
        }
        return false;
    }

    // 75. Sort Colors
    public static void sortColors(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > cur) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = cur;
        }
    }

    // 77. Combinations
    public static List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        solveCombine(result, list, nums, 0, k);
        return result;
    }

    private static void solveCombine(List<List<Integer>> result, List<Integer> list, int[] nums, int start, int k) {
        if (list.size() == k){
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            solveCombine(result, list, nums, i + 1, k);
            list.remove(list.size() - 1);
        }
    }

    // 78. Subsets
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        result.add(new ArrayList<>(list));
        solveSubSets(result, list, nums, 0);
        return result;
    }

    private void solveSubSets(List<List<Integer>> result, List<Integer> list, int[] nums, int start) {
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            result.add(new ArrayList<>(list));
            solveSubSets(result, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    // 79. Word Search
    public static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] record = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (word.charAt(0) == board[i][j]) {
                    boolean result = solveExist(board, i, j, record, word, 0);
                    if (result) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean solveExist(char[][] board, int row, int col, int[][] record, String word, int index) {
        if (index == word.length()) {
            return true;
        }
        char target = word.charAt(index);
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        if (board[row][col] != target || record[row][col] == 1) {
            return false;
        }
        record[row][col] = 1;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < dirs.length; i++) {
            int nextRow = row + dirs[i][0];
            int nextCol = col + dirs[i][1];
            boolean result = solveExist(board, nextRow, nextCol, record, word, index + 1);
            if (result) {
                return true;
            }
        }
        record[row][col] = 0;
        return false;
    }

    // 80. Remove Duplicates from Sorted Array II
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 2;
        int fast = 2;
        while (fast < nums.length) {
            if (nums[fast] == nums[slow - 2]) {
                fast++;
            } else {
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }
        }
        return slow;
    }

    // 153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int start = 0;
        int end = nums.length - 1;
        while (start != end) {
            if (nums[start] < nums[end]) {
                return nums[start];
            }
            int mid = start + (end - start) / 2;
            if (nums[start] > nums[mid]) {
                end = mid;
                continue;
            } else {
                start = mid + 1;
                continue;
            }
        }
        return nums[start];
    }

    // 81. Search in Rotated Sorted Array II
    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return nums[0] == target;
        }
        int start = 0;
        int end = nums.length - 1;
        return solveSearch(nums, start, end, target);
    }

    private static boolean solveSearch(int[] nums, int start, int end, int target) {
        if (start > end) {
            return false;
        }
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            return true;
        }
        if (nums[start] < nums[mid]) {
            if (nums[start] <= target && nums[mid - 1] >= target) {
                return solveSearch(nums, start, mid - 1, target);
            } else {
                return solveSearch(nums, mid + 1, end, target);
            }
        } else if (nums[start] > nums[mid]) {
            if (nums[mid + 1] <= target && nums[end] >= target) {
                return solveSearch(nums, mid + 1, end, target);
            } else {
                return solveSearch(nums, start, mid - 1, target);
            }
        } else {
            return (solveSearch(nums, start, mid - 1, target)
                    || solveSearch(nums, mid + 1, end, target));
        }
    }

    // 90. Subsets II
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        result.add(new ArrayList<Integer>(list));
        solveSubSetsWithDup(nums, result, list, 0);
        return new ArrayList<>(result);
    }

    private void solveSubSetsWithDup(int[] nums, Set<List<Integer>> result, List<Integer> list, int start) {
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            List<Integer> sortedList = new ArrayList<>(list);
            Collections.sort(sortedList);
            result.add(new ArrayList<Integer>(sortedList));
            solveSubSetsWithDup(nums, result, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    // 91. Decode Ways
    public int numDecodings(String s) {
        int size = s.length();
        if (size <= 0 || s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[size + 1];
        dp[0] = dp[1] = 1;
        for (int i = 1; i < size; ++i) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            }
            if (s.charAt(i - 1) == '1' || s.charAt(i - 1) <= '2' && s.charAt(i) <= '6') {
                dp[i + 1] = dp[i] + dp[i - 1];
            } else {
                dp[i + 1] = dp[i];
            }
        }
        return dp[size];
    }

    // 92. Reverse Linked List II
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        int nums = n - m;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        ListNode slow = head;
        for (int i = 0; i < m - 1; i++) {
            slow = slow.next;
        }
        ListNode fastLast = slow.next;
        ListNode slowNext = slow.next;
        ListNode fast = fastLast.next;
        int count = 0;
        while (count < nums) {
            ListNode tmp = fast.next;
            slow.next = fast;
            fast.next = slowNext;
            fastLast.next = tmp;
            fast = tmp;
            slowNext = slow.next;
            count++;
        }
        return dummy.next;
    }

    // 142. Linked List Cycle II
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    // 147. Insertion Sort List
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        ListNode last = dummy.next;
        ListNode cur = dummy.next.next;
        while (cur != null) {
            if (last.val <= cur.val) {
                last = last.next;
                cur = cur.next;
                continue;
            } else {
                while (head.next.val <= cur.val) {
                    head = head.next;
                }
                last.next = cur.next;
                cur.next = head.next;
                head.next = cur;
                head = dummy;
                cur = last.next;
            }
        }
        return dummy.next;
    }

    // Addition: Multiply without multiple
    public static int myMultiple(int k, int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        int quotient = n / 2;
        int remainder = n % 2;
        int result = myMultiple(k, quotient);
        return result + result + myMultiple(k, remainder);
    }

    public static void main(String[] args) {
        ListNode n0 = new ListNode(0);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);


        // 2, 5, 3, 4, 1, 0, 6
        ListNode head = n2;
        n2.next = n5;
        n5.next = n3;
        n3.next = n4;
        n4.next = n1;
        n1.next = n0;
//        n0.next = n6;

        printList(head);
        ListNode result = sortList(head);
        printList(result);
        reorderList(result);
        printList(result);
//        printList(reverseList(sortList(head)));

        int[] nums ={4,4,4,4,4,4,4,0,1,2,3};
//        sortColors(nums);
//        System.out.println(Arrays.toString(nums));

        combine(4, 2);
        char[][] chars = {{'A', 'B', 'C', 'E'},
                          {'S', 'F', 'C', 'S'},
                          {'A', 'D', 'E', 'E'}};
//        System.out.println(exist(chars, "ABCCED"));
        System.out.println(search(nums, 3));

        System.out.println(myMultiple(2, 5));
    }
}
