class Solution {
    public int maximalSquare(char[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] dp = new int[cols + 1];

        int maxSide = 0;
        int prevDiagonal = 0;

        for (int i = 1; i <= rows; i++) {

            prevDiagonal = 0;

            for (int j = 1; j <= cols; j++) {

                int temp = dp[j];

                if (matrix[i - 1][j - 1] == '1') {

                    dp[j] = 1 + Math.min(
                        Math.min(dp[j], dp[j - 1]),
                        prevDiagonal
                    );

                    maxSide = Math.max(maxSide, dp[j]);

                } else {
                    dp[j] = 0;
                }

                prevDiagonal = temp;
            }
        }

        return maxSide * maxSide;
    }
}