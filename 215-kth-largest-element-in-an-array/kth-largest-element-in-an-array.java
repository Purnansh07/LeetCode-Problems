class Solution {
    public int findKthLargest(int[] nums, int k) {

        int target = nums.length - k;

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int pivot = nums[left + (right - left) / 2];

            int low = left;
            int i = left;
            int high = right;

            // 3-way partition
            while (i <= high) {

                if (nums[i] < pivot) {
                    swap(nums, i, low);
                    i++;
                    low++;

                } else if (nums[i] > pivot) {
                    swap(nums, i, high);
                    high--;

                } else {
                    i++;
                }
            }

            // target is inside the == pivot region
            if (target >= low && target <= high) {
                return nums[target];
            }

            // Search right
            if (target > high) {
                left = high + 1;
            }

            // Search left
            else {
                right = low - 1;
            }
        }

        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}