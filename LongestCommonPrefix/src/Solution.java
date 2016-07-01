
public class Solution {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        // give the first str in array strs to prefix
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
        
        
        
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < str[0].length(); i++) {
//            char currStr = str[0].charAt(i);
//            for (int j = 0; j < str.length; j++) {
//                if (i >= str[j].length() || str[j].charAt(i) != currStr) {
//                    return result.toString();
//                }
//            }
//            result.append(currStr);
//        }
//        return result.toString();
//    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] str = new String[] {"abcddd", "abceee"};
        String a = "abcdefg";
        String b = a.substring(0, a.length() - 1);
        System.out.println(b);
//        System.out.println(longestCommonPrefix(str));
    }

}
