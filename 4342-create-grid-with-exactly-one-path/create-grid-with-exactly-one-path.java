
import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];

        for (int i = 0; i < m; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '#');

            if (i == 0) {
                Arrays.fill(row, '.');
            }

            row[n - 1] = '.';
            ans[i] = new String(row);
        }

        return ans;
    }
}
