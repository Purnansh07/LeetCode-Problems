class Solution {

    public boolean isAdditiveNumber(String num) {
        int n = num.length();

        // Choose first number
        for (int i = 1; i < n - 1; i++) {

            // Leading zero
            if (i > 1 && num.charAt(0) == '0') {
                break;
            }

            String a = num.substring(0, i);

            // Choose second number
            for (int j = i + 1; j < n; j++) {

                // Leading zero
                if (j - i > 1 && num.charAt(i) == '0') {
                    break;
                }

                String b = num.substring(i, j);

                if (check(num, j, a, b)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean check(String num, int index, String a, String b) {

        int count = 2;

        while (index < num.length()) {

            String sum = add(a, b);

            // Next number must exactly match the sum
            if (!num.startsWith(sum, index)) {
                return false;
            }

            index += sum.length();

            a = b;
            b = sum;
            count++;
        }

        return count >= 3;
    }

    // Add two numbers represented as strings
    private String add(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {

            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }

            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }

            sb.append(sum % 10);
            carry = sum / 10;
        }

        return sb.reverse().toString();
    }
}