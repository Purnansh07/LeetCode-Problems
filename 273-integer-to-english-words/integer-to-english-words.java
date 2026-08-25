class Solution {

    private final String[] below20 = {
        "", "One", "Two", "Three", "Four",
        "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen",
        "Fourteen", "Fifteen", "Sixteen", "Seventeen",
        "Eighteen", "Nineteen"
    };

    private final String[] tens = {
        "", "", "Twenty", "Thirty", "Forty",
        "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public String numberToWords(int num) {

        if (num == 0) {
            return "Zero";
        }

        StringBuilder result = new StringBuilder();

        if (num >= 1_000_000_000) {
            result.append(convert(num / 1_000_000_000))
                  .append(" Billion ");
            num %= 1_000_000_000;
        }

        if (num >= 1_000_000) {
            result.append(convert(num / 1_000_000))
                  .append(" Million ");
            num %= 1_000_000;
        }

        if (num >= 1000) {
            result.append(convert(num / 1000))
                  .append(" Thousand ");
            num %= 1000;
        }

        if (num > 0) {
            result.append(convert(num));
        }

        return result.toString().trim().replaceAll(" +", " ");
    }

    private String convert(int num) {

        if (num == 0) {
            return "";
        }

        if (num < 20) {
            return below20[num];
        }

        if (num < 100) {
            return tens[num / 10] +
                   (num % 10 == 0 ? "" : " " + below20[num % 10]);
        }

        return below20[num / 100] +
               " Hundred" +
               (num % 100 == 0 ? "" : " " + convert(num % 100));
    }
}