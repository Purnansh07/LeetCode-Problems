class WordDictionary {

    private static class TrieNode {

        TrieNode[] children = new TrieNode[26];

        boolean isEnd;
    }

    private final TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {

        TrieNode current = root;

        for (char ch : word.toCharArray()) {

            int index = ch - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isEnd = true;
    }

    public boolean search(String word) {

        return dfs(word, 0, root);
    }

    private boolean dfs(
        String word,
        int index,
        TrieNode node
    ) {

        if (index == word.length()) {
            return node.isEnd;
        }

        char ch = word.charAt(index);

        // Normal character
        if (ch != '.') {

            int childIndex = ch - 'a';

            if (node.children[childIndex] == null) {
                return false;
            }

            return dfs(
                word,
                index + 1,
                node.children[childIndex]
            );
        }

        // '.' → try every character
        for (TrieNode child : node.children) {

            if (child != null &&
                dfs(word, index + 1, child)) {

                return true;
            }
        }

        return false;
    }
}