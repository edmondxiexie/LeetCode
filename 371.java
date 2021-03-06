// 371. Sum of Two Integers
// Calculate the sum of two integers a and b, but you are not allowed to use 
// the operator + and -.

public class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = a & b;
            a = a ^ b; // add
            b = carry << 1; // move carry to next bit 
        }
        return a;
    }
}