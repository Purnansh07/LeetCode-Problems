class Solution {
    public int findPeakElement(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                // Increasing slope
                left = mid + 1;
            } else {
                // Decreasing slope
                right = mid;
            }
        }

        return left;
    }
}