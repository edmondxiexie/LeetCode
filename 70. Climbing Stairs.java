    // 70. Climbing Stairs
    // 此题其实是一个动态规划问题，其本质是一个菲波那切数列。
    // 单序列型动态规划问题 - Sequence DP
    // 考虑要上的台阶数是n，则step(n) = step(n-1) + step(n-2)。
    // 因为对于n-1的情况，只能是再上一阶台阶成为n，而对于n-2的情况，如果走一步，那么久和n-1一样了，因此若要不同就只能走2步直接变成n。
public class Solution {
    public int climbStairs(int n) {
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
}