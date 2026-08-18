class Solution {
    public int maximumGap(int[] nums) {

        int n = nums.length;

        if (n < 2) {
            return 0;
        }

        int min = nums[0];
        int max = nums[0];

        // Find minimum and maximum
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // All numbers are the same
        if (min == max) {
            return 0;
        }

        // Bucket size
        int gap = Math.max(
            1,
            (max - min) / (n - 1)
        );

        int bucketCount = (max - min) / gap + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];

        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // Put numbers into buckets
        for (int num : nums) {

            int index = (num - min) / gap;

            bucketMin[index] =
                Math.min(bucketMin[index], num);

            bucketMax[index] =
                Math.max(bucketMax[index], num);
        }

        int answer = 0;
        int previousMax = min;

        // Compare adjacent non-empty buckets
        for (int i = 0; i < bucketCount; i++) {

            if (bucketMin[i] == Integer.MAX_VALUE) {
                continue;
            }

            answer = Math.max(
                answer,
                bucketMin[i] - previousMax
            );

            previousMax = bucketMax[i];
        }

        return answer;
    }
}