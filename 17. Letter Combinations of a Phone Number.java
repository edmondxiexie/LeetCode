public class Solution {
    public List<String> letterCombinations(String digits) {
        Map<Character, char[]> digitsMap = new HashMap<Character, char[]>();
        digitsMap.put('2', new char[] {'a', 'b', 'c'});
        digitsMap.put('3', new char[] {'d', 'e', 'f'});
        digitsMap.put('4', new char[] {'g', 'h', 'i'});
        digitsMap.put('5', new char[] {'j', 'k', 'l'});
        digitsMap.put('6', new char[] {'m', 'n', 'o'});
        digitsMap.put('7', new char[] {'p', 'q', 'r', 's'});
        digitsMap.put('8', new char[] {'t', 'u', 'v'});
        digitsMap.put('9', new char[] {'w', 'x', 'y', 'z'});

        char[] digitsArray = digits.toCharArray();    
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < digitsArray.length; i++) {
            List<String> temp = new ArrayList<String>();
            char[] letterArray = digitsMap.get(digitsArray[i]);
            if (result.size() == 0) {
                for (char letter : letterArray) {
                    temp.add("" + letter);
                }
            }
            else {
                for (String str : result) {
                    for (char letter : letterArray) {
                        temp.add(str + letter);
                    }
                }
            }
            result = temp;
        }
        return result;

    }
}