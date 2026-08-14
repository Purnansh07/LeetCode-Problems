/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node leftmost = root;

        while (leftmost.left != null) {
            Node current = leftmost;

            while (current != null) {
                // Connect left child to right child
                current.left.next = current.right;

                // Connect right child to next node's left child
                if (current.next != null) {
                    current.right.next = current.next.left;
                }

                current = current.next;
            }

            // Move to next level
            leftmost = leftmost.left;
        }

        return root;
    }
}