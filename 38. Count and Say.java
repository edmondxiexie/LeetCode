public class Solution {
    public static String convert(String num) {
        int slow = 0;
        int fast = 0;
        String result = "";
        while (fast < num.length()) {
            while (fast != num.length() 
                    && num.charAt(fast) == num.charAt(slow)) {
                fast++;
            }
            result += Integer.toString(fast - slow) + num.charAt(slow);
            slow = fast;
        }
        return result;
    }
    
    public String countAndSay(int n) {
        String last = "1";
        while (n > 1) {
            String cur = convert(last);
            last = cur;
            n--;
        }
        return last;
    }
}