class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        backtrack(s, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(
            String s,
            int start,
            List<String> path,
            List<List<String>> result) {

        // Reached the end
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        // Try every possible substring
        for (int end = start; end < s.length(); end++) {

            if (isPalindrome(s, start, end)) {

                // Choose
                path.add(s.substring(start, end + 1));

                // Explore
                backtrack(
                    s,
                    end + 1,
                    path,
                    result
                );

                // Backtrack
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}