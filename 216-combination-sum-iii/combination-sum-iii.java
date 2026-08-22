class Solution {

    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> result = new ArrayList<>();

        backtrack(
            1,
            k,
            n,
            new ArrayList<>(),
            result
        );

        return result;
    }

    private void backtrack(
        int start,
        int k,
        int target,
        List<Integer> current,
        List<List<Integer>> result
    ) {

        // Found a valid combination
        if (k == 0) {

            if (target == 0) {
                result.add(new ArrayList<>(current));
            }

            return;
        }

        // No valid combination possible
        if (target <= 0) {
            return;
        }

        for (int i = start; i <= 9; i++) {

            // Since numbers increase,
            // no need to continue if i > target
            if (i > target) {
                break;
            }

            current.add(i);

            backtrack(
                i + 1,
                k - 1,
                target - i,
                current,
                result
            );

            current.remove(current.size() - 1);
        }
    }
}