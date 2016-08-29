public class Solution {
    public boolean isValid(String s) {
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
}