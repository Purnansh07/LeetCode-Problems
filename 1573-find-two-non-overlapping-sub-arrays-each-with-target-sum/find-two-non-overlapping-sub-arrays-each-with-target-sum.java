class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = n + 1;

        // best[i] = minimum length of a valid subarray
        // completely inside [0 ... i]
        int[] best = new int[n];
        Arrays.fill(best, INF);

        int left = 0;
        int sum = 0;
        int ans = INF;
        int minLength = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int len = right - left + 1;

                // Combine with the best subarray ending
                // before this one.
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, len + best[left - 1]);
                }

                minLength = Math.min(minLength, len);
            }

            // Carry forward the best subarray seen so far.
            best[right] = minLength;
        }

        return ans == INF ? -1 : ans;
    }
}