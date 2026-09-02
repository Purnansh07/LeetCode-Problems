class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        // First pass: determine next state
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                int live = 0;

                // Count ORIGINAL live neighbors
                for (int d = 0; d < 8; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr >= 0 && nr < m &&
                        nc >= 0 && nc < n &&
                        (board[nr][nc] == 1 || board[nr][nc] == 2)) {
                        live++;
                    }
                }

                // Alive -> Dead
                if (board[r][c] == 1 && (live < 2 || live > 3)) {
                    board[r][c] = 2;
                }

                // Dead -> Alive
                else if (board[r][c] == 0 && live == 3) {
                    board[r][c] = -1;
                }
            }
        }

        // Second pass: finalize states
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == 2) {
                    board[r][c] = 0;
                } else if (board[r][c] == -1) {
                    board[r][c] = 1;
                }
            }
        }
    }
}