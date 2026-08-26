class Solution {

    public List<String> addOperators(String num, int target) {

        List<String> result = new ArrayList<>();

        dfs(num, target, 0, 0, 0, new StringBuilder(), result);

        return result;
    }

    private void dfs(
        String num,
        long target,
        int index,
        long value,
        long prev,
        StringBuilder expression,
        List<String> result
    ) {

        // Entire string is consumed
        if (index == num.length()) {

            if (value == target) {
                result.add(expression.toString());
            }

            return;
        }

        int length = expression.length();

        // Try every possible next number
        for (int i = index; i < num.length(); i++) {

            // Don't allow numbers with leading zeros
            if (i > index && num.charAt(index) == '0') {
                break;
            }

            long current = Long.parseLong(
                num.substring(index, i + 1)
            );

            // First number
            if (index == 0) {

                expression.append(current);

                dfs(
                    num,
                    target,
                    i + 1,
                    current,
                    current,
                    expression,
                    result
                );

                expression.setLength(length);

            } else {

                // +
                expression.append("+").append(current);

                dfs(
                    num,
                    target,
                    i + 1,
                    value + current,
                    current,
                    expression,
                    result
                );

                expression.setLength(length);

                // -
                expression.append("-").append(current);

                dfs(
                    num,
                    target,
                    i + 1,
                    value - current,
                    -current,
                    expression,
                    result
                );

                expression.setLength(length);

                // *
                expression.append("*").append(current);

                dfs(
                    num,
                    target,
                    i + 1,
                    value - prev + prev * current,
                    prev * current,
                    expression,
                    result
                );

                expression.setLength(length);
            }
        }
    }
}