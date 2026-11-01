
class Solution {
    public int uniquePaths(int[][] grid) {
        int MOD = 1_000_000_007;
        int m = grid.length;
        int n = grid[0].length;

        // Number of ways entering each column from above.
        int[] down = new int[n];
        down[0] = 1;

        for (int i = 0; i < m; i++) {
            int right = 0;

            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    // Mirror: swap the incoming directions.
                    int temp = right;
                    right = down[j];
                    down[j] = temp;

                } else {
                    // Empty cell: paths can move right or down.
                    int total = (right + down[j]) % MOD;

                    right = total;
                    down[j] = total;
                }
            }
        }

        // Number of ways reaching bottom-right.
        return down[n - 1];
    }
}
