public class Solution {
    public int reverseBits(int n) {

        int result = 0;

        for (int i = 0; i < 32; i++) {

            // Take the last bit of n
            int bit = n & 1;

            // Shift result left and add the bit
            result = (result << 1) | bit;

            // Move to the next bit
            n >>>= 1;
        }

        return result;
    }
}