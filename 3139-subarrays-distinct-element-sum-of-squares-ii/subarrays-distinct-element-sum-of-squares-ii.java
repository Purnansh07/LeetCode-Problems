import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;

    private int n;
    private long[] sum, sumSq, lazy;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long ans = 0;

        for (int r = 0; r < n; r++) {
            int l = last[nums[r]] + 1;

            // Increase distinct counts for subarrays ending at r.
            update(1, 0, n - 1, l, r);

            // Sum of squared distinct counts for subarrays ending at r.
            ans = (ans + sumSq[1]) % MOD;

            last[nums[r]] = r;
        }

        return (int) ans;
    }

    private void update(int node, int start, int end, int ql, int qr) {
        if (ql > end || qr < start) return;

        if (ql <= start && end <= qr) {
            apply(node, start, end, 1);
            return;
        }

        pushDown(node, start, end);

        int mid = start + (end - start) / 2;
        update(node * 2, start, mid, ql, qr);
        update(node * 2 + 1, mid + 1, end, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSq[node] = (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
    }

    private void apply(int node, int start, int end, long delta) {
        long len = end - start + 1L;

        // (x + delta)^2 = x^2 + 2*x*delta + delta^2
        sumSq[node] = (
            sumSq[node]
            + 2L * delta % MOD * sum[node] % MOD
            + delta * delta % MOD * len % MOD
        ) % MOD;

        sum[node] = (sum[node] + delta * len) % MOD;
        lazy[node] = (lazy[node] + delta) % MOD;
    }

    private void pushDown(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) return;

        int mid = start + (end - start) / 2;
        apply(node * 2, start, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, end, lazy[node]);

        lazy[node] = 0;
    }
}