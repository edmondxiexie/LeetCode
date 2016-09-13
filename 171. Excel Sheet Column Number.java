public class Solution {
    public int titleToNumber(String s) {
        int result = 0;
        int exp = 0;
        while (s.length() > 0) {
            char cur = s.charAt(s.length() - 1);
            result = result + (cur - 'A' + 1) * (int)Math.pow(26, exp);

            s = s.substring(0, s.length() - 1);
            exp++;
        }
        return result;
    }
}