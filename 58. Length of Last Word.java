public class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null) return 0;
        String[] parts = s.split(" ");
        if (parts.length == 0) return 0;
        return parts[parts.length - 1].length();
    }
}