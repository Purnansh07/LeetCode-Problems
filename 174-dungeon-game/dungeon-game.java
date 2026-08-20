class Solution {
    public int calculateMinimumHP(int[][] dungeon) {

        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] dp = new int[m + 1][n + 1];

        // Initialize everything with a very large value
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // Boundary conditions
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;

        // Work backwards
        for (int i = m - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                int nextHealth = Math.min(
                    dp[i + 1][j],
                    dp[i][j + 1]
                );

                dp[i][j] = Math.max(
                    1,
                    nextHealth - dungeon[i][j]
                );
            }
        }

        return dp[0][0];
    }
}