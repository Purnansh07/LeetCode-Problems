class Solution {
    public int minCut(String s) {
        int n = s.length();

        boolean[][] isPalindrome = new boolean[n][n];

        // Build palindrome table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {

                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 2 || isPalindrome[i + 1][j - 1])) {

                    isPalindrome[i][j] = true;
                }
            }
        }

        // dp[i] = minimum cuts for s[0...i]
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {

            // Entire prefix is a palindrome
            if (isPalindrome[0][i]) {
                dp[i] = 0;
                continue;
            }

            dp[i] = i; // Maximum possible cuts

            for (int j = 1; j <= i; j++) {

                if (isPalindrome[j][i]) {
                    dp[i] = Math.min(
                        dp[i],
                        dp[j - 1] + 1
                    );
                }
            }
        }

        return dp[n - 1];
    }
}