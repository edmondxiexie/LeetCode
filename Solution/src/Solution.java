public class Solution {
    public int firstUniqChar(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            boolean unique = true;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    unique = false;
                    break;
                }
            }
            if (unique == true) {
                return i;
            }
        }
        return -1;
    }
}