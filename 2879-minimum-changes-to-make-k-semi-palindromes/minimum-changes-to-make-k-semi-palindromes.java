
import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1_000_000_000;

        int[][] cost = new int[n][n];

        // Precompute minimum changes for every substring.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int best = INF;

                // Find all proper divisors of len.
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check each sequence formed using modulo d.
                    for (int r = 0; r < d; r++) {
                        int left = i + r;
                        int right = i + r
                                + ((len - 1 - r) / d) * d;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[i][j] = best;
            }
        }

        // DP: dp[g][i] = minimum cost to split first i chars
        // into exactly g semi-palindromes.
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int g = 1; g <= k; g++) {
            for (int i = 2 * g; i <= n; i++) {

                // Last substring is s[j ... i-1].
                for (int j = 2 * (g - 1); j <= i - 2; j++) {
                    if (dp[g - 1][j] == INF) {
                        continue;
                    }

                    dp[g][i] = Math.min(
                        dp[g][i],
                        dp[g - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
