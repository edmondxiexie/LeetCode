public class Solution {
    public int reverse(int x) {
        if (x == 0) {
            return x;
        }
        int result = 0;
        int absX = Math.abs(x);
        int sign = x / absX;
        while (absX > 0) {
            int temp = result * 10 + absX % 10;
            if (temp / 10 != result) {
                // overflow
                return 0;
            }
            absX = absX / 10;
            result = temp;
        }
        return result * sign;
    }
}