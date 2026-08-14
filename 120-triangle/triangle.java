class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        // Start from the second-last row
        for (int i = n - 2; i >= 0; i--) {

            for (int j = 0; j <= i; j++) {

                int current = triangle.get(i).get(j);

                int left = triangle.get(i + 1).get(j);
                int right = triangle.get(i + 1).get(j + 1);

                triangle.get(i).set(
                    j,
                    current + Math.min(left, right)
                );
            }
        }

        return triangle.get(0).get(0);
    }
}