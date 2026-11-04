import java.util.*;

class Solution {

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] unique = new int[n];
        int m = 0;

        for (int num : sorted) {
            if (m == 0 || unique[m - 1] != num) {
                unique[m++] = num;
            }
        }

        int[] arr = new int[n];

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            map.put(unique[i], i);
        }

        for (int i = 0; i < n; i++) {
            arr[i] = map.get(nums[i]);
        }

        Fenwick count = new Fenwick(m);
        Fenwick sum = new Fenwick(m);

        long[] cost = new long[n - x + 1];

        // Calculate cost for every window of size x
        for (int i = 0; i < n; i++) {

            count.add(arr[i], 1);
            sum.add(arr[i], nums[i]);

            if (i >= x) {
                count.add(arr[i - x], -1);
                sum.add(arr[i - x], -nums[i - x]);
            }

            if (i >= x - 1) {

                int medianIndex = count.kth((x + 1) / 2);
                long median = unique[medianIndex];

                // Include median in left partition
                long leftCount = count.query(medianIndex + 1);
                long leftSum = sum.query(medianIndex + 1);

                long totalCount = count.query(m);
                long totalSum = sum.query(m);

                long rightCount = totalCount - leftCount;
                long rightSum = totalSum - leftSum;

                cost[i - x + 1] =
                    median * leftCount - leftSum
                    + rightSum - median * rightCount;
            }
        }

        // DP
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], INF);
        }

        Arrays.fill(dp[0], 0);

        for (int j = 1; j <= k; j++) {

            for (int i = x; i <= n; i++) {

                // Skip current element
                dp[j][i] = dp[j][i - 1];

                // Select window of length x
                if (dp[j - 1][i - x] != INF) {

                    dp[j][i] = Math.min(
                        dp[j][i],
                        dp[j - 1][i - x] + cost[i - x]
                    );
                }
            }
        }

        return dp[k][n];
    }

    class Fenwick {

        long[] bit;
        int n;

        Fenwick(int n) {
            this.n = n;
            bit = new long[n + 1];
        }

        void add(int index, long value) {

            for (index++; index <= n; index += index & -index) {
                bit[index] += value;
            }
        }

        long query(int index) {

            long result = 0;

            for (; index > 0; index -= index & -index) {
                result += bit[index];
            }

            return result;
        }

        int kth(long k) {

            int index = 0;

            for (int step = Integer.highestOneBit(n);
                 step > 0; step >>= 1) {

                int next = index + step;

                if (next <= n && bit[next] < k) {
                    index = next;
                    k -= bit[next];
                }
            }

            return index;
        }
    }
}