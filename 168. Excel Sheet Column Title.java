public class Solution {
    public String convertToTitle(int n) {
        if (n <= 26) {
            return (char)('A' + n - 1) + "";
        } else {
            return convertToTitle((n - 1) / 26) + convertToTitle((n - 1) % 26 + 1);
        }
    }
}