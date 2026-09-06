class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        // Remove duplicates and assign ranks
        int m = 0;
        for (int x : sorted) {
            if (m == 0 || sorted[m - 1] != x) {
                sorted[m++] = x;
            }
        }

        // Fenwick Tree
        int[] bit = new int[m + 1];

        List<Integer> ans = new ArrayList<>(n);

        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            int rank = lowerBound(sorted, m, nums[i]) + 1;

            // Count values strictly smaller than nums[i]
            ans.add(query(bit, rank - 1));

            // Add current number
            update(bit, rank);
        }

        Collections.reverse(ans);
        return ans;
    }

    private int lowerBound(int[] arr, int size, int target) {
        int l = 0, r = size;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }

    private void update(int[] bit, int i) {
        while (i < bit.length) {
            bit[i]++;
            i += i & -i;
        }
    }

    private int query(int[] bit, int i) {
        int sum = 0;

        while (i > 0) {
            sum += bit[i];
            i -= i & -i;
        }

        return sum;
    }
}