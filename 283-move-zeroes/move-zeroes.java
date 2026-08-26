class Solution {
    public void moveZeroes(int[] nums) {

        int insert = 0;

        // Move all non-zero elements forward
        for (int num : nums) {
            if (num != 0) {
                nums[insert++] = num;
            }
        }

        // Fill remaining positions with zero
        while (insert < nums.length) {
            nums[insert++] = 0;
        }
    }
}