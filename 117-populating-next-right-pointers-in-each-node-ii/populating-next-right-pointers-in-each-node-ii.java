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
        Node current = root;

        while (current != null) {

            // Dummy node for the next level
            Node dummy = new Node(0);

            // Tail of the next level
            Node tail = dummy;

            while (current != null) {

                // Add left child
                if (current.left != null) {
                    tail.next = current.left;
                    tail = tail.next;
                }

                // Add right child
                if (current.right != null) {
                    tail.next = current.right;
                    tail = tail.next;
                }

                // Move across current level
                current = current.next;
            }

            // Move to the first node of next level
            current = dummy.next;
        }

        return root;
    }
}