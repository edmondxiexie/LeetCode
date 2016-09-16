import java.lang.reflect.Array;
import java.util.*;

public class LeetcodeSolution3 {
    
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

    // 190. Reverse Bits
    public int reverseBits(int n) {
        int mask = 1;
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int lastBit = n & mask;
            result = result << 1 | lastBit;
            n = n >> 1;
        }
        return result;
    }

    // 34. Search for a Range
    public static int[] binarySearchRange(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int[] result = new int[3];

        while(high >= low) {
            int middle = (low + high) / 2;
            if(nums[middle] == target) {
                result[0] = low;
                result[1] = middle;
                result[2] = high;
                return result;
            }
            if(nums[middle] < target) {
                low = middle + 1;
            }
            if(nums[middle] > target) {
                high = middle - 1;
            }
        }
        return null;
    }

    // [1, 2, 3, 4, 4, 4, 4]
    // OR [4, 4, 4, 4, 5, 6, 7, 8]
    public static int BinaryFindEnds(int[] nums, int target, int low, int high, boolean first) {
        if (first) {
            int min = high;
            while (high >= low) {
                int middle = (low + high) / 2;
                if (nums[middle] == target) {
                    min = middle;
                    high = middle - 1;
                } else if (nums[middle] < target) {
                    low = middle + 1;
                }
            }
            return min;
        } else {
            int max = low;
            while (high >= low) {
                int middle = (low + high) / 2;
                if (nums[middle] == target) {
                    max = middle;
                    low = middle + 1;
                } else if (nums[middle] > target) {
                    high = middle - 1;
                }
            }
            return max;
        }
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] pos = new int[3];
        pos = binarySearchRange(nums, target);
        int[] result = new int[2];
        if (pos != null) {
            result[0] = BinaryFindEnds(nums, target, pos[0], pos[1], true);
            result[1] = BinaryFindEnds(nums, target, pos[1], pos[2], false);
        } else {
            result[0] = -1;
            result[1] = -1;
        }
        return result;
    }

    // 35. Search Insert Position
    public static int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int middle = (low + high) / 2;


        while(high >= low) {
            middle = (low + high) / 2;
            if(nums[middle] == target) {
                return middle;
            }
            if(nums[middle] < target) {
                low = middle + 1;
            }
            if(nums[middle] > target) {
                high = middle - 1;
            }
        }
        if (nums[middle] < target) {
            return middle + 1;
        }
        return middle;
    }

    // 39. Combination Sum
    private void solve(List<List<Integer>> res, int currentIndex, int count, List<Integer> tmp,
                       int[] candidates, int target){
        if (count >= target) {
            if(count == target)
                res.add(new LinkedList<>(tmp));
            return;
        }

        for (int i = currentIndex; i < candidates.length; i++){
            if (count + candidates[i] > target) {
                break;
            }
            tmp.add(candidates[i]);
            solve(res, i, count + candidates[i], tmp, candidates, target);
            tmp.remove(tmp.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        List<Integer> tmp = new LinkedList<>();
        Arrays.sort(candidates);

        solve(res, 0, 0, tmp, candidates, target);
        return res;
    }

    // 40. Combination Sum II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        List<Integer> tmp = new LinkedList<>();

        Set<Integer> set = new HashSet<Integer>();
        for (int num : candidates) {
            set.add(num);
        }
        candidates = new int[set.size()];
        int index = 0;
        for (int num : set) {
            candidates[index] = num;
            index++;
        }
        Arrays.sort(candidates);

        solve(res, 0, 0, tmp, candidates, target);
        return res;
    }

    // 43. Multiply Strings
    public static String multiply(String num1, String num2) {
        // http://www.cnblogs.com/springfor/p/3889706.html
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        int[] ints = new int[num1.length() + num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            int a = num1.charAt(i) - '0';
            for (int j = 0; j < num2.length(); j++) {
                int b = num2.charAt(j) - '0';
                ints[i + j] += a * b;
            }
        }

        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int index = 0; index < ints.length; index++) {
            int remainder = ints[index] % 10;
            carry = ints[index] / 10;

            result.append(remainder);
            if (index + 1 < ints.length) {
                ints[index + 1] += carry;
            }
        }

        // reverse
        result = result.reverse();

        // delete heading '0'
        while (result.length() > 0 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }
        return result.toString();
    }

    // 46. Permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        return result;


    }

    public static void divide(int a, int b) {
        try {
            int c = a / b;
        } catch (Exception e) {
            System.out.print("Exception ");
        } finally {
            System.out.println("Finally");
        }
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
//        System.out.println(trailingZeroes(25));
//        int[] nums = {1, 3, 4};
//        System.out.println(Arrays.toString(searchRange(nums, 9)));
//        System.out.println(searchInsert(nums, 5));
//        searchRange(nums, 9);
//        divide(4, 0);
//        String s1=new String("Hello");
//        String s2=new String("there");
//        String s3=new String();
//        s3 = s1 + s2;
        System.out.println(multiply("52", "60"));

    }

}
