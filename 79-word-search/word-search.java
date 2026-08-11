class Solution {
    private int rows;
    private int cols;
    private String word;

    public boolean exist(char[][] board, String word) {
        this.rows = board.length;
        this.cols = board[0].length;
        this.word = word;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == word.charAt(0) &&
                    dfs(board, r, c, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int r, int c, int index) {
        if (index == word.length()) {
            return true;
        }

        // Boundary and character check
        if (r < 0 || r >= rows ||
            c < 0 || c >= cols ||
            board[r][c] != word.charAt(index)) {
            return false;
        }

        // Mark as visited
        char original = board[r][c];
        board[r][c] = '#';

        boolean found =
                dfs(board, r + 1, c, index + 1) ||
                dfs(board, r - 1, c, index + 1) ||
                dfs(board, r, c + 1, index + 1) ||
                dfs(board, r, c - 1, index + 1);

        // Backtrack
        board[r][c] = original;

        return found;
    }
}