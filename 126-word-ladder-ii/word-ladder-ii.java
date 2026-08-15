class Solution {
    public List<List<String>> findLadders(
            String beginWord,
            String endWord,
            List<String> wordList) {

        List<List<String>> result = new ArrayList<>();

        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) {
            return result;
        }

        // child -> all parents that can reach it
        Map<String, List<String>> parents = new HashMap<>();

        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);

        boolean found = false;

        while (!currentLevel.isEmpty() && !found) {

            // Remove current level from available words
            // so we don't revisit previous levels.
            wordSet.removeAll(currentLevel);

            Set<String> nextLevel = new HashSet<>();

            for (String word : currentLevel) {
                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {

                        if (c == original) {
                            continue;
                        }

                        chars[i] = c;
                        String nextWord = new String(chars);

                        if (wordSet.contains(nextWord)) {

                            nextLevel.add(nextWord);

                            parents
                                .computeIfAbsent(
                                    nextWord,
                                    k -> new ArrayList<>()
                                )
                                .add(word);

                            if (nextWord.equals(endWord)) {
                                found = true;
                            }
                        }
                    }

                    chars[i] = original;
                }
            }

            currentLevel = nextLevel;
        }

        if (!parents.containsKey(endWord)) {
            return result;
        }

        // Reconstruct paths from endWord to beginWord
        List<String> path = new ArrayList<>();
        path.add(endWord);

        backtrack(
            endWord,
            beginWord,
            parents,
            path,
            result
        );

        return result;
    }

    private void backtrack(
            String word,
            String beginWord,
            Map<String, List<String>> parents,
            List<String> path,
            List<List<String>> result) {

        if (word.equals(beginWord)) {
            List<String> sequence = new ArrayList<>(path);

            Collections.reverse(sequence);
            result.add(sequence);

            return;
        }

        for (String parent : parents.getOrDefault(
                word,
                Collections.emptyList())) {

            path.add(parent);

            backtrack(
                parent,
                beginWord,
                parents,
                path,
                result
            );

            path.remove(path.size() - 1);
        }
    }
}