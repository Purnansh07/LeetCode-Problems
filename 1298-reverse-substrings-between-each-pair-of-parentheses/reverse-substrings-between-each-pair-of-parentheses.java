class Solution {
    public String reverseParentheses(String s) {
        Deque<StringBuilder> stack = new ArrayDeque<>();
        StringBuilder curr = new StringBuilder();

        for (char c : s.toCharArray()) {

            if (c == '(') {
                stack.push(curr);
                curr = new StringBuilder();

            } else if (c == ')') {
                curr.reverse();

                StringBuilder prev = stack.pop();
                prev.append(curr);
                curr = prev;

            } else {
                curr.append(c);
            }
        }

        return curr.toString();
    }
}