class Solution {
    public int calculate(String s) {

        int n = s.length();

        Stack<Integer> stack = new Stack<>();

        int number = 0;
        char operation = '+';

        for (int i = 0; i < n; i++) {

            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                number = number * 10 + (ch - '0');
            }

            // Process operation when we reach it
            if ((!Character.isDigit(ch) && ch != ' ')
                    || i == n - 1) {

                if (operation == '+') {
                    stack.push(number);
                }

                else if (operation == '-') {
                    stack.push(-number);
                }

                else if (operation == '*') {
                    stack.push(stack.pop() * number);
                }

                else if (operation == '/') {
                    stack.push(stack.pop() / number);
                }

                operation = ch;
                number = 0;
            }
        }

        int result = 0;

        for (int value : stack) {
            result += value;
        }

        return result;
    }
}