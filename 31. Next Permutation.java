public class Solution {
    public void nextPermutation(int[] nums) {
        // array print use Arrays.toString(nums)
        // array print use Arrays.toString(nums)
        // {6, 5, 2, 4, 3, 1} 从后向前找，找到第一个不是降序的元素，这里是2，pivot
        // 从末尾到pivot之间，从后向前找第一个比2大的数，这里是3
        // 交换2和3，pivot之后顺序排列
        if (nums.length <= 1) 
            return;
        boolean changed = false;
        int pivot = nums.length - 2;
        while (pivot >= 0) {
            if (nums[pivot] < nums[pivot + 1]) {
                changed = true;
                break;
            }
            pivot--;
        }
        if (changed == false) {
            Arrays.sort(nums);
            return;
        } 
        
        for (int i = nums.length - 1; i > pivot; i--) {
            if (nums[i] > nums[pivot]) {
                int temp = nums[pivot];
                nums[pivot] = nums[i];
                nums[i] = temp;
                break;
            }
        }
        Arrays.sort(nums, pivot + 1, nums.length);
    }
}