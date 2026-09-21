class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            int mod = num % k;
            long[] next = new long[k];

            // Start a new subarray with only this element
            next[mod] = 1;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {
                int newRemainder = (int) ((long) r * mod % k);
                next[newRemainder] += dp[r];
            }

            // Add all subarrays ending at this position
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}