public class Solution {
    public static int[] binarySearchRange(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int[] result = new int[3];

        while(high >= low) {
            int middle = (low + high) / 2;
            if(nums[middle] == target) {
                result[0] = low;
                result[1] = middle;
                result[2] = high;
                return result;
            }
            if(nums[middle] < target) {
                low = middle + 1;
            }
            if(nums[middle] > target) {
                high = middle - 1;
            }
        }
        return null;
    }

    public static int BinaryFindEnds(int[] nums, int target, int low, int high, boolean first) {
        if (first) {
            int min = high;
            while (high >= low) {
                int middle = (low + high) / 2;
                if (nums[middle] == target) {
                    min = middle;
                    high = middle - 1;
                } else if (nums[middle] < target) {
                    low = middle + 1;
                }
            }
            return min;
        } else {
            int max = low;
            while (high >= low) {
                int middle = (low + high) / 2;
                if (nums[middle] == target) {
                    max = middle;
                    low = middle + 1;
                } else if (nums[middle] > target) {
                    high = middle - 1;
                }
            }
            return max;
        }
    }
    
    public int[] searchRange(int[] nums, int target) {
        int[] pos = new int[3];
        pos = binarySearchRange(nums, target);
        int[] result = new int[2];
        if (pos != null) {
            result[0] = BinaryFindEnds(nums, target, pos[0], pos[1], true);
            result[1] = BinaryFindEnds(nums, target, pos[1], pos[2], false);
        } else {
            result[0] = -1;
            result[1] = -1;
        }
        return result;
    }
}