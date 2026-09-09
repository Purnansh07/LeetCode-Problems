class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;

        // Prefix sums
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Coordinate compression
        long[] values = new long[(n + 1) * 3];

        int idx = 0;
        for (long x : prefix) {
            values[idx++] = x;
            values[idx++] = x - lower;
            values[idx++] = x - upper;
        }

        Arrays.sort(values);

        int m = 0;
        for (long x : values) {
            if (m == 0 || values[m - 1] != x) {
                values[m++] = x;
            }
        }

        int[] bit = new int[m + 1];
        int ans = 0;

        for (long x : prefix) {

            // Required range:
            // x - upper <= previousPrefix <= x - lower

            int left = lowerBound(values, m, x - upper);
            int right = lowerBound(values, m, x - lower);

            // IMPORTANT:
            // left is a 0-based position.
            // Fenwick indices are 1-based.
            ans += query(bit, right + 1)
                 - query(bit, left);

            // Add current prefix sum
            int rank = lowerBound(values, m, x) + 1;
            update(bit, rank);
        }

        return ans;
    }

    private int lowerBound(long[] arr, int size, long target) {
        int l = 0, r = size;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private void update(int[] bit, int i) {
        while (i < bit.length) {
            bit[i]++;
            i += i & -i;
        }
    }

    private int query(int[] bit, int i) {
        int sum = 0;

        while (i > 0) {
            sum += bit[i];
            i -= i & -i;
        }

        return sum;
    }
}