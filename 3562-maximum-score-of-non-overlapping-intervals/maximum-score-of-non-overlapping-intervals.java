class Solution {

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0); // start
            a[i][1] = intervals.get(i).get(1); // end
            a[i][2] = intervals.get(i).get(2); // weight
            a[i][3] = i;                       // original index
        }

        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0])
                return Integer.compare(x[0], y[0]);
            return Integer.compare(x[1], y[1]);
        });

        // next[i] = first interval starting strictly after a[i].end
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = upperBound(a, a[i][1]);
        }

        long[][] dp = new long[n + 1][5];

        // best[i][k] = lexicographically smallest indices
        // for the optimal score from i onward using at most k intervals.
        int[][][] best = new int[n + 1][5][];

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // Option 1: Skip current interval
                long skipScore = dp[i + 1][k];
                int[] skip = best[i + 1][k];

                // Option 2: Take current interval
                long takeScore = a[i][2] + dp[next[i]][k - 1];

                int[] take = addIndex(
                    best[next[i]][k - 1],
                    a[i][3]
                );

                if (takeScore > skipScore) {
                    dp[i][k] = takeScore;
                    best[i][k] = take;
                } 
                else if (takeScore < skipScore) {
                    dp[i][k] = skipScore;
                    best[i][k] = skip;
                } 
                else {
                    dp[i][k] = skipScore;
                    best[i][k] = smaller(skip, take);
                }
            }
        }

        return best[0][4];
    }

    private int[] addIndex(int[] arr, int index) {

        // No previously selected intervals
        if (arr == null) {
            return new int[]{index};
        }

        int[] res = new int[arr.length + 1];

        int pos = 0;

        while (pos < arr.length && arr[pos] < index) {
            res[pos] = arr[pos];
            pos++;
        }

        res[pos] = index;

        while (pos < arr.length) {
            res[pos + 1] = arr[pos];
            pos++;
        }

        return res;
    }

    private int[] smaller(int[] a, int[] b) {

        if (a == null) return b;
        if (b == null) return a;

        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i] ? a : b;
            }
        }

        return a.length <= b.length ? a : b;
    }

    private int upperBound(int[][] a, int target) {
        int l = 0;
        int r = a.length;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (a[mid][0] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}