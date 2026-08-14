class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        if (n > m) {
            return 0;
        }

        long[] dp = new long[n + 1];

        // Empty t can always be formed in exactly one way
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {
            // Go backwards to avoid overwriting dp[j - 1]
            for (int j = Math.min(i, n); j >= 1; j--) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return (int) dp[n];
    }
}