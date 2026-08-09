class Solution {
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExponent = false;
        boolean exponentDigit = true;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                seenDigit = true;

                if (seenExponent) {
                    exponentDigit = true;
                }

            } else if (c == '+' || c == '-') {
                // Sign is allowed only at the beginning
                // or immediately after e/E.
                if (i > 0 &&
                    s.charAt(i - 1) != 'e' &&
                    s.charAt(i - 1) != 'E') {
                    return false;
                }

            } else if (c == '.') {
                // Dot cannot appear after exponent or more than once.
                if (seenDot || seenExponent) {
                    return false;
                }

                seenDot = true;

            } else if (c == 'e' || c == 'E') {
                // Exponent requires a number before it.
                // Only one exponent is allowed.
                if (seenExponent || !seenDigit) {
                    return false;
                }

                seenExponent = true;
                exponentDigit = false;

            } else {
                return false;
            }
        }

        return seenDigit && exponentDigit;
    }
}