
import java.util.TreeSet;

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Ensure we square the smaller dimension.
        boolean transpose = m > n;

        int rows = transpose ? n : m;
        int cols = transpose ? m : n;

        int ans = Integer.MIN_VALUE;

        for (int start = 0; start < rows; start++) {
            int[] sums = new int[cols];

            for (int end = start; end < rows; end++) {

                // Compress the current row/column range.
                for (int j = 0; j < cols; j++) {
                    sums[j] += transpose
                            ? matrix[j][end]
                            : matrix[end][j];
                }

                // Find maximum subarray sum <= k.
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);

                int prefix = 0;

                for (int value : sums) {
                    prefix += value;

                    Integer previous = set.ceiling(prefix - k);

                    if (previous != null) {
                        ans = Math.max(ans, prefix - previous);
                    }

                    // Cannot improve beyond k.
                    if (ans == k) {
                        return k;
                    }

                    set.add(prefix);
                }
            }
        }

        return ans;
    }
}
