public class Solution {
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
}