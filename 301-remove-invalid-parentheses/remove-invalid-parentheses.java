class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Set<String> result = new HashSet<>();

        int left = 0;
        int right = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        dfs(s, 0, left, right, 0,
            new StringBuilder(), result);

        return new ArrayList<>(result);
    }

    private void dfs(
        String s,
        int i,
        int removeLeft,
        int removeRight,
        int open,
        StringBuilder path,
        Set<String> result
    ) {
        if (removeLeft < 0 || removeRight < 0 || open < 0) {
            return;
        }

        if (i == s.length()) {
            if (removeLeft == 0 &&
                removeRight == 0 &&
                open == 0) {
                result.add(path.toString());
            }
            return;
        }

        char c = s.charAt(i);
        int len = path.length();

        if (c == '(') {

            // Remove
            if (removeLeft > 0) {
                dfs(s, i + 1,
                    removeLeft - 1, removeRight,
                    open, path, result);
            }

            // Keep
            path.append(c);
            dfs(s, i + 1,
                removeLeft, removeRight,
                open + 1, path, result);
            path.setLength(len);

        } else if (c == ')') {

            // Remove
            if (removeRight > 0) {
                dfs(s, i + 1,
                    removeLeft, removeRight - 1,
                    open, path, result);
            }

            // Keep
            if (open > 0) {
                path.append(c);

                dfs(s, i + 1,
                    removeLeft, removeRight,
                    open - 1, path, result);

                path.setLength(len);
            }

        } else {
            path.append(c);

            dfs(s, i + 1,
                removeLeft, removeRight,
                open, path, result);

            path.setLength(len);
        }
    }
}