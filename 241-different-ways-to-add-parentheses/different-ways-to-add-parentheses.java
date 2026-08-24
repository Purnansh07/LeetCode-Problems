class Solution {

    private final Map<String, List<Integer>> memo = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {

        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {

            char ch = expression.charAt(i);

            if (ch != '+' && ch != '-' && ch != '*') {
                continue;
            }

            String leftPart = expression.substring(0, i);
            String rightPart = expression.substring(i + 1);

            List<Integer> leftResults =
                diffWaysToCompute(leftPart);

            List<Integer> rightResults =
                diffWaysToCompute(rightPart);

            for (int left : leftResults) {
                for (int right : rightResults) {

                    if (ch == '+') {
                        result.add(left + right);
                    }
                    else if (ch == '-') {
                        result.add(left - right);
                    }
                    else {
                        result.add(left * right);
                    }
                }
            }
        }

        // Expression contains only a number
        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression));
        }

        memo.put(expression, result);

        return result;
    }
}