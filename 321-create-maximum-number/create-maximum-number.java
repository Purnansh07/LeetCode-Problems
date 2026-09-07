class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        int[] best = new int[k];

        // Number of elements taken from nums1
        int start = Math.max(0, k - n);
        int end = Math.min(k, m);

        for (int take1 = start; take1 <= end; take1++) {
            int take2 = k - take1;

            int[] a = maxSubsequence(nums1, take1);
            int[] b = maxSubsequence(nums2, take2);

            int[] candidate = merge(a, b);

            if (greater(candidate, 0, best, 0)) {
                best = candidate;
            }
        }

        return best;
    }

    // Maximum subsequence of length k
    private int[] maxSubsequence(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }

        int[] stack = new int[k];
        int top = 0;
        int remove = nums.length - k;

        for (int num : nums) {

            while (top > 0 &&
                   remove > 0 &&
                   stack[top - 1] < num) {
                top--;
                remove--;
            }

            if (top < k) {
                stack[top++] = num;
            } else {
                remove--;
            }
        }

        return stack;
    }

    // Merge two sequences into the largest possible sequence
    private int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];

        int i = 0;
        int j = 0;

        for (int p = 0; p < result.length; p++) {
            if (greater(a, i, b, j)) {
                result[p] = a[i++];
            } else {
                result[p] = b[j++];
            }
        }

        return result;
    }

    // Is a[i...] lexicographically greater than b[j...]?
    private boolean greater(int[] a, int i, int[] b, int j) {
        while (i < a.length && j < b.length) {
            if (a[i] != b[j]) {
                return a[i] > b[j];
            }

            i++;
            j++;
        }

        return i < a.length;
    }
}