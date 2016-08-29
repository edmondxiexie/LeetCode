public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        // count how many digits in this number
        int digits = (int) Math.log10(x) + 1;
        // if the last and frst digits are different, return false
        if (x % 10 != (int)(x / Math.pow(10, digits - 1))) {
            return false;
        }
        // remove the last digit 
        // avoid overflow
        x = (int)(x / 10); 
        int temp = x;
        int num = 0;
        // num is the reversed middle digits of the x
        while (temp >= 10) {
            num = num * 10 + temp % 10;
            temp = temp / 10;
        }
        // remove the first digit
        x = (int) (x % Math.pow(10, digits - 2));
        return num == x;
    }
}