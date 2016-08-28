public class Solution {
    private String longestPalFromCenter(String s, int begin, int end) {
        while (begin >= 0 && end < s.length() 
            && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        String longestSub = "";
        for (int i = 0; i < s.length(); i++) {
            String temp_sub_odd = longestPalFromCenter(s, i, i);
            String temp_sub_even = longestPalFromCenter(s, i, i + 1);
            if (temp_sub_odd.length() > longestSub.length()) {
                longestSub = temp_sub_odd;
            }
            if (temp_sub_even.length() > longestSub.length()) {
                longestSub = temp_sub_even;
            }
        }
        return longestSub;
    }
}