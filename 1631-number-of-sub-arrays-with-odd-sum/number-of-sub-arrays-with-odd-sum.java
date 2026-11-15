
class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;

        long even = 1;
        long odd = 0;
        long result = 0;

        int parity = 0;

        for (int num : arr) {
            parity = (parity + num) % 2;

            if (parity == 1) {
                result = (result + even) % MOD;
                odd++;
            } else {
                result = (result + odd) % MOD;
                even++;
            }
        }

        return (int) result;
    }
}
