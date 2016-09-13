public class Solution {
    public int singleNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            
            // 只保留出现奇数次的位数
            sum = sum ^ num;
        }
        return sum;
    }
}