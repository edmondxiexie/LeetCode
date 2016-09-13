public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int row = 0; row < numRows; row++) {
            List<Integer> level = new ArrayList<Integer>();
            if (row == 0) {
                level.add(1);
            } else {
                level.add(1);
                for (int i = 0; i < row - 1; i++) {
                    List<Integer> lastLevel = result.get(row - 1);
                    int value = lastLevel.get(i) + lastLevel.get(i + 1);
                    level.add(value);
                }
                level.add(1);
            }
            result.add(level);
        }
        return result;
    }
}