
public class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        if (nums.length <= 1) {
            return nums.length;
        }
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
