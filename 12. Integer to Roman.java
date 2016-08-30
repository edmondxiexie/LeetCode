public class Solution {
    public String intToRoman(int num) {
        // I = 1
        // V = 5
        // X = 10
        // L = 50
        // C = 100
        // D = 500
        // M = 1000
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", 
                            "XL", "X", "IX", "V", "IV", "I"};
        int[] Values = {1000, 900, 500, 400, 100, 90, 50,
                        40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        for (int i = 0; num > 0; i++) {
            while (num >= Values[i]) {
                result.append(symbols[i]);
                num -= Values[i];
            }
        }
        return result.toString();
    }
}