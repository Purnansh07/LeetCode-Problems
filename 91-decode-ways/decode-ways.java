class Solution {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        int prev2 = 1; // dp[i - 2]
        int prev1 = 1; // dp[i - 1]

        for (int i = 1; i < s.length(); i++) {
            int current = 0;

            // Decode current digit individually
            if (s.charAt(i) != '0') {
                current += prev1;
            }

            // Decode current + previous digit together
            int twoDigit = (s.charAt(i - 1) - '0') * 10
                         + (s.charAt(i) - '0');

            if (twoDigit >= 10 && twoDigit <= 26) {
                current += prev2;
            }

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}