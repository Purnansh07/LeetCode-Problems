class Solution {
    public int kthSmallest(TreeNode root, int k) {

        Deque<TreeNode> stack = new ArrayDeque<>();

        while (true) {

            // Go as far left as possible
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // Smallest remaining node
            root = stack.pop();

            k--;

            // kth smallest found
            if (k == 0) {
                return root.val;
            }

            // Process right subtree
            root = root.right;
        }
    }
}