class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] prefMax = new int[n + 1];
        int[] suffMin = new int[n + 1];
        int[] prefDrop = new int[n];

        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }

        suffMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
        }

        for (int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1]
                    + (nums[i - 1] > nums[i] ? 1 : 0);
        }

        int ans = 0;

        // Enumerate divisors in O(sqrt(n)).
        for (int d = 1; d <= n / d; d++) {
            if (n % d != 0) continue;

            if (isSortable(nums, prefMax, suffMin, prefDrop, d)) {
                ans += d;
            }

            int other = n / d;
            if (other != d &&
                isSortable(nums, prefMax, suffMin, prefDrop, other)) {
                ans += other;
            }
        }

        return ans;
    }

    private boolean isSortable(int[] nums, int[] prefMax,
                               int[] suffMin, int[] prefDrop, int k) {
        int n = nums.length;

        for (int start = 0; start < n; start += k) {
            int end = start + k - 1;

            // Descents inside the block.
            int drops = prefDrop[end] - prefDrop[start];

            // Include the circular boundary.
            if (nums[end] > nums[start]) {
                drops++;
            }

            if (drops > 1) return false;

            // Previous blocks must fit before the remaining elements.
            if (prefMax[start] > suffMin[start]) return false;
        }

        return true;
    }
}