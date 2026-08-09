class Solution {
    public int minDistance(String word1, String word2) {
        // Use the shorter word for the DP array
        if (word1.length() < word2.length()) {
            return solve(word1, word2);
        }

        return solve(word2, word1);
    }

    private int solve(String shorter, String longer) {
        int m = shorter.length();
        int n = longer.length();

        int[] dp = new int[m + 1];

        // Convert empty prefix of longer string
        // to prefixes of shorter string
        for (int i = 0; i <= m; i++) {
            dp[i] = i;
        }

        for (int j = 1; j <= n; j++) {
            int diagonal = dp[0];
            dp[0] = j;

            for (int i = 1; i <= m; i++) {
                int previous = dp[i];

                if (shorter.charAt(i - 1) == longer.charAt(j - 1)) {
                    dp[i] = diagonal;
                } else {
                    dp[i] = 1 + Math.min(
                        diagonal,
                        Math.min(dp[i - 1], dp[i])
                    );
                }

                diagonal = previous;
            }
        }

        return dp[m];
    }
}