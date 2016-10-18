import java.util.*;

/**
 * Created by Edmond on 9/23/16.
 */
public class LeetcodeSolution4 {
    // 46. Permutations
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> list = new ArrayList<Integer>();
        solve(nums, result, list);
        return result;
    }

    private static void solve(int[] nums, List<List<Integer>> result, List<Integer> list) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            solve(nums, result, list);
            list.remove(list.size() - 1);
        }
    }

    // 47. Permutations II
    public static List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        if (nums == null || nums.length == 0) {
            return new ArrayList<>(result);
        }
        List<Integer> list = new ArrayList<>();
        solveUnique(nums, result, list);
        return new ArrayList<>(result);
    }

    private static void solveUnique(int[] nums, Set<List<Integer>> result, List<Integer> list) {
        // base case
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
            int[] curNums = removeindex(nums, i);
            solveUnique(curNums, result, list);
            list.remove(list.size() - 1);
        }
    }

    private static int[] removeindex(int[] nums, int index) {
        int[] result = new int[nums.length - 1];
        int i = 0;
        int j = 0;
        while (i < result.length) {
            if (j == index) {
                j++;
                continue;
            }
            result[i] = nums[j];
            i++;
            j++;
        }
        return result;
    }

    // 48. Rotate Image
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int level = 0; level < n / 2; level++) {
            for (int step = 0; step < n - 1 - level * 2; step++) {
                int tmp = matrix[level][level + step];
                matrix[level][level + step] = matrix[n - 1 - level - step][level];
                matrix[n - 1 - level - step][level] = matrix[n - 1 - level][n - 1 - level - step];
                matrix[n - 1 - level][n - 1 - level - step] = matrix[level + step][n - 1 - level];
                matrix[level + step][n - 1 - level] = tmp;
            }
        }
    }

    // 49. Group Anagrams
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // set: records the number of every letter
            String set = anagramSet(str);
            if (map.containsKey(set)) {
                List<String> list = map.get(set);
                list.add(str);
                map.put(set, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(set, list);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for (String key : map.keySet()) {
            result.add(map.get(key));
        }
        return result;
    }

    private static String anagramSet(String str) {
        List<Integer> list = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return list.toString();
        }
        for (int i = 0; i < 26; i++) {
            list.add(0);
        }
        char[] letters = str.toCharArray();
        for (char letter : letters) {
            int index = letter - 'a';
            list.set(index, list.get(index) + 1);
        }
        return list.toString();
    }

    // 50. Pow(x, n)
    public static double myPow(double x, int n) {
        if (n == 1) {
            return x;
        }

        if (n == -1) {
            return 1 / x;
        }

        if (n == 0) {
            return 1;
        }

        int sub = n / 2;
        int remainder = n % 2;

        double result = myPow(x, sub);
            return result * result * myPow(x, remainder);
    }

    // 53. Maximum Subarray
    public static int maxSubArray(int[] nums) {
        int curSum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (curSum >= 0) {
                curSum = curSum + nums[i];
            } else {
                curSum = nums[i];
            }
            if (curSum > max) {
                max = curSum;
            }
        }
        return max;
    }

    // 152. Maximum Product Subarray
    public static int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tmpMax = max * nums[i];
            int tmpMin = min * nums[i];
            max = Math.max(Math.max(tmpMax, tmpMin), nums[i]);
            min = Math.min(Math.min(tmpMax, tmpMin), nums[i]);
            result = Math.max(max, result);
        }
        return result;
    }

    // 54. Spiral Matrix
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null) {
            return result;
        }
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        solveSpiralOrder(matrix, 0, result);
        return result;
    }

    private void solveSpiralOrder(int[][] matrix, int level, List<Integer> result) {
        int rows = matrix.length - 2 * level;
        int cols = matrix[0].length - 2 * level;
        if (rows <= 0 || cols <= 0) {
            return;
        }
        for (int col = 0; col < cols; col++) {
            result.add(matrix[level][col + level]);
        }
        for (int row = 1; row < rows; row++) {
            result.add(matrix[row + level][matrix[0].length - 1 - level]);
        }
        if (rows > 1) {
            for (int col = matrix[0].length - 2 - level; col >= 0 + level; col--) {
                result.add(matrix[matrix.length - 1 - level][col]);
            }
        }
        if (cols > 1) {
            for (int row = matrix.length - 2 - level; row >= 1 + level; row--) {
                result.add(matrix[row][level]);
            }
        }
        solveSpiralOrder(matrix, level + 1, result);
    }

    // 59. Spiral Matrix II
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        if (n == 0) {
            return result;
        }
        solveGenerateMatrix(result, 0, 1);
        return result;
    }

    private void solveGenerateMatrix(int[][] result, int level, int curNum) {
        int rows = result.length - 2 * level;
        int cols = result[0].length - 2 * level;
        if (rows <= 0 || cols <= 0) {
            return;
        }
        for (int col = 0; col < cols; col++) {
            result[level][col + level] = curNum;
            curNum++;
        }
        for (int row = 1; row < rows; row++) {
            result[row + level][result[0].length - 1 - level] = curNum;
            curNum++;
        }
        if (rows > 1) {
            for (int col = result[0].length - 2 - level; col >= level; col--) {
                result[result.length - 1 - level][col] = curNum;
                curNum++;
            }
        }
        if (cols > 1) {
            for (int row = result.length - 2 - level; row >= 1 + level; row--) {
                result[row][level] = curNum;
                curNum++;
            }
        }
        solveGenerateMatrix(result, level + 1, curNum);
    }

    // 55. Jump Game
    public boolean canJump(int[] nums) {
        // 不寻找路劲，可用贪心算法
        // 最大可达距离
        int reach = 0;
        int i = 0;
        // i 必须小于reach才是可达的
        while (i < nums.length && i <= reach) {
            reach = Math.max(reach, i + nums[i]);
            i++;
        }
        return reach >= nums.length - 1;
    }

    // 15. 3Sum
    // http://www.cnblogs.com/zihaowang/p/4477575.html
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            twoSum(nums, result, i + 1, nums.length - 1, nums[i]);
        }
        return result;
    }

    public static void twoSum(int[] nums, List<List<Integer>> result, int start, int end, int target) {
        while (start < end) {
            if (nums[start] + nums[end] + target == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(target);
                list.add(nums[start]);
                list.add(nums[end]);
                result.add(new ArrayList<>(list));
                while (start < end && nums[start] == nums[start + 1]) {
                    start++;
                }
                start++;
                while (start < end && nums[end] == nums[end - 1]) {
                    end--;
                }
                end--;
            } else if (nums[start] + nums[end] + target > 0) {
                end--;
            } else {
                start++;
            }
        }
    }

    // 16. 3Sum Closest
    public int threeSumClosest(int[] nums, int target) {
        int minDif = Integer.MAX_VALUE;
        int result = 0;
        if (nums == null || nums.length < 3) {
            return minDif;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int twoSum = twoSumClosest(nums, i + 1, nums.length - 1, target - nums[i]);
            int curDif = Math.abs(nums[i] + twoSum - target);
            if (curDif < minDif) {
                minDif = curDif;
                result = nums[i] + twoSum;
            }
        }
        return result;
    }



    public static int twoSumClosest(int[] nums, int start, int end, int target) {
        int min = Integer.MAX_VALUE;
        int result = 0;
        while (start < end) {
            int absDif = Math.abs(nums[start] + nums[end] - target);
            int curDif = nums[start] + nums[end] - target;
            if (absDif < min) {
                min = absDif;
                result = nums[start] + nums[end];
            }
            if (curDif == 0) {
                return nums[start] + nums[end];
            } else if (curDif > 0) {
                end--;
            } else {
                start++;
            }
        }
        return result;
    }

    // 60. Permutation Sequence
    public static String getPermutation(int n, int k) {
        StringBuilder result = new StringBuilder();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        // 后面可以承载的排列数的数位
        int digitsLeft = n - 1;

        // k starts from 1, make it from 0
        k = k - 1;
        for (int i = 0; i < n; i++) {
            int fact = factorial(digitsLeft);

            // 得出第一位数在nums中的index
            int index = k / fact;
            result.append(nums[index]);

            // 删除已用数位
            for (int j = index; j < digitsLeft; j++) {
                nums[j] = nums[j + 1];
            }
            k = k % fact;
            digitsLeft--;
        }
        return result.toString();
    }

    public static int factorial(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        int result = 1;
        while (n > 0) {
            result = result * n;
            n--;
        }
        return result;
    }

    // 61. Rotate List
    public static ListNode rotateRight(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        if (head == null) {
            return head;
        }
        int length = getLinkedListLength(head);
        k = k % length;

        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }

    public static int getLinkedListLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        return length;
    }

    // 82. Remove Duplicates from Sorted List II
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null && head.next.next != null) {
            if (head.next.val == head.next.next.val) {
                int val = head.next.val;
                while (head.next != null && head.next.val == val) {
                    head.next = head.next.next;
                }
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        List<List<Integer>> res = permute(nums);
        System.out.println(res);
        List<List<Integer>> resUni = permuteUnique(nums);
        System.out.println(resUni);
        System.out.println(Arrays.toString(removeindex(nums, 0)));
        int[][] matrix = new int[2][2];
        int n = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = n;
                n++;
            }
        }
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.println(matrix[i][j]);
            }
        }

//        System.out.println(anagramSet("and").equals(anagramSet("adn")));
//        System.out.println(anagramSet("adn"));
//        System.out.println(-3 / 2);

        int[] nums1 = {-1,-2,-9,-6};
        System.out.println(factorial(5));
        System.out.println(getPermutation(4, 1));

    }
}
