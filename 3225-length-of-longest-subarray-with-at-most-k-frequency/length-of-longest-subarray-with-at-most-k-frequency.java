class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];

            freq.put(num, freq.getOrDefault(num, 0) + 1);

            while (freq.get(num) > k) {
                int leftNum = nums[left];

                freq.put(leftNum, freq.get(leftNum) - 1);
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}