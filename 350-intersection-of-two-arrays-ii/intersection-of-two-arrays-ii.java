class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] freq = new int[1001];

        for (int num : nums1) {
            freq[num]++;
        }

        int[] ans = new int[Math.min(nums1.length, nums2.length)];
        int k = 0;

        for (int num : nums2) {
            if (freq[num] > 0) {
                ans[k++] = num;
                freq[num]--;
            }
        }

        return Arrays.copyOf(ans, k);
    }
}