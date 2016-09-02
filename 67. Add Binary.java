public class Solution {
    public String addBinary(String a, String b) {
        char[] longer;
        char[] shorter;
        if (a.length() > b.length()) {
            longer = a.toCharArray();
            shorter = b.toCharArray();
        } else {
            shorter = a.toCharArray();
            longer = b.toCharArray();            
        }
        int carry = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < shorter.length; i++) {
            if (carry == 0) {
                
                // 1 + 1
                if (longer[longer.length - i - 1] == '1'
                        && shorter[shorter.length - i - 1] == '1') {
                    carry = 1;
                    result.append("0");
                } 
                
                // 0 + 0
                else if (longer[longer.length - i - 1] == '0'
                        && shorter[shorter.length - i - 1] == '0') {
                    result.append("0");
                }
                
                // 1 + 0 or 0 + 1
                else {
                    result.append("1");
                }
            } 
            
            // carry == 1
            else {

                // 1 + 1
                if (longer[longer.length - i - 1] == '1'
                        && shorter[shorter.length - i - 1] == '1') {
                    carry = 1;
                    result.append("1");
                } 
                
                // 0 + 0
                else if (longer[longer.length - i - 1] == '0'
                        && shorter[shorter.length - i - 1] == '0') {
                    result.append("1");
                    carry = 0;
                }
                
                // 1 + 0 or 0 + 1
                else {
                    result.append("0");
                    carry = 1;
                }
            }
        }
        
        // longer
        for (int i = 0; i < (longer.length - shorter.length); i++) {
            
            // carry == 0
            if (carry == 0) {
                
                // 1
                if (longer[longer.length - shorter.length - i - 1] == '1') {
                    result.append("1");
                } 
                
                // 0
                else if (longer[longer.length - shorter.length - i - 1] == '0') {
                    result.append("0");
                }
            } 
            
            // carry == 1
            else {
                // 1
                if (longer[longer.length - shorter.length - i - 1] == '1') {
                    carry = 1;
                    result.append("0");
                } 
                
                // 0
                else if (longer[longer.length - shorter.length - i - 1] == '0') {
                    result.append("1");
                    carry = 0;
                }
            }
        }
        
        // one more bit 
        if (carry == 1) {
            result.append("1");
        }
        return result.reverse().toString();

    }
}