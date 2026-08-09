class Solution {
    private int[][] memo;
    private int[] suffix;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        memo = new int[n][n + 1];
        suffix = new int[n + 1];

        // Suffix sums
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return dfs(0, 1);
    }

    private int dfs(int i, int m) {
        if (i >= n) {
            return 0;
        }

        // Can take all remaining piles
        if (2 * m >= n - i) {
            return suffix[i];
        }

        if (memo[i][m] != 0) {
            return memo[i][m];
        }

        int best = 0;

        for (int x = 1; x <= 2 * m; x++) {
            int nextM = Math.max(m, x);

            int current = suffix[i] - dfs(i + x, nextM);

            best = Math.max(best, current);
        }

        return memo[i][m] = best;
    }
}