public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int mask = 0x00000001;
        int result = 0;
        // type int is 32 bits
        for (int i = 0; i < 32; i++) {
            if ((n & mask) == mask) {
                result++;
            }
            mask = mask << 1;
        }
        return result;
    }
}