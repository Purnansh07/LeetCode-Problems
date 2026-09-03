class NumArray {

    private int[] nums;
    private int[] tree;

    public NumArray(int[] nums) {
        int n = nums.length;

        this.nums = nums.clone();
        this.tree = new int[n + 1];

        // Build Fenwick Tree
        for (int i = 0; i < n; i++) {
            add(i + 1, nums[i]);
        }
    }

    // Add delta to index i (1-based)
    private void add(int i, int delta) {
        while (i < tree.length) {
            tree[i] += delta;
            i += i & -i;
        }
    }

    // Prefix sum of first i elements
    private int prefixSum(int i) {
        int sum = 0;

        while (i > 0) {
            sum += tree[i];
            i -= i & -i;
        }

        return sum;
    }

    public void update(int index, int val) {
        int delta = val - nums[index];

        nums[index] = val;
        add(index + 1, delta);
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }
}