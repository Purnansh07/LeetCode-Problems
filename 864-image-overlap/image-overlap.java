class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        // Store positions of all 1s
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    ones1.add(new int[]{r, c});
                }

                if (img2[r][c] == 1) {
                    ones2.add(new int[]{r, c});
                }
            }
        }

        Map<Integer, Integer> count = new HashMap<>();
        int ans = 0;

        // Compare every 1 in img1 with every 1 in img2
        for (int[] a : ones1) {
            for (int[] b : ones2) {

                int dr = a[0] - b[0];
                int dc = a[1] - b[1];

                // Encode (dr, dc) into one integer.
                // dr, dc are in [-29, 29], so 100 is safe.
                int key = dr * 100 + dc;

                int freq = count.getOrDefault(key, 0) + 1;
                count.put(key, freq);

                ans = Math.max(ans, freq);
            }
        }

        return ans;
    }
}