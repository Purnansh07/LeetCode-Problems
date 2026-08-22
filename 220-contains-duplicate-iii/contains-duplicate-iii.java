class Solution {
    public boolean containsNearbyAlmostDuplicate(
            int[] nums,
            int indexDiff,
            int valueDiff) {

        if (indexDiff <= 0 || valueDiff < 0) {
            return false;
        }

        TreeSet<Long> window = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {

            long num = nums[i];

            // Find the smallest value >= num - valueDiff
            Long candidate = window.ceiling(
                    num - valueDiff
            );

            // Check whether candidate <= num + valueDiff
            if (candidate != null &&
                candidate <= num + valueDiff) {
                return true;
            }

            window.add(num);

            // Keep only the previous indexDiff elements
            if (i >= indexDiff) {
                window.remove((long) nums[i - indexDiff]);
            }
        }

        return false;
    }
}