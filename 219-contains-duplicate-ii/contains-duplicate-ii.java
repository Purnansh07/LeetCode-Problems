class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        Map<Integer, Integer> lastSeen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (lastSeen.containsKey(nums[i])) {

                int previousIndex = lastSeen.get(nums[i]);

                if (i - previousIndex <= k) {
                    return true;
                }
            }

            // Always store the latest index
            lastSeen.put(nums[i], i);
        }

        return false;
    }
}