
import java.util.*;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dirs = {-1, 0, 1, 0, -1};

        Deque<Integer> stack = new ArrayDeque<>();
        int count = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] <= 0) {
                    continue;
                }

                long sum = 0;
                grid[r][c] = -grid[r][c];
                stack.push(r * n + c);

                while (!stack.isEmpty()) {
                    int pos = stack.pop();
                    int x = pos / n;
                    int y = pos % n;

                    sum -= grid[x][y];

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dirs[d];
                        int ny = y + dirs[d + 1];

                        if (nx >= 0 && nx < m &&
                            ny >= 0 && ny < n &&
                            grid[nx][ny] > 0) {

                            grid[nx][ny] = -grid[nx][ny];
                            stack.push(nx * n + ny);
                        }
                    }
                }

                if (sum % k == 0) {
                    count++;
                }
            }
        }

        return count;
    }
}
