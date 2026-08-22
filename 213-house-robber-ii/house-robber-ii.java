class Solution {
    public int rob(int[] nums) {

        int n = nums.length;

        // Only one house
        if (n == 1) {
            return nums[0];
        }

        // Case 1: Exclude last house
        int case1 = robRange(nums, 0, n - 2);

        // Case 2: Exclude first house
        int case2 = robRange(nums, 1, n - 1);

        return Math.max(case1, case2);
    }

    private int robRange(int[] nums, int start, int end) {

        int prev2 = 0;
        int prev1 = 0;

        for (int i = start; i <= end; i++) {

            int current = Math.max(
                prev1,
                prev2 + nums[i]
            );

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}