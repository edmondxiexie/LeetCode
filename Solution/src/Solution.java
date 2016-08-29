public class Solution {
    public static String convert(String s, int numRows) {
        // 首行和尾行相邻两个元素之间的距离是2*(numRows - 1)
        // 首行和尾行之间的其他行除了像首位两行一样有间隔距离2*(numRows - 1)的元素，
        // 如，1，7和2，8，在它们之间还有一个元素，该元素到该行下一个元素的距离为2*i，
        // i为所在行数，所以到上一个元素的距离为2*(numRows -1) - 2*i。
        if (numRows <= 1) {
            return s;
        }

        StringBuffer result = new StringBuffer();
        
        for (int row = 0; row < numRows; row++) {
            StringBuffer subResult = new StringBuffer();
    
            for (int i = row; i < s.length(); i += 2*(numRows - 1)) {
                subResult.append(s.charAt(i)); 
                if ((row != 0 && row != numRows - 1)
                    && (i + 2*(numRows -1) - 2*row) < s.length()) {
                    subResult.append(s.charAt(i + 2*(numRows -1) - 2*row));
                    
                }
            }
            result.append(subResult.toString());
        }
        return result.toString();
    }
    
    public static int reverse(int x) {
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
//        if (result < 0) {
//            return -1;
//        }
        return result * sign;
    }
    
    public static int myAtoi(String str) {
        // null string or empty
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        int sign = 1;
        int index = 0;
        // determine the sign
        if (str.charAt(index) == '+') {
            index++;
        } else if (str.charAt(index) == '-') {
            sign = -1;
            index++;
        }
        
        // make the number
        long num = 0;
        while (index < str.length()) {
            if (str.charAt(index) < '0' || str.charAt(index) > '9') {
                break;
            }
            num = num * 10 + (str.charAt(index) - '0');
            index++;
            System.out.println(num);
            if (num * sign > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (num * sign < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
//        if (num * sign > Integer.MAX_VALUE) {
//            return Integer.MAX_VALUE;
//        }
//        if (num * sign < Integer.MIN_VALUE) {
//            return Integer.MIN_VALUE;
//        }
        return (int) (num * sign);
    }
    
    
    public static void main(String[] args) {
//        System.out.println(reverse(1534236469));
        System.out.println(myAtoi("9223372036854775809"));
    }
}