public class Solution {
    public boolean isValid(char letter) {
        if ((letter >= 'a' && letter <= 'z') 
                || letter >= 'A' && letter <= 'Z'
                || letter >= '0' && letter <= '9') {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while (right > left) {
            while (left <= right && !isValid(s.charAt(left))) {
                left++;
            }
            while (right >= left &&!isValid(s.charAt(right))) {
                right--;
            }
            if (left >= right) {
                break;
            }
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}