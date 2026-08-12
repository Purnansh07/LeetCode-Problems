class Solution {
    private String s1;
    private String s2;
    private byte[][][] memo;

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        if (s1.equals(s2)) {
            return true;
        }

        this.s1 = s1;
        this.s2 = s2;

        int n = s1.length();

        // 0 = unknown, 1 = true, -1 = false
        memo = new byte[n][n][n + 1];

        return dfs(0, 0, n);
    }

    private boolean dfs(int i, int j, int len) {
        if (memo[i][j][len] != 0) {
            return memo[i][j][len] == 1;
        }

        // Same substring
        if (isSame(i, j, len)) {
            memo[i][j][len] = 1;
            return true;
        }

        // Character frequency pruning
        if (!sameCharacters(i, j, len)) {
            memo[i][j][len] = -1;
            return false;
        }

        // Try every possible split
        for (int k = 1; k < len; k++) {

            // No swap
            if (dfs(i, j, k) &&
                dfs(i + k, j + k, len - k)) {

                memo[i][j][len] = 1;
                return true;
            }

            // Swap
            if (dfs(i, j + len - k, k) &&
                dfs(i + k, j, len - k)) {

                memo[i][j][len] = 1;
                return true;
            }
        }

        memo[i][j][len] = -1;
        return false;
    }

    private boolean isSame(int i, int j, int len) {
        for (int k = 0; k < len; k++) {
            if (s1.charAt(i + k) != s2.charAt(j + k)) {
                return false;
            }
        }

        return true;
    }

    private boolean sameCharacters(int i, int j, int len) {
        int[] count = new int[26];

        for (int k = 0; k < len; k++) {
            count[s1.charAt(i + k) - 'a']++;
            count[s2.charAt(j + k) - 'a']--;
        }

        for (int value : count) {
            if (value != 0) {
                return false;
            }
        }

        return true;
    }
}