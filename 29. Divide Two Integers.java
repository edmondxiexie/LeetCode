public class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 1) return dividend;
        if ((dividend == Integer.MIN_VALUE && divisor == -1) || divisor == 0) {
            return Integer.MAX_VALUE;
        }
        
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            sign = -1;
        }
        long dvd = Math.abs((long)dividend);
        long dvs = Math.abs((long)divisor);
        int power = 31;
        long dvsPower = dvs << power;
        long quotient = 0;
        
        while (dvd >= dvs) {
            
            while (dvsPower > dvd) {
                dvsPower >>= 1;
                power--;
            }
            dvd -= dvsPower;
            quotient += (1L << power);
            
        }
        if (sign < 0) {
            return (int) (0 - quotient);
        } else {
            return (int) quotient;
        }
    }
}