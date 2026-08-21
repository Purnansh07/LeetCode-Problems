class Trie {

    private static class Node {

        Node[] children = new Node[26];

        boolean isEnd;
    }

    private final Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {

        Node current = root;

        for (char ch : word.toCharArray()) {

            int index = ch - 'a';

            if (current.children[index] == null) {
                current.children[index] = new Node();
            }

            current = current.children[index];
        }

        current.isEnd = true;
    }

    public boolean search(String word) {

        Node node = findNode(word);

        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {

        return findNode(prefix) != null;
    }

    private Node findNode(String word) {

        Node current = root;

        for (char ch : word.toCharArray()) {

            int index = ch - 'a';

            if (current.children[index] == null) {
                return null;
            }

            current = current.children[index];
        }

        return current;
    }
}