public class Solution {
    public int[] plusOne(int[] digits) {
        int bits = digits.length;
        int flag = 1;
        int i =  bits - 1;
        while (flag == 1 && i >= 0) {
            digits[i]++;
            if (digits[i] == 10) {
                digits[i] = 0;
            } else {
                flag = 0;
            }
            i--;
        }
        
        // in case we need one more bits
        if (flag == 1) {
            int[] newDigits = new int[(bits + 1)];
            newDigits[0] = 1;
            for (int index = 0; index < bits; index++) {
                newDigits[index + 1] = digits[index];
            }
            return newDigits;
        } else {            
            return digits;
        }          
    }
}