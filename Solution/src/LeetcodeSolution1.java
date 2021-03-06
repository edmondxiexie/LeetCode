import java.util.HashSet;
import java.util.Stack;

/**
 * @author Edmond
 *
 */

public class LeetcodeSolution1 {
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
    
    public static int reverse(int x) {
        if (x == 0) {
            return x;
        }
        int result = 0;
        int absX = Math.abs(x);
        int sign = x / absX;
        while (absX > 0) {
            int temp = result * 10 + absX % 10;
            if (temp / 10 != result) {
                // overflow
                return 0;
            }
            absX = absX / 10;
            result = temp;
        }
//        if (result < 0) {
//            return -1;
//        }
        return result * sign;
    }
    
    public static int myAtoi(String str) {
        // null string or empty
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        int sign = 1;
        int index = 0;
        // determine the sign
        if (str.charAt(index) == '+') {
            index++;
        } else if (str.charAt(index) == '-') {
            sign = -1;
            index++;
        }
        
        // make the number
        long num = 0;
        while (index < str.length()) {
            if (str.charAt(index) < '0' || str.charAt(index) > '9') {
                break;
            }
            num = num * 10 + (str.charAt(index) - '0');
            index++;
            System.out.println(num);
            if (num * sign > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (num * sign < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return (int) (num * sign);
    }
    
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int digits = (int) Math.log10(x) + 1;
        if (x % 10 != (int)(x / Math.pow(10, digits - 1))) {
            return false;
        }
        // remove the first and last digit 
        // avoid overflow
        x = (int)(x / 10); // 10002
//        x = (int) (x % Math.pow(10, digits - 1) / 10);
        int temp = x;
        int num = 0;
        while (temp >= 10) {
            num = num * 10 + temp % 10;
            temp = temp / 10;
        }
        x = (int) (x % Math.pow(10, digits - 2));
        System.out.println(x);
        System.out.println(num);
        return num == x;
    }
    
    public static boolean isValid(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        Stack<Character> myStack = new Stack<Character>();
        for (int index = 0; index < s.length(); index++) {
            if (s.charAt(index) == '(' || s.charAt(index) == '[' 
                    || s.charAt(index) == '{') {
                myStack.push(s.charAt(index));
            } else {
                // if stack is already empty
                if (myStack.isEmpty()) {
                    return false;
                }
                if (s.charAt(index) == ')') {
                    if (myStack.pop() != '(') {
                        return false;
                    }
                }
                else if (s.charAt(index) == ']') {
                    if (myStack.pop() != '[') {
                        return false;
                    }
                }
                else if (s.charAt(index) == '}') {
                    if (myStack.pop() != '{') {
                        return false;
                    }
                }
            }
           
        }
        if (myStack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    public static int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int max_area = 0;
        int cur_area = 0;
        while (left < right) {
            // everytime move the short one
            // the short one is crucial
            if (height[left] <= height[right]) {
                cur_area = (right - left) * height[left];
                if (cur_area > max_area) {
                    max_area = cur_area;
                }
                left++;
            } 
            else {
                cur_area = (right - left) * height[right];
                if (cur_area > max_area) {
                    max_area = cur_area;
                }
                right--;
            }
        }
        return max_area;
    }
    
//     Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
   
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                lastNode.next = l1;
                l1 = l1.next;
            } else {
                lastNode.next = l2;
                l2 = l2.next;
            }
            lastNode = lastNode.next;
        }
        
        if (l1 != null) {
            lastNode.next = l1;
        } else {
            lastNode.next = l2;
        }
        return dummy.next; 
    }
    
    public static String intToRoman(int num) {
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
    
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head; 
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode former = dummy;
        ListNode cur = head;
////        ListNode later = head.next;
//        ListNode temp;
//        dummy = former.next;
//        former.next = former.next.next;
//        dummy.next = former;
        
        while (cur.next != null) {
            former.next = former.next.next;
            cur.next = former.next.next;
            former.next = cur;
            cur = cur.next;
            former = former.next.next;
        }
        return dummy.next;
    }
    
