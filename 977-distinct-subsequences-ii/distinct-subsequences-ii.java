class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;

        long[] end = new long[26];
        long total = 0;

        for (char c : s.toCharArray()) {
            int idx = c - 'a';

            // New distinct subsequences ending with c
            long add = (total + 1) % MOD;

            // Replace old subsequences ending with c
            total = (total + add - end[idx] + MOD) % MOD;

            end[idx] = add;
        }

        return (int) total;
    }
}