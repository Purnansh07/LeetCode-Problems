class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        int max = 0;
        long total = 0;
        long k = (long) k1 + k2;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            max = Math.max(max, diff[i]);
        }

        // All differences can be reduced to zero.
        if (total <= k) return 0;

        // Find the minimum feasible maximum difference.
        int left = 0, right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long required = 0;

            for (int d : diff) {
                if (d > mid) {
                    required += d - mid;
                }
            }

            if (required <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int level = left;

        // Reduce every difference to at most 'level'.
        for (int i = 0; i < n; i++) {
            k -= Math.max(0, diff[i] - level);
            diff[i] = Math.min(diff[i], level);
        }

        // Use remaining operations to reduce some values by one more.
        for (int i = 0; i < n && k > 0; i++) {
            if (diff[i] == level) {
                diff[i]--;
                k--;
            }
        }

        long ans = 0;

        for (int d : diff) {
            ans += (long) d * d;
        }

        return ans;
    }
}