class Solution {
    public int maxProduct(int[] nums) {

        int maxProduct = nums[0];
        int minProduct = nums[0];

        int answer = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int num = nums[i];

            // Save old values before updating
            int oldMax = maxProduct;
            int oldMin = minProduct;

            maxProduct = Math.max(
                num,
                Math.max(
                    num * oldMax,
                    num * oldMin
                )
            );

            minProduct = Math.min(
                num,
                Math.min(
                    num * oldMax,
                    num * oldMin
                )
            );

            answer = Math.max(answer, maxProduct);
        }

        return answer;
    }
}