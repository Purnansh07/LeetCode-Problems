class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;

        // Check all 32 bits
        for (int bit = 0; bit < 32; bit++) {

            int count = 0;

            for (int num : nums) {
                if ((num & (1 << bit)) != 0) {
                    count++;
                }
            }

            // The single number contributes the remainder
            if (count % 3 != 0) {
                result |= (1 << bit);
            }
        }

        return result;
    }
}