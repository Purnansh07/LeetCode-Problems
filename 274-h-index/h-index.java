class Solution {
    public int hIndex(int[] citations) {

        Arrays.sort(citations);

        int n = citations.length;

        for (int i = n - 1; i >= 0; i--) {

            int papers = n - i;

            if (citations[i] < papers) {
                return papers - 1;
            }
        }

        return n;
    }
}