public class Solution {
    public int maxArea(int[] height) {
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
}