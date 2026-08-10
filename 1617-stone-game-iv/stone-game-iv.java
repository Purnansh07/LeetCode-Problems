class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];

        // dp[0] = false:
        // If there are no stones, the player whose turn it is loses.

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                int remaining = i - j * j;

                // If the opponent is in a losing state,
                // current player can force a win.
                if (!dp[remaining]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}