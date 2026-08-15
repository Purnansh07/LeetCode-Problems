class Solution {
    public int ladderLength(String beginWord,
                            String endWord,
                            List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);

        // endWord must exist
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();

                if (word.equals(endWord)) {
                    return level;
                }

                char[] chars = word.toCharArray();

                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];

                    for (char c = 'a'; c <= 'z'; c++) {

                        if (c == original) {
                            continue;
                        }

                        chars[j] = c;

                        String nextWord = new String(chars);

                        if (wordSet.contains(nextWord)) {
                            queue.offer(nextWord);

                            // Mark visited
                            wordSet.remove(nextWord);
                        }
                    }

                    chars[j] = original;
                }
            }

            level++;
        }

        return 0;
    }
}