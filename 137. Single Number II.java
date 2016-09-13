public class Solution {
    public int singleNumber(int[] nums) {
        // 记录出现过一次的数位
        int ones = 0;
        
        // 记录出现过两次的数位
        int twos = 0;
        
        // 记录出现过三次的数位，ones & twos
        int threes = 0;
        
        for (int num : nums) {
            
            // ones 和 num 里都为1的数位说明出现过两次，放到twos里
            twos = twos | (ones & num);
            
            // ones 为0，num为1的数位说明出现过一次
            ones = ones ^ num;
            threes = ones & twos;
            
            ones = ones & ~threes;
            twos = twos & ~threes;
        }
        return ones;
    }
}