class Solution {
    public String removeDuplicateLetters(String s) {
        int[] last = new int[26];

        // Last occurrence of every character
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        char[] stack = new char[26];
        boolean[] used = new boolean[26];
        int top = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = c - 'a';

            // Already included
            if (used[idx]) {
                continue;
            }

            // Remove larger characters if they occur again later
            while (top > 0 &&
                   stack[top - 1] > c &&
                   last[stack[top - 1] - 'a'] > i) {

                used[stack[top - 1] - 'a'] = false;
                top--;
            }

            stack[top++] = c;
            used[idx] = true;
        }

        return new String(stack, 0, top);
    }
}