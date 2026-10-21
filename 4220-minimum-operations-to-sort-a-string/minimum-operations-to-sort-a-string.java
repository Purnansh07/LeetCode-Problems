class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        boolean sorted = true;
        char minChar = arr[0];
        char maxChar = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                sorted = false;
            }

            minChar = (char) Math.min(minChar, arr[i]);
            maxChar = (char) Math.max(maxChar, arr[i]);
        }

        // Case 1: Already sorted
        if (sorted) {
            return 0;
        }

        // Case 2: Cannot sort the entire string in one operation
        if (n == 2) {
            return -1;
        }

        // Case 3: Minimum or maximum is already at the correct endpoint
        if (arr[0] == minChar || arr[n - 1] == maxChar) {
            return 1;
        }

        // Case 4: Minimum or maximum occurs in the middle
        for (int i = 1; i < n - 1; i++) {
            if (arr[i] == minChar || arr[i] == maxChar) {
                return 2;
            }
        }

        // Case 5: Minimum is at the end and maximum at the beginning
        return 3;
    }
}