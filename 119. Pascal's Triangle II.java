public class Solution {
    
    public List<Integer> getRow(int rowIndex) {
        // 递推公式可以写作a(m,n)=a(m-1,n-1)+a(m-1,n)
        // r[i] = r[i−1]∗(rowIndex−i+1)/i
        List<Integer> result = new ArrayList<Integer>();
        if (rowIndex == 0) {
            result.add(1);
            return result;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int cols = rowIndex + 1;
        
        for (int col = 0; col < (cols - 1) / 2 + 1; col++) {
            if (col == 0) {
                result.add(1);
                stack.push(1);
            } else {
                int lasVal = result.get(result.size() - 1);
                int val = (int)(lasVal * (long)(rowIndex - col + 1) / col);
                result.add(val);
                stack.push(val);
            }
        }
        if (rowIndex % 2 == 0) {
            stack.pop();
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}