    public static int strStr(String haystack, String needle) {
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (haystack.startsWith(needle, i)) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean isValidSudoku(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        HashSet<Character> sets = new HashSet<Character>();
        
        // check row
        for (int row = 0; row < rows; row++) {
            sets.clear();
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == '.') {
                    continue;
                }
                if (sets.contains(board[row][col])) {
                    return false;
                }
                if (board[row][col] >= '1' 
                    && board[row][col] <= '9') {
                    sets.add(board[row][col]);
                } else {
                    return false;
                }
            }
        }
        
        // check col
        for (int col = 0; col < cols; col++) {
            sets.clear();
            for (int row = 0; row < rows; row++) {
                if (board[row][col] == '.') {
                    continue;
                }
                if (sets.contains(board[row][col])) {
                    return false;
                }
                if (board[row][col] >= '1' 
                    && board[row][col] <= '9') {
                    sets.add(board[row][col]);
                } else {
                    return false;
                }
            }
        } 
        
        // check block
        for (int bigRow = 0; bigRow < rows/3; bigRow++) {
            for (int bigCol = 0; bigCol < cols/3; bigCol++) {
                sets.clear();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int row = bigRow * 3 + i;
                        int col = bigCol * 3 + j;
                        if (board[row][col] == '.') {
                            continue;
                        }
                        if (sets.contains(board[row][col])) {
                            return false;
                        }
                        if (board[row][col] >= '1' 
                            && board[row][col] <= '9') {
                            sets.add(board[row][col]);
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public static String convert(String num) {
        int slow = 0;
        int fast = 0;
        String result = "";
        while (fast < num.length()) {
            while (fast != num.length() 
                    && num.charAt(fast) == num.charAt(slow)) {
                fast++;
            }
            result += Integer.toString(fast - slow) + num.charAt(slow);
            slow = fast;
        }
        return result;
    }
    
    public static String countAndSay(int n) {
        String last = "1";
        while (n > 0) {
            String cur = convert(last);
            last = cur;
            n--;
        }
        return last;
    }
    
    public int lengthOfLastWord(String s) {
        if (s == null) return 0;
        String[] parts = s.split(" ");
        if (parts.length == 0) return 0;
        return parts[parts.length - 1].length();
    }
    
    // 66. Plus One
    public static int[] plusOne(int[] digits) {
        int bits = digits.length;
        int flag = 1;
        
        int i =  bits - 1;
        while (flag == 1 && i >= 0) {
            digits[i]++;
            if (digits[i] == 10) {
                digits[i] = 0;
            } else {
                flag = 0;
            }
            i--;
        }
        
        // in case we need one more bits
        if (flag == 1) {
            int[] newDigits = new int[(bits + 1)];
            newDigits[0] = 1;
            for (int index = 0; index < bits; index++) {
                newDigits[index + 1] = digits[index];
            }
            return newDigits;
        } else {            
            return digits;
        }  
    }
    
    // 67. Add Binary
    public static String addBinary(String a, String b) {
        
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
    
    
    public static void main(String[] args) {
        System.out.println(addBinary("111", "1"));
        int[] s = {9, 9, 9};
        plusOne(s);
//        System.out.println(countAndSay(4));
//        String S0 = "we promptly judged antique ivory buckles for the next prize";
//        String S1 = "we promptly judged antique ivory buckles for the prize";
//        String S2 = "qwertyuiopasdfghjklzxcvbnm";
//        String[] s = {S0, S1, S2};
//        System.out.println(isPangram(s));
//        int[] height = new int[]{1, 1, 3, 8, 6, 7, 3};
//        System.out.println(strStr("world", "d"));
//        System.out.println(intToRoman(78));
//        System.out.println(maxArea(height));
//        System.out.println(isValid("()]"));
//        System.out.println(reverse(1534236469));
//        System.out.println(myAtoi("9223372036854775809"));
//        System.out.println(isPalindrome(10001));
    }
}