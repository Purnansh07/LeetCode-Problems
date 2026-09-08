class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        // amount + 1 acts as infinity
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int sum = coin; sum <= amount; sum++) {
                dp[sum] = Math.min(
                    dp[sum],
                    dp[sum - coin] + 1
                );
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}