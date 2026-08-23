class Solution {
    public int calculate(String s) {

        int result = 0;
        int number = 0;
        int sign = 1;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // Build number
            if (Character.isDigit(ch)) {

                number = number * 10 + (ch - '0');

            }

            // Addition
            else if (ch == '+') {

                result += sign * number;

                number = 0;
                sign = 1;
            }

            // Subtraction
            else if (ch == '-') {

                result += sign * number;

                number = 0;
                sign = -1;
            }

            // Opening parenthesis
            else if (ch == '(') {

                // Save current result
                stack.push(result);

                // Save current sign
                stack.push(sign);

                // Reset for inside parentheses
                result = 0;
                sign = 1;
            }

            // Closing parenthesis
            else if (ch == ')') {

                // Finish current number
                result += sign * number;

                number = 0;

                // Sign before '('
                int previousSign = stack.pop();

                // Result before '('
                int previousResult = stack.pop();

                result = previousResult +
                         previousSign * result;
            }
        }

        // Add final number
        result += sign * number;

        return result;
    }
}