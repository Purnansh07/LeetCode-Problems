class Solution {
    private int[] rows = new int[9];
    private int[] cols = new int[9];
    private int[] boxes = new int[9];

    public void solveSudoku(char[][] board) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int mask = 1 << num;

                    rows[i] |= mask;
                    cols[j] |= mask;
                    boxes[(i / 3) * 3 + j / 3] |= mask;
                }
            }
        }

        solve(board, 0, 0);
    }

    private boolean solve(char[][] board, int row, int col) {

        for (int i = row; i < 9; i++, col = 0) {
            for (int j = col; j < 9; j++) {

                if (board[i][j] != '.') {
                    continue;
                }

                int box = (i / 3) * 3 + j / 3;

                for (int num = 0; num < 9; num++) {

                    int mask = 1 << num;

                    if ((rows[i] & mask) != 0 ||
                        (cols[j] & mask) != 0 ||
                        (boxes[box] & mask) != 0) {
                        continue;
                    }

                    board[i][j] = (char) ('1' + num);

                    rows[i] |= mask;
                    cols[j] |= mask;
                    boxes[box] |= mask;

                    if (solve(board, i, j + 1)) {
                        return true;
                    }

                    board[i][j] = '.';

                    rows[i] ^= mask;
                    cols[j] ^= mask;
                    boxes[box] ^= mask;
                }

                return false;
            }
        }

        return true;
    }
}