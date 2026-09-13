class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int n = word.length();

            for (int j = 0; j <= n; j++) {

                // Case 1:
                // word[j..n-1] is palindrome.
                // Need reverse(word[0..j-1]) before word.
                if (isPalindrome(word, j, n - 1)) {
                    String left = word.substring(0, j);
                    String rev = reverse(left);

                    Integer k = map.get(rev);

                    if (k != null && k != i) {
                        ans.add(Arrays.asList(i, k));
                    }
                }

                // Case 2:
                // word[0..j-1] is palindrome.
                // Need reverse(word[j..n-1]) before word.
                if (j > 0 && isPalindrome(word, 0, j - 1)) {
                    String right = word.substring(j);
                    String rev = reverse(right);

                    Integer k = map.get(rev);

                    if (k != null && k != i) {
                        ans.add(Arrays.asList(k, i));
                    }
                }
            }
        }

        return ans;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }

        return true;
    }

    private String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}