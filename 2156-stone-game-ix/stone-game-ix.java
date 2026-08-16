class Solution {
    public boolean stoneGameIX(int[] stones) {
        int cnt0 = 0;
        int cnt1 = 0;
        int cnt2 = 0;

        for (int stone : stones) {
            int remainder = stone % 3;

            if (remainder == 0) {
                cnt0++;
            } else if (remainder == 1) {
                cnt1++;
            } else {
                cnt2++;
            }
        }

        // Even number of remainder-0 stones
        if (cnt0 % 2 == 0) {
            return cnt1 > 0 && cnt2 > 0;
        }

        // Odd number of remainder-0 stones
        return Math.abs(cnt1 - cnt2) > 2;
    }
}