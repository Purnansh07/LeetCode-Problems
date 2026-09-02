class Solution {
    public int findDuplicate(int[] nums) {
        // Phase 1: Find intersection point
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Phase 2: Find cycle entrance
        int finder = nums[0];

        while (finder != slow) {
            finder = nums[finder];
            slow = nums[slow];
        }

        return finder;
    }
}