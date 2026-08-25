class Solution {
    public int missingMultiple(int[] nums, int k) {

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        for (int x = k; ; x += k) {
            if (!set.contains(x)) {
                return x;
            }
        }
    }
}