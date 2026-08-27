class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Try to find the rightmost position
        // where we can make the string greater.
        for (int i = n - 1; i >= 0; i--) {

            // Rebuild frequency of characters
            // remaining after target[0 ... i-1]
            int[] count = freq.clone();

            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int x = target.charAt(j) - 'a';

                if (count[x] == 0) {
                    possible = false;
                    break;
                }

                count[x]--;
            }

            if (!possible) {
                continue;
            }

            int cur = target.charAt(i) - 'a';

            // Find the smallest character greater than target[i]
            for (int c = cur + 1; c < 26; c++) {

                if (count[c] == 0) {
                    continue;
                }

                char[] ans = new char[n];

                // Keep prefix same as target
                for (int j = 0; j < i; j++) {
                    ans[j] = target.charAt(j);
                }

                // Make current position greater
                ans[i] = (char) ('a' + c);
                count[c]--;

                // Fill remaining characters in ascending order
                int pos = i + 1;

                for (int x = 0; x < 26; x++) {
                    while (count[x] > 0) {
                        ans[pos++] = (char) ('a' + x);
                        count[x]--;
                    }
                }

                return new String(ans);
            }
        }

        return "";
    }
}