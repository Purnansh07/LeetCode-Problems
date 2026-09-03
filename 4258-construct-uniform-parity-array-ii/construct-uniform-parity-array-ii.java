class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;

        for (int x : nums1) {
            min = Math.min(min, x);
        }

        // If the smallest number is odd,
        // every larger even number can subtract it
        // and become odd.
        if ((min & 1) == 1) {
            return true;
        }

        // If minimum is even, then every number
        // must already be even.
        for (int x : nums1) {
            if ((x & 1) == 1) {
                return false;
            }
        }

        return true;
    }
}