class Solution {

    public int numIslands(char[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int count = 0;

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                if (grid[r][c] == '1') {

                    count++;

                    dfs(grid, r, c);
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int r, int c) {

        // Out of bounds
        if (r < 0 || r >= grid.length ||
            c < 0 || c >= grid[0].length) {
            return;
        }

        // Water or already visited
        if (grid[r][c] != '1') {
            return;
        }

        // Mark as visited
        grid[r][c] = '0';

        // Up
        dfs(grid, r - 1, c);

        // Down
        dfs(grid, r + 1, c);

        // Left
        dfs(grid, r, c - 1);

        // Right
        dfs(grid, r, c + 1);
    }
}