class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int[] nums, int start,
                           List<Integer> current,
                           List<List<Integer>> result) {

        // Every current state is a valid subset
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            // Include nums[i]
            current.add(nums[i]);

            // Move forward so elements aren't reused
            backtrack(nums, i + 1, current, result);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}