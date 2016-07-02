import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static int romanToInt(String s) {
        // Build the map
        Map<String, Integer> valMap = new HashMap<>();
        valMap.put("I", 1);
        valMap.put("V", 5);
        valMap.put("X", 10);
        valMap.put("L", 50);
        valMap.put("C", 100);
        valMap.put("D", 500);
        valMap.put("M", 1000);
        
        int total = 0;
        while (s.length() > 0) {
            if (s.length() == 1) {
                total += valMap.get(s.substring(0, 1));
                break;
            }
            int firNum = valMap.get(s.substring(0, 1));
            int secNum = valMap.get(s.substring(1, 2));
            // 1. only addition
            if (firNum >= secNum) {
                total += firNum;
                s = s.substring(1);
            } else {
                total += secNum - firNum;
                s = s.substring(2);
            }
        }
        return total;
        
    }
    public static void main(String[] args) {
        System.out.println(romanToInt("XI"));
        System.out.println(romanToInt("X"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("XLI"));
        System.out.println(romanToInt("DCXXI"));
    }

}
