class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();

        if (s.length() < 4 || s.length() > 12) {
            return result;
        }

        backtrack(s, 0, 0, new StringBuilder(), result);

        return result;
    }

    private void backtrack(String s, int index, int parts,
                            StringBuilder current,
                            List<String> result) {

        // All 4 parts are formed
        if (parts == 4) {
            if (index == s.length()) {
                result.add(current.toString());
            }
            return;
        }

        // Remaining characters must fit in remaining parts
        int remainingChars = s.length() - index;
        int remainingParts = 4 - parts;

        if (remainingChars < remainingParts ||
            remainingChars > remainingParts * 3) {
            return;
        }

        int length = current.length();

        for (int end = index; end < s.length() && end < index + 3; end++) {

            // Leading zero: only "0" is valid
            if (end > index && s.charAt(index) == '0') {
                break;
            }

            int value = 0;

            for (int i = index; i <= end; i++) {
                value = value * 10 + (s.charAt(i) - '0');
            }

            // IP segment must be <= 255
            if (value > 255) {
                break;
            }

            if (parts > 0) {
                current.append('.');
            }

            current.append(s, index, end + 1);

            backtrack(
                s,
                end + 1,
                parts + 1,
                current,
                result
            );

            // Restore StringBuilder
            current.setLength(length);
        }
    }
}