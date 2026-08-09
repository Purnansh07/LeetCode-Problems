class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int j = i;
            int lineLength = 0;

            // Find all words that fit in the current line
            while (j < words.length &&
                   lineLength + words[j].length() + (j - i) <= maxWidth) {
                lineLength += words[j].length();
                j++;
            }

            int wordCount = j - i;
            int spaces = maxWidth - lineLength;

            StringBuilder line = new StringBuilder();

            // Last line or single-word line -> left justified
            if (j == words.length || wordCount == 1) {
                for (int k = i; k < j; k++) {
                    if (k > i) {
                        line.append(' ');
                    }
                    line.append(words[k]);
                }

                while (line.length() < maxWidth) {
                    line.append(' ');
                }

            } else {
                // Fully justified line
                int gaps = wordCount - 1;
                int spacesPerGap = spaces / gaps;
                int extraSpaces = spaces % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1) {
                        int count = spacesPerGap;

                        if (k - i < extraSpaces) {
                            count++;
                        }

                        line.append(" ".repeat(count));
                    }
                }
            }

            result.add(line.toString());
            i = j;
        }

        return result;
    }
}