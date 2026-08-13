class Solution {
    private char[] leftChar;
    private char[] rightChar;
    private int[] len;
    private int[] prefix;
    private int[] suffix;
    private int[] best;

    public int[] longestRepeating(String s, String queryCharacters,
                                  int[] queryIndices) {

        int n = s.length();

        leftChar = new char[4 * n];
        rightChar = new char[4 * n];
        len = new int[4 * n];
        prefix = new int[4 * n];
        suffix = new int[4 * n];
        best = new int[4 * n];

        build(1, 0, n - 1, s);

        int[] result = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {
            update(
                1,
                0,
                n - 1,
                queryIndices[i],
                queryCharacters.charAt(i)
            );

            result[i] = best[1];
        }

        return result;
    }

    private void build(int node, int l, int r, String s) {
        if (l == r) {
            char c = s.charAt(l);

            leftChar[node] = c;
            rightChar[node] = c;
            len[node] = 1;
            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, s);
        build(node * 2 + 1, mid + 1, r, s);

        merge(node);
    }

    private void update(int node, int l, int r,
                        int index, char c) {

        if (l == r) {
            leftChar[node] = c;
            rightChar[node] = c;
            len[node] = 1;
            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, c);
        } else {
            update(node * 2 + 1, mid + 1, r, index, c);
        }

        merge(node);
    }

    private void merge(int node) {
        int a = node * 2;
        int b = node * 2 + 1;

        leftChar[node] = leftChar[a];
        rightChar[node] = rightChar[b];
        len[node] = len[a] + len[b];

        prefix[node] = prefix[a];
        suffix[node] = suffix[b];

        best[node] = Math.max(best[a], best[b]);

        if (rightChar[a] == leftChar[b]) {

            // Prefix can extend through the entire left segment
            if (prefix[a] == len[a]) {
                prefix[node] = len[a] + prefix[b];
            }

            // Suffix can extend through the entire right segment
            if (suffix[b] == len[b]) {
                suffix[node] = len[b] + suffix[a];
            }

            // Combine suffix of left + prefix of right
            best[node] = Math.max(
                best[node],
                suffix[a] + prefix[b]
            );
        }
    }
}