import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] {i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
        
    }
    
    public static int[] twoSumTwoPass(int[] nums, int target) {
        Map<Integer, Integer> myMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            myMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int comp = target - nums[i];
            if(myMap.containsKey(comp) && (myMap.get(comp) != i)) {
                return new int[] {i, myMap.get(comp)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    
    public static int[] twoSumOnePass(int[] nums, int target) {
        Map<Integer, Integer> myMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int comp = target - nums[i];
            if (myMap.containsKey(comp)) {
                return new int[] {myMap.get(comp), i};
            }
            myMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 18;
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(twoSum(nums, target)));
        // TODO Auto-generated method stub

    }

}
