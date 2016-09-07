public class Solution {
    
    public static void tryParenthesis(List<String> result, String str, int left, int right) {
        if (left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            result.add(new String(str));
            return;
        }
        if (left > 0) {
            tryParenthesis(result, str + "(", left - 1, right);
        }
        if (right > 0) {
            tryParenthesis(result, str + ")", left, right - 1);

        }        
    }
    
    public List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<String>();
        String str = new String();
        if (n < 0) return result;
        tryParenthesis(result, str, n, n);
        return result;    
    }
}