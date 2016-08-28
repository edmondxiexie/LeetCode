public class Solution {
    public static String convert(String s, int numRows) {
        // 首行和尾行相邻两个元素之间的距离是2*(numRows - 1)
        // 首行和尾行之间的其他行除了像首位两行一样有间隔距离2*(numRows - 1)的元素，
        // 如，1，7和2，8，在它们之间还有一个元素，该元素到该行下一个元素的距离为2*i，
        // i为所在行数，所以到上一个元素的距离为2*(numRows -1) - 2*i。
        if (numRows <= 1) {
            return s;
        }
        
        StringBuffer result = new StringBuffer();
        
        for (int row = 0; row < numRows; row++) {
            StringBuffer subResult = new StringBuffer();
    
            for (int i = row; i < s.length(); i += 2*(numRows - 1)) {
                subResult.append(s.charAt(i)); 
                if ((row != 0 && row != numRows - 1)
                    && (i + 2*(numRows -1) - 2*row) < s.length()) {
                    subResult.append(s.charAt(i + 2*(numRows -1) - 2*row));
                    
                }
            }
            result.append(subResult.toString());
        }
        return result.toString();
    }
}