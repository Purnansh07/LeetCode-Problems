class Solution {

    public long findKthSmallest(int[] coins, int k) {

        int n = coins.length;

        long left = 1;

        // The kth amount cannot be larger than
        // k times the smallest coin.
        long minCoin = coins[0];

        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }

        long right = minCoin * (long) k;

        while (left < right) {

            long mid = left + (right - left) / 2;

            if (count(mid, coins) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private long count(long x, int[] coins) {

        int n = coins.length;
        long result = 0;

        // Enumerate every non-empty subset
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    lcm = lcm(
                        lcm,
                        coins[i]
                    );

                    // No multiple of this LCM
                    // can be <= x
                    if (lcm > x) {
                        break;
                    }
                }
            }

            if (lcm > x) {
                continue;
            }

            long occurrences = x / lcm;

            if (bits % 2 == 1) {
                result += occurrences;
            } else {
                result -= occurrences;
            }
        }

        return result;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    private long gcd(long a, long b) {

        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}