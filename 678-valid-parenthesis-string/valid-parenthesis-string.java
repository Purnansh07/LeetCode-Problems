class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } 
            else if (c == ')') {
                low--;
                high--;
            } 
            else { // '*'
                low--;
                high++;
            }

            // Minimum unmatched '(' cannot be negative
            if (low < 0) {
                low = 0;
            }

            // Even the maximum possible balance is negative
            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }
}