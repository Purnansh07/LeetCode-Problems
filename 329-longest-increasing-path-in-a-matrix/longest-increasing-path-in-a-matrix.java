class Solution {
    private int[][] matrix;
    private int[][] dp;
    private int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        m = matrix.length;
        n = matrix[0].length;

        dp = new int[m][n];

        int ans = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                ans = Math.max(ans, dfs(r, c));
            }
        }

        return ans;
    }

    private int dfs(int r, int c) {
        // Already calculated
        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        int best = 1;

        // Up
        if (r > 0 && matrix[r - 1][c] > matrix[r][c]) {
            best = Math.max(best, 1 + dfs(r - 1, c));
        }

        // Down
        if (r + 1 < m && matrix[r + 1][c] > matrix[r][c]) {
            best = Math.max(best, 1 + dfs(r + 1, c));
        }

        // Left
        if (c > 0 && matrix[r][c - 1] > matrix[r][c]) {
            best = Math.max(best, 1 + dfs(r, c - 1));
        }

        // Right
        if (c + 1 < n && matrix[r][c + 1] > matrix[r][c]) {
            best = Math.max(best, 1 + dfs(r, c + 1));
        }

        return dp[r][c] = best;
    }
}