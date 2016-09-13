import java.util.*;

public class Solution3 {
    
    // 119. Pascal's Triangle II
    public int getVal (int row, int col) {
        if (row == 0 && col == 0) {
            return 1;
        }
        if (row < 0 || col < 0) {
            return 0;
        }
        return getVal(row - 1, col - 1) + getVal(row - 1, col);
    }
    
    public static List<Integer> getRow(int rowIndex) {
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
                System.out.println(val);
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
    
    // 125. Valid Palindrome
    public boolean isValid(char letter) {
        if ((letter >= 'a' && letter <= 'z') 
                || letter >= 'A' && letter <= 'Z'
                || letter >= '0' && letter <= '9') {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while (right > left) {
            while (left <= right && !isValid(s.charAt(left))) {
                left++;
            }
            while (right >= left &&!isValid(s.charAt(right))) {
                right--;
            }
            if (left >= right) {
                break;
            }
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    // 136. Single Number
    public int singleNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            
            // 只保留出现奇数次的位数
            sum = sum ^ num;
        }
        return sum;
    }
    
    // 137. Single Number II
    public int singleNumber2(int[] nums) {
        // 记录出现过一次的数位
        int ones = 0;
        
        // 记录出现过两次的数位
        int twos = 0;
        
        // 记录出现过三次的数位，ones & twos
        int threes = 0;
        
        for (int num : nums) {
            
            // ones 和 num 里都为1的数位说明出现过两次，放到twos里
            twos = twos | (ones & num);
            
            // ones 为0，num为1的数位说明出现过一次
            ones = ones ^ num;
            threes = ones & twos;
            
            ones = ones & ~threes;
            twos = twos & ~threes;
        }
        return ones;
    }
    
    // 155. Min Stack
    public class MinStack {
        List<Integer> mins;
        List<Integer> stack;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new ArrayList<Integer>();
            mins = new ArrayList<Integer>();
        }
        
        public void push(int x) {
            if (mins.size() == 0) {
                mins.add(x);
            }
            else if (x <= mins.get(mins.size() - 1)) {
                mins.add(x);
            }
            stack.add(x);
        }

        public void pop() {
            if (stack.get(stack.size() - 1).equals(mins.get(mins.size() - 1))) {
                mins.remove(mins.size() - 1);
            }
            
            stack.remove(stack.size() - 1);
        }

        public int top() {
            return stack.get(stack.size() - 1);
        }
        
        public int getMin() {
            return mins.get(mins.size() - 1);
        }
    }

    // 165. Compare Version Numbers
    public static int compareVersion(String version1, String version2) {
        version1 = version1.replace(".", " ");
        version2 = version2.replace(".", " ");
        String[] versions1 = version1.split(" ");
        String[] versions2 = version2.split(" ");
        int length1 = versions1.length;
        int length2 = versions2.length;

        int max = Math.max(length1, length2);
        int index = 0;

        while (index < max) {
            int beta1 = 0;
            int beta2 = 0;

            if (index < length1) {
                beta1 = Integer.valueOf(versions1[index]);
            }
            if (index < length2) {
                beta2 = Integer.valueOf(versions2[index]);
            }

            if (beta1 > beta2) {
                return 1;
            } else if (beta1 < beta2) {
                return -1;
            } else {
                index++;
            }
        }
        return 0;
    }

    // 168. Excel Sheet Column Title
    public static String convertToTitle(int n) {
        if (n <= 26) {
            return (char)('A' + n - 1) + "";
        } else {
            return convertToTitle((n - 1) / 26) + convertToTitle((n - 1) % 26 + 1);
        }
    }

    // 169. Majority Element
    public int majorityElement(int[] nums) {
        Map<Integer, Integer>  map = new HashMap<Integer, Integer>();
        int length = nums.length;
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
            if (map.get(num) > length / 2) {
                return num;
            }
        }
        return -1;
    }

    // 171. Excel Sheet Column Number
    public int titleToNumber(String s) {
        int result = 0;
        int exp = 0;
        while (s.length() > 0) {
            char cur = s.charAt(s.length() - 1);
            result = result + (cur - 'A' + 1) * (int)Math.pow(26, exp);

            s = s.substring(0, s.length() - 1);
            exp++;
        }
        return result;
    }

    // 172. Factorial Trailing Zeroes
    public static int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count = count + n / 5;
            n = n / 5;
        }
        return count;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        System.out.println(155117520);
//        System.out.println(Integer.MAX_VALUE);
//        getRow(30);
//        System.out.println("2".compareTo("15"));
//        System.out.println(compareVersion("1", "1.15"));
//        System.out.println(compareVersion("1.0.1", "1"));
//        System.out.println(compareVersion("1.2", "1.15"));
//        System.out.println(compareVersion("1.0.1", "1.0.1"));
//
//        System.out.println("1.5".contains("."));
//
//        System.out.println(convertToTitle(52));
        System.out.println(trailingZeroes(25));

    }

}
