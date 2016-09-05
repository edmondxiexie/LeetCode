import java.util.List;
import java.util.Map;
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
    
    public static void main(String[] args) {
        int[] nums = {-13,5,13,12,-2,-11,-1,12,-3,0,-3,-7,-7,-5,-3,-15,-2,14,14,13,6,-11,-11,5,-15,-14,5,-5,-2,0,3,-8,-10,-7,11,-5,-10,-5,-7,-6,2,5,3,2,7,7,3,-10,-2,2,-12,-11,-1,14,10,-9,-15,-8,-7,-9,7,3,-2,5,11,-13,-15,8,-3,-7,-12,7,5,-2,-6,-3,-10,4,2,-5,14,-3,-1,-10,-3,-14,-4,-3,-7,-4,3,8,14,9,-2,10,11,-10,-4,-15,-9,-1,-1,3,4,1,8,1};
        System.out.println(letterCombinations("233"));
        //        System.out.println(twoSum(nums, 5, 0).size());
//        System.out.println(twoSum(nums, 0, 0).toString());
//        System.out.println(threeSum(nums));
//        newNums.add(Arrays.copyOfRange(nums, 0, 2));
//        System.out.println(Arrays.toString(newNums));
        // TODO Auto-generated method stub
        System.out.println(climbStairs(1));
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(4));
    }

}
