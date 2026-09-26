class Solution {

    private final TreeSet<String> result = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(result);
    }

    private void dfs(String exp) {
        // No braces left -> complete word
        int right = exp.indexOf('}');

        if (right == -1) {
            result.add(exp);
            return;
        }

        // Find the matching '{'
        int left = exp.lastIndexOf('{', right);

        String prefix = exp.substring(0, left);
        String inside = exp.substring(left + 1, right);
        String suffix = exp.substring(right + 1);

        // Try every comma-separated option
        for (String option : inside.split(",")) {
            dfs(prefix + option + suffix);
        }
    }
}